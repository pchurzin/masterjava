package ru.javaops.masterjava.persist;

import com.google.common.collect.ImmutableList;
import ru.javaops.masterjava.persist.dao.ProjectDao;
import ru.javaops.masterjava.persist.model.Project;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProjectTestData {
    public static Project PROJECT_1;
    public static Project PROJECT_2;
    public static Project PROJECT_3;
    public static Project PROJECT_NO_GROUPS;
    public static List<Project> FIRST2_PROJECTS;

    static {
        PROJECT_1 = new Project("test project 1", "test project 1 description", Collections.singletonList(GroupTestData.GROUP_1));
        PROJECT_2 = new Project("test project 2", "test project 2 description", Arrays.asList(GroupTestData.GROUP_2, GroupTestData.GROUP_3));
        PROJECT_3 = new Project("test project 3", "test project 3 description", GroupTestData.FIRST2_GROUPS);
        PROJECT_NO_GROUPS = new Project("test project 4", "test project 4 description", Collections.EMPTY_LIST);
        FIRST2_PROJECTS = ImmutableList.of(PROJECT_1, PROJECT_2);
    }

    public static void setUp() {
        ProjectDao dao = DBIProvider.getDao(ProjectDao.class);
        dao.clean();
        DBIProvider.getDBI().useTransaction((conn, status) -> {
            FIRST2_PROJECTS.forEach(dao::insert);
            dao.insert(PROJECT_3);
        });
    }

}
