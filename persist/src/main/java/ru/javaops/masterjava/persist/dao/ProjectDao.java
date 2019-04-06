package ru.javaops.masterjava.persist.dao;

import com.bertoncelj.jdbi.entitymapper.EntityMapperFactory;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;
import org.skife.jdbi.v2.unstable.BindIn;
import ru.javaops.masterjava.persist.model.Project;

import java.util.List;

@RegisterMapperFactory(EntityMapperFactory.class)
@UseStringTemplate3StatementLocator
public abstract class ProjectDao extends BaseEntityDao<Project> {
    @Override
    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO projects (name, description) VALUES (:name, :description)")
    abstract int insertGeneratedId(@BindBean Project project);

    @Override
    @SqlUpdate("INSERT INTO projects (id, name, description) VALUES (:id, :name, :description) ")
    abstract void insertWitId(@BindBean Project project);

    @SqlQuery("SELECT * FROM projects LIMIT :it")
    public abstract List<Project> getWithLimit(@Bind int limit);

    @SqlQuery("SELECT * FROM projects WHERE id=:it")
    public abstract Project getById(@Bind int key);

    @SqlQuery("SELECT * FROM projects WHERE name=:it")
    public abstract Project getByName(@Bind String name);

    @SqlQuery("SELECT * FROM projects WHERE name IN (<names>)")
    public abstract List<Project> getByNames(@BindIn("names") String... names);

    @SqlUpdate("TRUNCATE projects CASCADE")
    @Override
    public abstract void clean();

    @SqlUpdate("UPDATE projects SET name=:name, description=:description WHERE id=:id")
    public abstract int update(@BindBean Project city);

}
