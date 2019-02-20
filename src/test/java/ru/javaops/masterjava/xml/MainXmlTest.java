package ru.javaops.masterjava.xml;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static ru.javaops.masterjava.xml.MainXml.JaxbImplementation.getProjectUsers;

public class MainXmlTest {

    @Test
    public void testJaxb() {
        assertArrayEquals(new String[]{"Admin", "Deleted", "Full Name", "PChurzin"},
                getProjectUsers("topjava").toArray());

        assertArrayEquals(new String[]{"Admin", "PChurzin"},
                getProjectUsers("masterjava").toArray());

        assertArrayEquals(new String[]{},
                getProjectUsers("nosuchproject").toArray());
    }
}