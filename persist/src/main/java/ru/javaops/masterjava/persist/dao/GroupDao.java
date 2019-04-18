package ru.javaops.masterjava.persist.dao;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;
import org.skife.jdbi.v2.unstable.BindIn;
import ru.javaops.masterjava.persist.DBIProvider;
import ru.javaops.masterjava.persist.PersistException;
import ru.javaops.masterjava.persist.dao.mapper.GroupMapper;
import ru.javaops.masterjava.persist.model.Group;
import ru.javaops.masterjava.persist.model.Project;

import java.util.List;

@RegisterMapper(GroupMapper.class)
@UseStringTemplate3StatementLocator
public abstract class GroupDao extends BaseEntityDao<Group> {

    @Override
    @SqlQuery("SELECT * FROM \"groups\" WHERE id=:it")
    public abstract Group getById(@Bind int key);

    @Override
    @SqlUpdate("TRUNCATE \"groups\"")
    public abstract void clean();

    @Override
    protected int insert(Group entity) {
        return insert(entity, entity.getProject());
    }

    @Override
    protected int update(@BindBean Group group) {
        throw new PersistException("You must use update(Group, Project)");
    }

    @Override
    protected void saveDependents(Group entity) {
        if (entity.getProject().isNew()) {
            ProjectDao projectDao = DBIProvider.getDao(ProjectDao.class);
            projectDao.save(entity.getProject());
        }
    }

    @SqlUpdate("INSERT INTO \"groups\" (name, type, project) VALUES (:g.name, CAST(:g.type AS GROUP_TYPE), :p.id)")
    @GetGeneratedKeys
    abstract int insert(@BindBean("g") Group group, @BindBean("p") Project project);


    @SqlUpdate("UPDATE \"groups\" (name, type, project) VALUES (:g.name, CAST(:g.type AS GROUP_TYPE), :p.id) WHERE id=:g.id")
    protected abstract int update(@BindBean("g") Group group, @BindBean("p") Project project);

    @SqlBatch("INSERT INTO \"groups\" (id, name, type, project) VALUES (:g.id, :g.name, CAST(:g.type AS GROUP_TYPE), :p.id)")
    public abstract int[] insertBatch(@BindBean("g") List<Group> groups, @BindBean("p") Project project);


    @SqlQuery("SELECT * FROM \"groups\" LIMIT :it")
    public abstract List<Group> getWithLimit(@Bind int limit);


    @SqlQuery("SELECT * FROM \"groups\" WHERE name=:it")
    public abstract Group getByName(@Bind String name);

    @SqlQuery("SELECT * FROM \"groups\" WHERE name IN (<names>)")
    public abstract List<Group> getByNames(@BindIn("names") String... keys);
}
