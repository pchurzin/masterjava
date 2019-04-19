package ru.javaops.masterjava.upload;

import lombok.val;
import ru.javaops.masterjava.persist.DBIProvider;
import ru.javaops.masterjava.persist.dao.CityDao;
import ru.javaops.masterjava.persist.model.City;
import ru.javaops.masterjava.xml.schema.CityType;
import ru.javaops.masterjava.xml.schema.ObjectFactory;
import ru.javaops.masterjava.xml.util.JaxbParser;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CityProcessor {

    private static final JaxbParser jaxbParser = new JaxbParser(ObjectFactory.class);
    private static CityDao cityDao = DBIProvider.getDao(CityDao.class);

    Map<String, City> process(XMLStreamReader reader) throws XMLStreamException, JAXBException {
        Map<String, City> cities = new HashMap<>();
        val unmarshaller = jaxbParser.createUnmarshaller();

        ru.javaops.masterjava.xml.schema.Payload.Cities xmlCities = unmarshaller.unmarshal(reader, ru.javaops.masterjava.xml.schema.Payload.Cities.class);
        for (CityType xmlCity : xmlCities.getCity()) {
            City city = new City(xmlCity.getId(), xmlCity.getValue());
            cityDao.save(city);
            cities.put(city.getKey(), city);
        }
        return cities;
    }
}
