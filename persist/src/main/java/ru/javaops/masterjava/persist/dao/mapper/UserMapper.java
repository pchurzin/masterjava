package ru.javaops.masterjava.persist.dao.mapper;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import ru.javaops.masterjava.persist.DBIProvider;
import ru.javaops.masterjava.persist.dao.CityDao;
import ru.javaops.masterjava.persist.model.City;
import ru.javaops.masterjava.persist.model.User;
import ru.javaops.masterjava.persist.model.UserFlag;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ResultSetMapper<User> {
    @Override
    public User map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        CityDao cityDao = DBIProvider.getDao(CityDao.class);
        City city = cityDao.getById(r.getInt("city"));
        return new User(
                r.getInt("id"),
                r.getString("full_name"),
                r.getString("email"),
                UserFlag.valueOf(r.getString("flag")),
                city);
    }
}
