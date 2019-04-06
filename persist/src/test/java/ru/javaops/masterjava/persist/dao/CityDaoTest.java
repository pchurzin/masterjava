package ru.javaops.masterjava.persist.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.javaops.masterjava.persist.CityTestData;
import ru.javaops.masterjava.persist.model.City;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static ru.javaops.masterjava.persist.CityTestData.FIRST2_CITIES;
import static ru.javaops.masterjava.persist.CityTestData.MSK;

public class CityDaoTest extends AbstractDaoTest<CityDao> {
    public CityDaoTest() {
        super(CityDao.class);
    }

    @BeforeClass
    public static void init() throws Exception {
//        UserTestData.init();
    }

    @Before
    public void setUp() {
        CityTestData.setUp();
    }

    @Test
    public void getWithLimit() {
        List<City> cities = dao.getWithLimit(2);
        Assert.assertEquals(FIRST2_CITIES, cities);
    }

    @Test
    public void getByKey() {
        City msk = dao.getByKey("msk");
        Assert.assertEquals(MSK, msk);
    }

    @Test
    public void getByKeys() {
        List<City> cities = dao.getByKeys(new String[]{"msk", "spb"});
        Assert.assertTrue(cities.containsAll(FIRST2_CITIES));

        cities = dao.getByKeys("msk", "spb");
        Assert.assertTrue(cities.containsAll(FIRST2_CITIES));
    }
}
