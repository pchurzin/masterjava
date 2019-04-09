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
    public User insert(User user) {
        checkCity(user);
        if (user.isNew()) {
            int id = insertGeneratedId(user, user.getCity());
            user.setId(id);
        } else {
            insertWitId(user, user.getCity());
        }
        return user;
    }

    @SqlQuery("SELECT nextval('user_seq')")
    abstract int getNextVal();

    @Transaction
    public int getSeqAndSkip(int step) {
        int id = getNextVal();
//        DBIProvider.getDBI().useHandle(h -> h.execute("ALTER SEQUENCE user_seq RESTART WITH " + (id + step)));
        DBIProvider.getDBI().useHandle(h -> h.execute("SELECT setval('user_seq', " + (id + step - 1) + ")"));
        return id;
    }

    @SqlUpdate("INSERT INTO users (full_name, email, flag, city) " +
            "VALUES (:u.fullName, :u.email, CAST(:u.flag AS USER_FLAG), :c.id) ")
    @GetGeneratedKeys
    abstract int insertGeneratedId(@BindBean("u") User user, @BindBean("c") City city);

    @SqlUpdate("INSERT INTO users (id, full_name, email, flag, city)" +
            " VALUES (:u.id, :u.fullName, :u.email, CAST(:u.flag AS USER_FLAG), :c.id)")
    abstract void insertWitId(@BindBean("u") User user, @BindBean("c") City city);

    //    @SqlQuery("SELECT u.*, c.* FROM users u INNER JOIN cities c ON u.city = c.id ORDER BY u.full_name, u.email LIMIT :it")
    @SqlQuery("SELECT * FROM users ORDER BY full_name, email LIMIT :it")
    public abstract List<User> getWithLimit(@Bind int limit);

    //   http://stackoverflow.com/questions/13223820/postgresql-delete-all-content
    @SqlUpdate("TRUNCATE users")
    @Override
    public abstract void clean();

    //    https://habrahabr.ru/post/264281/
    @SqlBatch("INSERT INTO users (id, full_name, email, flag, city) " +
            "VALUES (:id, :fullName, :email, CAST(:flag AS USER_FLAG), :city)" +
            "ON CONFLICT DO NOTHING")
//            "ON CONFLICT (email) DO UPDATE SET full_name=:fullName, flag=CAST(:flag AS USER_FLAG)")
    public abstract int[] insertBatch(@BindUser List<User> users, @BatchChunkSize int chunkSize);


    public List<String> insertAndGetConflictEmails(List<User> users) {
        int[] result = insertBatch(users, users.size());
        return IntStreamEx.range(0, users.size())
                .filter(i -> result[i] == 0)
                .mapToObj(index -> users.get(index).getEmail())
                .toList();
    }

    void checkCity(User user) {
        if (user.getCity().isNew()) {
            CityDao cityDao = DBIProvider.getDao(CityDao.class);
            cityDao.insert(user.getCity());
        }
    }
}
