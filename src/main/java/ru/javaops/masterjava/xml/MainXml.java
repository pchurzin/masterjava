package ru.javaops.masterjava.xml;

import com.google.common.io.Resources;
import ru.javaops.masterjava.xml.schema.*;
import ru.javaops.masterjava.xml.util.JaxbParser;
import ru.javaops.masterjava.xml.util.Schemas;
import ru.javaops.masterjava.xml.util.StaxStreamProcessor;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
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
        for (String name : JaxbImplementation.getProjectUsers(args[0])) {
            System.out.println(name);
        }

        for (UserInfo ui : StaxImplementation.getProjectUsers(args[0])) {
            System.out.println(ui.name + "\t" + ui.email);
        }

    }

    static class JaxbImplementation {
        static List<String> getProjectUsers(String projectId) {
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

    static class StaxImplementation {
        private static Map<String, UserInfo> users = new HashMap<>();
        private static Map<String, Set<String>> groupUsers = new HashMap<>();
        private static Map<String, Set<String>> projectGroups = new HashMap<>();

        static List<UserInfo> getProjectUsers(String projectId) {
            parse();

            if (!projectGroups.containsKey(projectId)) {
                return new ArrayList<>();
            }
            Set<UserInfo> userInfos = projectGroups.get(projectId).stream()
                    .flatMap(groupId -> groupUsers.get(groupId).stream())
                    .map(u -> users.get(u))
                    .collect(Collectors.toSet());
            List<UserInfo> result = new ArrayList<>(userInfos);
            result.sort(Comparator.comparing(o -> o.name));
            return result;
        }

        private static void parse() {
            try (InputStream is = Resources.getResource("payload.xml").openStream();
                 StaxStreamProcessor processor = new StaxStreamProcessor(is)) {
                if (processor.doUntil(XMLEvent.START_ELEMENT, "Payload")) {
                    readPayload(processor);
                }
            } catch (XMLStreamException | IOException e) {
                e.printStackTrace();
            }
        }

        private static void readPayload(StaxStreamProcessor processor) throws XMLStreamException {
            while (!processor.isElementEnded("Payload")) {
                processor.next();
                if (processor.getEvent() == XMLEvent.START_ELEMENT) {
                    switch (processor.getElementLocalName()) {
                        case "Users":
                            readUsers(processor);
                            break;
                        case "Groups":
                            readGroups(processor);
                            break;
                        case "Projects":
                            readProjects(processor);
                            break;
                        default:
                            processor.consumeElement();
                    }
                }
            }
            processor.next();
        }

        private static void readProjects(StaxStreamProcessor processor) throws XMLStreamException {
            while (!processor.isElementEnded("Projects")) {
                processor.next();
                if (processor.getEvent() == XMLEvent.START_ELEMENT) {
                    if ("Project".equals(processor.getElementLocalName())) {
                        readProject(processor);
                    } else {
                        processor.consumeElement();
                    }
                }
            }
        }

        private static void readProject(StaxStreamProcessor processor) throws XMLStreamException {
            String id = processor.getAttributeValue("id");
            while (!processor.isElementEnded("Project")) {
                processor.next();
                if (processor.getEvent() == XMLEvent.START_ELEMENT) {
                    if ("Group".equals(processor.getElementLocalName())) {
                        String group = processor.getText();
                        projectGroups.computeIfAbsent(id, k -> new HashSet<>()).add(group);
                    } else if ("Groups".equals(processor.getElementLocalName())) {
                    } else {
                        processor.consumeElement();
                    }
                }
            }
        }

        private static void readGroups(StaxStreamProcessor processor) throws XMLStreamException {
            while (!processor.isElementEnded("Groups")) {
                processor.next();
                if (processor.getEvent() == XMLEvent.START_ELEMENT) {
                    if ("Group".equals(processor.getElementLocalName())) {
                        readGroup(processor);
                    } else {
                        processor.consumeElement();
                    }
                }
            }
        }

        private static void readGroup(StaxStreamProcessor processor) throws XMLStreamException {
            String id = processor.getAttributeValue("id");
            while (!processor.isElementEnded("Group")) {
                processor.next();
                if (processor.getEvent() == XMLEvent.START_ELEMENT) {
                    if ("User".equals(processor.getElementLocalName())) {
                        String user = processor.getText();
                        groupUsers.computeIfAbsent(id, k -> new HashSet<>()).add(user);
                    } else if ("Users".equals(processor.getElementLocalName())) {
                    } else {
                        processor.consumeElement();
                    }
                }
            }
        }

        private static void readUsers(StaxStreamProcessor processor) throws XMLStreamException {
            while (!processor.isElementEnded("Users")) {
                processor.next();
                if (processor.getEvent() == XMLEvent.START_ELEMENT) {
                    if ("User".equals(processor.getElementLocalName())) {
                        readUser(processor);
                    } else {
                        processor.consumeElement();
                    }
                }
            }
        }

        private static void readUser(StaxStreamProcessor processor) throws XMLStreamException {
            String email = processor.getAttributeValue("email");
            String fullName = processor.getElementValue("fullName");
            users.put(fullName, new UserInfo(fullName, email));
            processor.exitElement("User");
        }
    }

    static class UserInfo {
        final String name;
        final String email;

        UserInfo(String name, String email) {
            this.name = name;
            this.email = email;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UserInfo userInfo = (UserInfo) o;
            return Objects.equals(name, userInfo.name) &&
                    Objects.equals(email, userInfo.email);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, email);
        }
    }

}
