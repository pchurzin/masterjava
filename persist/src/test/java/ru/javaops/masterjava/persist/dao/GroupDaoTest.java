package ru.javaops.masterjava.persist.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javaops.masterjava.persist.GroupTestData;
import ru.javaops.masterjava.persist.model.Group;

import java.util.List;

import static ru.javaops.masterjava.persist.GroupTestData.FIRST2_GROUPS;
import static ru.javaops.masterjava.persist.GroupTestData.GROUP_1;

public class GroupDaoTest extends AbstractDaoTest<GroupDao> {
    public GroupDaoTest() {
        super(GroupDao.class);
    }

    @Before
    public void setUp() {
        GroupTestData.setUp();
    }

    @Test
    public void getWithLimit() {
        List<Group> groups = dao.getWithLimit(2);
        Assert.assertEquals(FIRST2_GROUPS, groups);
    }

    @Test
    public void getByKey() {
        Group group = dao.getByName("test group 1");
        Assert.assertEquals(GROUP_1, group);
    }

    @Test
    public void getByKeys() {
        List<Group> groups = dao.getByNames("test group 1", "test group 2");
        Assert.assertTrue(groups.containsAll(FIRST2_GROUPS));
    }
}
