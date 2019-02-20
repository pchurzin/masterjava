
package ru.javaops.masterjava.xml.schema;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="Cities" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence maxOccurs="unbounded">
 *                   &lt;element name="City" type="{http://javaops.ru}cityType"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Users" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence maxOccurs="unbounded">
 *                   &lt;element name="User" type="{http://javaops.ru}userType"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Projects" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence maxOccurs="unbounded">
 *                   &lt;element name="Project" type="{http://javaops.ru}projectType"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Groups" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence maxOccurs="unbounded">
 *                   &lt;element name="Group" type="{http://javaops.ru}groupType"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "Payload", namespace = "http://javaops.ru")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class Payload {

    @XmlElement(name = "Cities", namespace = "http://javaops.ru")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Payload.Cities cities;
    @XmlElement(name = "Users", namespace = "http://javaops.ru")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Payload.Users users;
    @XmlElement(name = "Projects", namespace = "http://javaops.ru")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Payload.Projects projects;
    @XmlElement(name = "Groups", namespace = "http://javaops.ru")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Payload.Groups groups;

    /**
     * Gets the value of the cities property.
     * 
     * @return
     *     possible object is
     *     {@link Payload.Cities }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Payload.Cities getCities() {
        return cities;
    }

    /**
     * Sets the value of the cities property.
     * 
     * @param value
     *     allowed object is
     *     {@link Payload.Cities }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setCities(Payload.Cities value) {
        this.cities = value;
    }

    /**
     * Gets the value of the users property.
     * 
     * @return
     *     possible object is
     *     {@link Payload.Users }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Payload.Users getUsers() {
        return users;
    }

    /**
     * Sets the value of the users property.
     * 
     * @param value
     *     allowed object is
     *     {@link Payload.Users }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setUsers(Payload.Users value) {
        this.users = value;
    }

    /**
     * Gets the value of the projects property.
     * 
     * @return
     *     possible object is
     *     {@link Payload.Projects }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Payload.Projects getProjects() {
        return projects;
    }

    /**
     * Sets the value of the projects property.
     * 
     * @param value
     *     allowed object is
     *     {@link Payload.Projects }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setProjects(Payload.Projects value) {
        this.projects = value;
    }

    /**
     * Gets the value of the groups property.
     * 
     * @return
     *     possible object is
     *     {@link Payload.Groups }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Payload.Groups getGroups() {
        return groups;
    }

    /**
     * Sets the value of the groups property.
     * 
     * @param value
     *     allowed object is
     *     {@link Payload.Groups }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setGroups(Payload.Groups value) {
        this.groups = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence maxOccurs="unbounded">
     *         &lt;element name="City" type="{http://javaops.ru}cityType"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "city"
    })
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public static class Cities {

        @XmlElement(name = "City", namespace = "http://javaops.ru", required = true)
        @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
        protected List<CityType> city;

        /**
         * Gets the value of the city property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the city property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCity().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CityType }
         * 
         * 
         */
        @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
        public List<CityType> getCity() {
            if (city == null) {
                city = new ArrayList<CityType>();
            }
            return this.city;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence maxOccurs="unbounded">
     *         &lt;element name="Group" type="{http://javaops.ru}groupType"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "group"
    })
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public static class Groups {

        @XmlElement(name = "Group", namespace = "http://javaops.ru", required = true)
        @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
        protected List<GroupType> group;

        /**
         * Gets the value of the group property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the group property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getGroup().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link GroupType }
         * 
         * 
         */
        @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
        public List<GroupType> getGroup() {
            if (group == null) {
                group = new ArrayList<GroupType>();
            }
            return this.group;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence maxOccurs="unbounded">
     *         &lt;element name="Project" type="{http://javaops.ru}projectType"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "project"
    })
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public static class Projects {

        @XmlElement(name = "Project", namespace = "http://javaops.ru", required = true)
        @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
        protected List<ProjectType> project;

        /**
         * Gets the value of the project property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the project property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getProject().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ProjectType }
         * 
         * 
         */
        @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
        public List<ProjectType> getProject() {
            if (project == null) {
                project = new ArrayList<ProjectType>();
            }
            return this.project;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence maxOccurs="unbounded">
     *         &lt;element name="User" type="{http://javaops.ru}userType"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "user"
    })
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public static class Users {

        @XmlElement(name = "User", namespace = "http://javaops.ru", required = true)
        @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
        protected List<UserType> user;

        /**
         * Gets the value of the user property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the user property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getUser().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link UserType }
         * 
         * 
         */
        @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
        public List<UserType> getUser() {
            if (user == null) {
                user = new ArrayList<UserType>();
            }
            return this.user;
        }

    }

}