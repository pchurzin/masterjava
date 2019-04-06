package ru.javaops.masterjava.persist;

import com.google.common.collect.ImmutableList;
import ru.javaops.masterjava.persist.dao.GroupDao;
import ru.javaops.masterjava.persist.model.Group;
import ru.javaops.masterjava.persist.model.GroupType;

import java.util.List;

public class GroupTestData {
    public static Group GROUP_1;
    public static Group GROUP_2;
    public static Group GROUP_3;
    public static Group GROUP_4;
    public static List<Group> FIRST2_GROUPS;

    static {
        GROUP_1 = new Group("test group 1", GroupType.CURRENT);
        GROUP_2 = new Group("test group 2", GroupType.FINISHED);
        GROUP_3 = new Group("test group 3", GroupType.REGISTERING);
        GROUP_4 = new Group("test group 4", GroupType.CURRENT);
        FIRST2_GROUPS = ImmutableList.of(GROUP_1, GROUP_2);
    }

    public static void setUp() {
        ProjectTestData.setUp();
        GroupDao dao = DBIProvider.getDao(GroupDao.class);
        dao.clean();
        DBIProvider.getDBI().useTransaction((conn, status) -> {
            FIRST2_GROUPS.forEach(dao::insert);
            dao.insert(GROUP_3);
        });
    }
}
