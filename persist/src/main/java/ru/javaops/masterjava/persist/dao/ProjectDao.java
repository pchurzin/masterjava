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
    @SqlQuery("SELECT * FROM projects WHERE id=:it")
    public abstract Project getById(@Bind int key);

    @Override
    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO projects (name, description) VALUES (:name, :description)")
    protected abstract int insert(@BindBean Project project);

    @Override
    @SqlUpdate("UPDATE projects SET name=:name, description=:description WHERE id=:id")
    protected abstract int update(@BindBean Project city);

    @Override
    @SqlUpdate("TRUNCATE projects CASCADE")
    public abstract void clean();

    @SqlQuery("SELECT * FROM projects LIMIT :it")
    public abstract List<Project> getWithLimit(@Bind int limit);

    @SqlQuery("SELECT * FROM projects WHERE name=:it")
    public abstract Project getByName(@Bind String name);

    @SqlQuery("SELECT * FROM projects WHERE name IN (<names>)")
    public abstract List<Project> getByNames(@BindIn("names") String... names);
}
