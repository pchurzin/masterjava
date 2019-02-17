package ru.javaops.masterjava.xml;

import com.google.common.io.Resources;
import ru.javaops.masterjava.xml.schema.ObjectFactory;
import ru.javaops.masterjava.xml.schema.Payload;
import ru.javaops.masterjava.xml.schema.ProjectType;
import ru.javaops.masterjava.xml.util.JaxbParser;
import ru.javaops.masterjava.xml.util.Schemas;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MainXml {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage MainXml <ProjectId>");
            return;
        }

        JaxbParser parser = new JaxbParser(ObjectFactory.class);
        parser.setSchema(Schemas.ofClasspath("payload.xsd"));
        Payload payload = null;
        try (InputStream is = Resources.getResource("payload.xml").openStream()) {
            payload = parser.unmarshal(is);
        } catch (IOException | JAXBException e) {
            //todo add logging
            e.printStackTrace();
        }

        if (payload == null) {
            System.out.println("Can't load XML");
            return;
        }

        Optional<ProjectType> projectType = payload.getProjects().getProject().stream()
                .filter(pt -> args[0].equals(pt.getId()))
                .findFirst();
        if (!projectType.isPresent()) {
            System.out.println("No such project with id '" + args[0] + "'");
            return;
        }
        List<String> projectGroupsIds = projectType.get().getGroups().getGroup();
        payload.getGroups().getGroup().stream()
                .filter(gt -> projectGroupsIds.contains(gt.getId()))
                .flatMap(gt -> gt.getUsers().getUser().stream())
                .collect(Collectors.toSet()).stream()
                .sorted()
                .forEach(System.out::println);
    }
}
