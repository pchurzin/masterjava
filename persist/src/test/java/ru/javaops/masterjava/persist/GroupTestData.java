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

    private static GroupDao dao = DBIProvider.getDao(GroupDao.class);

    public static void init() {
        GROUP_1 = new Group("test group 1", GroupType.CURRENT, ProjectTestData.PROJECT_1);
        GROUP_2 = new Group("test group 2", GroupType.FINISHED, ProjectTestData.PROJECT_2);
        GROUP_3 = new Group("test group 3", GroupType.REGISTERING, ProjectTestData.PROJECT_3);
        GROUP_4 = new Group("test group 4", GroupType.CURRENT, ProjectTestData.PROJECT_1);
        FIRST2_GROUPS = ImmutableList.of(GROUP_1, GROUP_2);
        dao.clean();
    }

    public static void setUp() {
        ProjectTestData.init();
        init();

        dao.save(GROUP_1);
        dao.save(GROUP_2);
        dao.save(GROUP_3);
    }
}



