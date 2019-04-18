package ru.javaops.masterjava.persist;

import com.google.common.collect.ImmutableList;
import ru.javaops.masterjava.persist.dao.ProjectDao;
import ru.javaops.masterjava.persist.model.Project;

import java.util.List;

public class ProjectTestData {
    public static Project PROJECT_1;
    public static Project PROJECT_2;
    public static Project PROJECT_3;
    public static List<Project> FIRST2_PROJECTS;

    private static ProjectDao dao = DBIProvider.getDao(ProjectDao.class);

    public static void init() {
        PROJECT_1 = new Project("test project 1", "test project 1 description");
        PROJECT_2 = new Project("test project 2", "test project 2 description");
        PROJECT_3 = new Project("test project 3", "test project 3 description");
        FIRST2_PROJECTS = ImmutableList.of(PROJECT_1, PROJECT_2);

        dao.clean();
    }

    public static void setUp() {
        init();

        DBIProvider.getDBI().useTransaction((conn, status) -> {
            FIRST2_PROJECTS.forEach(dao::save);
            dao.save(PROJECT_3);
        });
    }
}
