package ru.javaops.masterjava.xml.util;

import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import java.io.*;
import java.util.HashMap;
import java.util.Map;


/**
 * Marshalling/Unmarshalling JAXB helper
 * XML Facade
 */
public class JaxbParser {
    protected Schema schema;
    private JAXBContext context;
    private Map<String, Object> marshallerProps = new HashMap<>();

    public JaxbParser(Class... classesToBeBound) {
        try {
            context = JAXBContext.newInstance(classesToBeBound);
        } catch (JAXBException e) {
            throw new IllegalArgumentException(e);
        }
    }

    //    http://stackoverflow.com/questions/30643802/what-is-jaxbcontext-newinstancestring-contextpath
    public JaxbParser(String context) {
        try {
            this.context = JAXBContext.newInstance(context);
        } catch (JAXBException e) {
            throw new IllegalArgumentException(e);
        }
    }

    // Unmarshaller
    public <T> T unmarshal(InputStream is) throws JAXBException {
        JaxbUnmarshaller jaxbUnmarshaller = getJaxbUnmarshaller();
        return (T) jaxbUnmarshaller.unmarshal(is);
    }

    public <T> T unmarshal(Reader reader) throws JAXBException {
        JaxbUnmarshaller jaxbUnmarshaller = new JaxbUnmarshaller(context);
        return (T) jaxbUnmarshaller.unmarshal(reader);
    }

    public <T> T unmarshal(String str) throws JAXBException {
        JaxbUnmarshaller jaxbUnmarshaller = new JaxbUnmarshaller(context);
        return (T) jaxbUnmarshaller.unmarshal(str);
    }

    public <T> T unmarshal(XMLStreamReader reader, Class<T> elementClass) throws JAXBException {
        JaxbUnmarshaller jaxbUnmarshaller = new JaxbUnmarshaller(context);
        return jaxbUnmarshaller.unmarshal(reader, elementClass);
    }

    private JaxbUnmarshaller getJaxbUnmarshaller() throws JAXBException {
        JaxbUnmarshaller jaxbUnmarshaller = new JaxbUnmarshaller(context);
        if (schema != null) {
            jaxbUnmarshaller.setSchema(schema);
        }
        return jaxbUnmarshaller;
    }


    // Marshaller

    public String marshal(Object instance) throws JAXBException {
        JaxbMarshaller jaxbMarshaller = getJaxbMarshaller();
        return jaxbMarshaller.marshal(instance);
    }

    public void marshal(Object instance, Writer writer) throws JAXBException {
        JaxbMarshaller jaxbMarshaller = getJaxbMarshaller();
        jaxbMarshaller.marshal(instance, writer);
    }

    private JaxbMarshaller getJaxbMarshaller() throws JAXBException {
        JaxbMarshaller jaxbMarshaller = new JaxbMarshaller(context);
        if (schema != null) {
            jaxbMarshaller.setSchema(schema);
        }
        return jaxbMarshaller;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    public void validate(String str) throws IOException, SAXException {
        validate(new StringReader(str));
    }

    public void validate(Reader reader) throws IOException, SAXException {
        schema.newValidator().validate(new StreamSource(reader));
    }
}
