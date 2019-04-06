package ru.javaops.masterjava.persist.dao;

import com.bertoncelj.jdbi.entitymapper.EntityMapperFactory;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;
import org.skife.jdbi.v2.unstable.BindIn;
import ru.javaops.masterjava.persist.model.Group;

import java.util.List;

@RegisterMapperFactory(EntityMapperFactory.class)
@UseStringTemplate3StatementLocator
public abstract class GroupDao implements AbstractDao {
    public Group insert(Group group) {
        if (group.isNew()) {
            int id = insertGeneratedId(group);
            group.setId(id);
        } else {
            insertWitId(group);
        }
        return group;
    }

    @SqlUpdate("INSERT INTO \"groups\" (name, type) VALUES (:name, CAST(:type AS GROUP_TYPE))")
    @GetGeneratedKeys
    abstract int insertGeneratedId(@BindBean Group group);

    @SqlUpdate("INSERT INTO \"groups\" (id, name, type) VALUES (:id, :name, CAST(:type AS GROUP_TYPE)) ")
    abstract void insertWitId(@BindBean Group group);

    @SqlQuery("SELECT * FROM \"groups\" LIMIT :it")
    public abstract List<Group> getWithLimit(@Bind int limit);

    @SqlQuery("SELECT * FROM \"groups\" WHERE id=:it")
    public abstract Group getById(@Bind int key);

    @SqlQuery("SELECT * FROM \"groups\" WHERE name=:it")
    public abstract Group getByName(@Bind String name);

    @SqlQuery("SELECT * FROM \"groups\" WHERE name IN (<names>)")
    public abstract List<Group> getByNames(@BindIn("names") String... keys);

    @SqlUpdate("TRUNCATE \"groups\"")
    @Override
    public abstract void clean();

    @SqlUpdate("UPDATE \"groups\" SET name=:name, type=CAST(:type AS GROUP_TYPE) WHERE id=:id")
    public abstract int update(@BindBean Group group);

}
