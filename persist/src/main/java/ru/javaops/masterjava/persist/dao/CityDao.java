package ru.javaops.masterjava.persist.dao;

import com.bertoncelj.jdbi.entitymapper.EntityMapperFactory;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import ru.javaops.masterjava.persist.model.City;

import java.util.List;

@RegisterMapperFactory(EntityMapperFactory.class)
public abstract class CityDao implements AbstractDao {
    public City insert(City city) {
        if (city.isNew()) {
            int id = insertGeneratedId(city);
            city.setId(id);
        } else {
            insertWitId(city);
        }
        return city;
    }

    @SqlUpdate("INSERT INTO cities (key, title) VALUES (:key, :title)")
    @GetGeneratedKeys
    abstract int insertGeneratedId(@BindBean City city);

    @SqlUpdate("INSERT INTO cities (id, key, title) VALUES (:id, :key, :title) ")
    abstract void insertWitId(@BindBean City city);

    @SqlQuery("SELECT * FROM cities LIMIT :it")
    public abstract List<City> getWithLimit(@Bind int limit);

    @SqlUpdate("TRUNCATE cities CASCADE")
    @Override
    public abstract void clean();
}
