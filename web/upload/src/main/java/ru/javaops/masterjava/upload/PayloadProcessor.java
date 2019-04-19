package ru.javaops.masterjava.upload;

import lombok.val;
import ru.javaops.masterjava.persist.model.City;
import ru.javaops.masterjava.xml.util.StaxStreamProcessor;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayloadProcessor {
    private CityProcessor cityProcessor = new CityProcessor();
    private UserProcessor userProcessor = new UserProcessor();

    List<UserProcessor.FailedEmails> process(InputStream is, int chunkSize) throws XMLStreamException, JAXBException {
        val processor = new StaxStreamProcessor(is);

        Map<String, City> cities = new HashMap<>();
        if (processor.startElement("Cities", "Payload")) {
            cities = cityProcessor.process(processor.getReader());
        }
        if (processor.startElement("Users", "Payload")) {
            return userProcessor.process(processor, cities, chunkSize);
        }
        return Collections.emptyList();
    }
}
