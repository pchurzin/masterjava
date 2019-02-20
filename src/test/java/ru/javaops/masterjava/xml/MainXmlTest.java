package ru.javaops.masterjava.xml;

import org.junit.Test;
import ru.javaops.masterjava.xml.MainXml.JaxbImplementation;
import ru.javaops.masterjava.xml.MainXml.StaxImplementation;
import ru.javaops.masterjava.xml.MainXml.UserInfo;

import static org.junit.Assert.assertArrayEquals;

public class MainXmlTest {

    @Test
    public void testJaxb() {
        assertArrayEquals(new String[]{"Admin", "Deleted", "Full Name", "PChurzin"},
                JaxbImplementation.getProjectUsers("topjava").toArray());

        assertArrayEquals(new String[]{"Admin", "PChurzin"},
                JaxbImplementation.getProjectUsers("masterjava").toArray());

        assertArrayEquals(new String[]{},
                JaxbImplementation.getProjectUsers("nosuchproject").toArray());
    }

    @Test
    public void testStax() {
        UserInfo admin = new UserInfo("Admin", "admin@javaops.ru");
        UserInfo pchurzin = new UserInfo("PChurzin", "pchurzin@pchurzin.ru");
        UserInfo deleted = new UserInfo("Deleted", "mail@yandex.ru");
        UserInfo fullName = new UserInfo("Full Name", "gmail@gmail.com");
        assertArrayEquals(new UserInfo[]{admin, deleted, fullName, pchurzin},
                StaxImplementation.getProjectUsers("topjava").toArray());

        assertArrayEquals(new UserInfo[]{admin, pchurzin},
                StaxImplementation.getProjectUsers("masterjava").toArray());

        assertArrayEquals(new UserInfo[]{},
                StaxImplementation.getProjectUsers("nosuchproject").toArray());
    }

}