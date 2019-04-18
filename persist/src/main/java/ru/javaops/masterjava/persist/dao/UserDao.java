package ru.javaops.masterjava.persist.dao;

import one.util.streamex.IntStreamEx;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.BatchChunkSize;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import ru.javaops.masterjava.persist.DBIProvider;
import ru.javaops.masterjava.persist.dao.bind.BindUser;
import ru.javaops.masterjava.persist.dao.mapper.UserMapper;
import ru.javaops.masterjava.persist.model.City;
import ru.javaops.masterjava.persist.model.User;

import java.util.List;

//@RegisterMapperFactory(EntityMapperFactory.class)
@RegisterMapper(UserMapper.class)
public abstract class UserDao extends BaseEntityDao<User> {

    @Override
    @SqlQuery("SELECT * FROM users WHERE id=:it")
    public abstract User getById(@Bind int key);

    //   http://stackoverflow.com/questions/13223820/postgresql-delete-all-content
    @Override
    @SqlUpdate("TRUNCATE users")
    public abstract void clean();

    @Override
    protected int insert(User user) {
        return insert(user, user.getCity());
    }

    @Override
    protected int update(User user) {
        return update(user, user.getCity());
    }

    @Override
    protected void saveDependents(User user) {
        if (user.getCity().isNew()) {
            CityDao cityDao = DBIProvider.getDao(CityDao.class);
            cityDao.save(user.getCity());
        }
    }

    @SqlQuery("SELECT nextval('user_seq')")
    abstract int getNextVal();

    @Transaction
    public int getSeqAndSkip(int step) {
        int id = getNextVal();
        DBIProvider.getDBI().useHandle(h -> h.execute("SELECT setval('user_seq', " + (id + step - 1) + ")"));
        return id;
    }

    @SqlUpdate("INSERT INTO users (full_name, email, flag, city) VALUES (:u.fullName, :u.email, CAST(:u.flag AS USER_FLAG), :c.id) ")
    @GetGeneratedKeys
    abstract int insert(@BindBean("u") User user, @BindBean("c") City city);

    @SqlUpdate("UPDATE users (full_name, email, flag, city) VALUES (:u.fullName, :u.email, CAST(:u.flag AS USER_FLAG), :c.id) WHERE id=:u.id")
    abstract int update(@BindBean("u") User user, @BindBean("c") City city);

    @SqlQuery("SELECT * FROM users ORDER BY full_name, email LIMIT :it")
    public abstract List<User> getWithLimit(@Bind int limit);

    //    https://habrahabr.ru/post/264281/
    @SqlBatch("INSERT INTO users (id, full_name, email, flag, city) " +
            "VALUES (:id, :fullName, :email, CAST(:flag AS USER_FLAG), :city)" +
            "ON CONFLICT DO NOTHING")
    public abstract int[] insertBatch(@BindUser List<User> users, @BatchChunkSize int chunkSize);


    public List<String> insertAndGetConflictEmails(List<User> users) {
        int[] result = insertBatch(users, users.size());
        return IntStreamEx.range(0, users.size())
                .filter(i -> result[i] == 0)
                .mapToObj(index -> users.get(index).getEmail())
                .toList();
    }
}
