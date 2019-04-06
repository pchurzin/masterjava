package ru.javaops.masterjava.persist.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.javaops.masterjava.persist.ProjectTestData;
import ru.javaops.masterjava.persist.model.Project;

import java.util.List;

import static ru.javaops.masterjava.persist.ProjectTestData.FIRST2_PROJECTS;
import static ru.javaops.masterjava.persist.ProjectTestData.PROJECT_1;

public class ProjectDaoTest extends AbstractDaoTest<ProjectDao> {
    public ProjectDaoTest() {
        super(ProjectDao.class);
    }

    @BeforeClass
    public static void init() throws Exception {
//        UserTestData.init();
    }

    @Before
    public void setUp() {
        ProjectTestData.setUp();
    }

    @Test
    public void getWithLimit() {
        List<Project> projects = dao.getWithLimit(2);
        Assert.assertEquals(FIRST2_PROJECTS, projects);
    }

    @Test
    public void getByName() {
        Project project1 = dao.getByName("test project 1");
        Assert.assertEquals(PROJECT_1, project1);
    }

    @Test
    public void getByNames() {
        List<Project> projects = dao.getByNames("test project 1", "test project 2");
        Assert.assertTrue(projects.containsAll(FIRST2_PROJECTS));
    }

}
