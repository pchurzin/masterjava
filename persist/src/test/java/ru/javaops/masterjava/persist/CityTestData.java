package ru.javaops.masterjava.persist;

import com.google.common.collect.ImmutableList;
import ru.javaops.masterjava.persist.model.City;

import java.util.List;

public class CityTestData {
    public static City SPB;
    public static City MSK;
    public static City KIV;
    public static City MNSK;
    public static List<City> FIRST2_CITIES;

    static {
        SPB = new City("sbp", "Санкт-Петербург");
        MSK = new City("msk", "Москва");
        KIV = new City("kiv", "Киев");
        MNSK = new City("mnsk", "Минск");
        FIRST2_CITIES = ImmutableList.of(SPB, MSK);
    }
}
