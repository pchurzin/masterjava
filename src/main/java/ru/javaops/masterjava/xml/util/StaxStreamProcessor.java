package ru.javaops.masterjava.xml.util;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;

public class StaxStreamProcessor implements AutoCloseable {
    private static final XMLInputFactory FACTORY = XMLInputFactory.newInstance();

    private final XMLStreamReader reader;

    private int currentEvent;

    public StaxStreamProcessor(InputStream is) throws XMLStreamException {
        reader = FACTORY.createXMLStreamReader(is);
    }

    XMLStreamReader getReader() {
        return reader;
    }

    public boolean doUntil(int stopEvent, String value) throws XMLStreamException {
        while (hasNext()) {
            next();
            if (currentEvent == stopEvent) {
                if (value.equals(getValue(currentEvent))) {
                    return true;
                }
            }
        }
        return false;
    }

    private String getValue(int event) {
        return (event == XMLEvent.CHARACTERS) ? reader.getText() : reader.getLocalName();
    }

    public String getElementValue(String element) throws XMLStreamException {
        return doUntil(XMLEvent.START_ELEMENT, element) ? reader.getElementText() : null;
    }

    public String getText() throws XMLStreamException {
        return reader.getElementText();
    }

    public String getAttributeValue(String name) {
        for (int i = 0; i < reader.getAttributeCount(); i++) {
            if (reader.getAttributeLocalName(i).equals(name)) {
                return reader.getAttributeValue(i);
            }
        }
        return null;
    }

    public void exitElement(String element) throws XMLStreamException {
        doUntil(XMLEvent.END_ELEMENT, element);
    }

    public void consumeElement() throws XMLStreamException {
        String elementName = reader.getLocalName();
        doUntil(XMLEvent.END_ELEMENT, elementName);
    }

    private boolean hasNext() throws XMLStreamException {
        return reader.hasNext();
    }

    public void next() throws XMLStreamException {
        currentEvent = reader.next();
    }

    public String getElementLocalName() {
        return reader.hasName() ? reader.getLocalName() : null;
    }

    public boolean isElementEnded(String name) {
        return currentEvent == XMLEvent.END_ELEMENT && getElementLocalName().equals(name);
    }

    public int getEvent() {
        return currentEvent;
    }

    @Override
    public void close() {
        if (reader != null) {
            try {
                reader.close();
            } catch (XMLStreamException e) {
                // empty
            }
        }
    }
}
