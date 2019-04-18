package ru.javaops.masterjava.persist;

import com.google.common.collect.ImmutableList;
import ru.javaops.masterjava.persist.dao.UserDao;
import ru.javaops.masterjava.persist.model.User;
import ru.javaops.masterjava.persist.model.UserFlag;

import java.util.List;

public class UserTestData {
    public static User ADMIN;
    public static User DELETED;
    public static User FULL_NAME;
    public static User USER1;
    public static User USER2;
    public static User USER3;
    public static User USER_BAD_CITY;
    public static List<User> FIST5_USERS;

    private static UserDao dao = DBIProvider.getDao(UserDao.class);


    public static void init() {
        ADMIN = new User("Admin", "admin@javaops.ru", UserFlag.superuser, CityTestData.MSK);
        DELETED = new User("Deleted", "deleted@yandex.ru", UserFlag.deleted, CityTestData.KIV);
        FULL_NAME = new User("Full Name", "gmail@gmail.com", UserFlag.active, CityTestData.SPB);
        USER1 = new User("User1", "user1@gmail.com", UserFlag.active, CityTestData.KIV);
        USER2 = new User("User2", "user2@yandex.ru", UserFlag.active, CityTestData.MSK);
        USER3 = new User("User3", "user3@yandex.ru", UserFlag.active, CityTestData.SPB);
        USER_BAD_CITY = new User("User4", "user4@yandex.ru", UserFlag.active, CityTestData.MNSK);
        FIST5_USERS = ImmutableList.of(ADMIN, DELETED, FULL_NAME, USER1, USER2);

        dao.clean();
    }

    public static void setUp() {
        CityTestData.init();
        init();

        DBIProvider.getDBI().useTransaction((conn, status) -> {
            FIST5_USERS.forEach(dao::save);
            dao.save(USER3);
        });
    }
}
