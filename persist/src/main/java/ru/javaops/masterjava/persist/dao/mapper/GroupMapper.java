package ru.javaops.masterjava.persist.dao.mapper;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import ru.javaops.masterjava.persist.DBIProvider;
import ru.javaops.masterjava.persist.dao.ProjectDao;
import ru.javaops.masterjava.persist.model.Group;
import ru.javaops.masterjava.persist.model.GroupType;
import ru.javaops.masterjava.persist.model.Project;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupMapper implements ResultSetMapper<Group> {
    @Override
    public Group map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        ProjectDao projectDao = DBIProvider.getDao(ProjectDao.class);
        Project project = projectDao.getById(r.getInt("project"));
        return new Group(
                r.getInt("id"),
                r.getString("name"),
                GroupType.valueOf(r.getString("type")),
                project
        );
    }
}
