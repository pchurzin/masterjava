package ru.javaops.masterjava.persist.dao;

import com.bertoncelj.jdbi.entitymapper.EntityMapperFactory;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;
import org.skife.jdbi.v2.unstable.BindIn;
import ru.javaops.masterjava.persist.model.Group;
import ru.javaops.masterjava.persist.model.Project;

import java.util.List;

@RegisterMapperFactory(EntityMapperFactory.class)
@UseStringTemplate3StatementLocator
public abstract class GroupDao extends BaseEntityDao<Group> {
    public Group insert(Group group, Project project) {
        if (group.isNew()) {
            int id = insertGeneratedId(group, project);
            group.setId(id);
        } else {
            insertWitId(group, project);
        }
        return group;
    }


    @SqlUpdate("INSERT INTO \"groups\" (name, type, project) VALUES (:g.name, CAST(:g.type AS GROUP_TYPE), :p.id)")
    @GetGeneratedKeys
    abstract int insertGeneratedId(@BindBean("g") Group group, @BindBean("p") Project project);


    @SqlUpdate("INSERT INTO \"groups\" (id, name, type, project) VALUES (:g.id, :g.name, CAST(:g.type AS GROUP_TYPE), :p.id) ")
    abstract void insertWitId(@BindBean("g") Group group, @BindBean("p") Project project);

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
