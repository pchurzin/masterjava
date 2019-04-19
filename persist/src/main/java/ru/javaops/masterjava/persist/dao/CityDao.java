package ru.javaops.masterjava.persist.dao;

import com.bertoncelj.jdbi.entitymapper.EntityMapperFactory;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;
import org.skife.jdbi.v2.unstable.BindIn;
import ru.javaops.masterjava.persist.model.City;

import java.util.List;

@RegisterMapperFactory(EntityMapperFactory.class)
@UseStringTemplate3StatementLocator
public abstract class CityDao extends BaseEntityDao<City> {

    @Override
    @SqlQuery("SELECT * FROM cities WHERE id=:it")
    public abstract City getById(@Bind int key);

    @Override
    @SqlUpdate("TRUNCATE cities CASCADE")
    public abstract void clean();

    @Override
    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO cities (key, title) VALUES (:key, :title) ON CONFLICT DO NOTHING")
    protected abstract int insert(@BindBean City entity);

    @Override
    @SqlUpdate("UPDATE cities SET key=:key, title=:title WHERE id=:id")
    protected abstract int update(@BindBean City city);

    @SqlQuery("SELECT * FROM cities LIMIT :it")
    public abstract List<City> getWithLimit(@Bind int limit);

    @SqlQuery("SELECT * FROM cities WHERE key=:it")
    public abstract City getByKey(@Bind String key);

    @SqlQuery("SELECT * FROM cities WHERE key IN (<keys>)")
    public abstract List<City> getByKeys(@BindIn("keys") String... keys);
}
