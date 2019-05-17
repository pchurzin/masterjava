package ru.javaops.masterjava.upload;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import ru.javaops.masterjava.persist.DBIProvider;
import ru.javaops.masterjava.persist.dao.ProjectDao;
import ru.javaops.masterjava.persist.model.Project;
import ru.javaops.masterjava.xml.util.StaxStreamProcessor;

import javax.xml.stream.XMLStreamException;
import java.util.Map;

@Slf4j
public class ProjectProcessor {
    private final ProjectDao projectDao = DBIProvider.getDao(ProjectDao.class);
    private final GroupProcessor groupProcessor = new GroupProcessor();

    public Map<String, Project> process(StaxStreamProcessor processor) throws XMLStreamException {
        val map = projectDao.getAsMap();

        while (processor.startElement("Project", "Projects")) {
            val name = processor.getAttribute("name");
            val description = processor.getElementValue("description");
            if (!map.containsKey(name)) {
                Project project = new Project(name, description);
                log.info("Insert project " + project);
                int projectId = projectDao.insertGeneratedId(project);
                groupProcessor.process(processor, projectId);
            }
        }
        return projectDao.getAsMap();
    }
}
