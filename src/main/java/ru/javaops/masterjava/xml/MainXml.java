package ru.javaops.masterjava.xml;

import com.google.common.io.Resources;
import ru.javaops.masterjava.xml.schema.*;
import ru.javaops.masterjava.xml.util.JaxbParser;
import ru.javaops.masterjava.xml.util.Schemas;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class MainXml {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage MainXml <ProjectId>");
            return;
        }
        for (String name : getProjectUsers(args[0])) {
            System.out.println(name);
        }

    }

    public static List<String> getProjectUsers(String projectId) {
        Objects.requireNonNull(projectId);
        List<String> result = new ArrayList<>();
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
            return result;
        }

        Set<UserType> projectUsers = getProjectUsers(payload, projectId);
        return projectUsers.stream()
                .map(UserType::getFullName)
                .sorted(String::compareTo).collect(Collectors.toList());
    }

    private static Optional<ProjectType> getProject(Payload payload, String projectId) {
        return payload.getProjects().getProject().stream()
                    .filter(pt -> projectId.equals(pt.getId()))
                    .findFirst();
    }

    private static Set<GroupType> getProjectGroups(Payload payload, ProjectType project) {
        Objects.requireNonNull(payload);
        Objects.requireNonNull(project);
        return payload.getGroups().getGroup().stream()
                .filter(gt -> project.getGroups().getGroup().contains(gt.getId()))
                .collect(Collectors.toSet());
    }

    private static Set<UserType> getGroupUsers(Payload payload, GroupType group) {
        Objects.requireNonNull(payload);
        Objects.requireNonNull(group);
        return payload.getUsers().getUser().stream()
                .filter(ut -> group.getUsers().getUser().contains(ut.getFullName()))
                .collect(Collectors.toSet());
    }

    private static Set<UserType> getProjectUsers(Payload payload, String projectId) {
        Objects.requireNonNull(payload);
        Objects.requireNonNull(projectId);
        Set<UserType> projectUsers = new HashSet<>();

        Optional<ProjectType> project = getProject(payload, projectId);
        if (project.isPresent()) {
            Set<GroupType> projectGroups = getProjectGroups(payload, project.get());
            for (GroupType group : projectGroups) {
                projectUsers.addAll(getGroupUsers(payload, group));
            }
        }
        return projectUsers;
    }
}
