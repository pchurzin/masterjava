
package ru.javaops.masterjava.xml.schema;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.javaops.masterjava.xml.schema package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.javaops.masterjava.xml.schema
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Payload }
     * 
     */
    public Payload createPayload() {
        return new Payload();
    }

    /**
     * Create an instance of {@link ProjectType }
     * 
     */
    public ProjectType createProjectType() {
        return new ProjectType();
    }

    /**
     * Create an instance of {@link GroupType }
     * 
     */
    public GroupType createGroupType() {
        return new GroupType();
    }

    /**
     * Create an instance of {@link Payload.Cities }
     * 
     */
    public Payload.Cities createPayloadCities() {
        return new Payload.Cities();
    }

    /**
     * Create an instance of {@link Payload.Users }
     * 
     */
    public Payload.Users createPayloadUsers() {
        return new Payload.Users();
    }

    /**
     * Create an instance of {@link Payload.Projects }
     * 
     */
    public Payload.Projects createPayloadProjects() {
        return new Payload.Projects();
    }

    /**
     * Create an instance of {@link Payload.Groups }
     * 
     */
    public Payload.Groups createPayloadGroups() {
        return new Payload.Groups();
    }

    /**
     * Create an instance of {@link CityType }
     * 
     */
    public CityType createCityType() {
        return new CityType();
    }

    /**
     * Create an instance of {@link UserType }
     * 
     */
    public UserType createUserType() {
        return new UserType();
    }

    /**
     * Create an instance of {@link ProjectType.Groups }
     * 
     */
    public ProjectType.Groups createProjectTypeGroups() {
        return new ProjectType.Groups();
    }

    /**
     * Create an instance of {@link GroupType.Users }
     * 
     */
    public GroupType.Users createGroupTypeUsers() {
        return new GroupType.Users();
    }

}
