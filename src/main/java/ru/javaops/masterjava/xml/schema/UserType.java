
package ru.javaops.masterjava.xml.schema;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for userType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="userType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fullName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="flag" use="required" type="{http://javaops.ru}flagType" />
 *       &lt;attribute name="city" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="email" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userType", namespace = "http://javaops.ru", propOrder = {
    "fullName"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class UserType {

    @XmlElement(namespace = "http://javaops.ru", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String fullName;
    @XmlAttribute(name = "flag", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected FlagType flag;
    @XmlAttribute(name = "city", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String city;
    @XmlAttribute(name = "email", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String email;

    /**
     * Gets the value of the fullName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the value of the fullName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setFullName(String value) {
        this.fullName = value;
    }

    /**
     * Gets the value of the flag property.
     * 
     * @return
     *     possible object is
     *     {@link FlagType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public FlagType getFlag() {
        return flag;
    }

    /**
     * Sets the value of the flag property.
     * 
     * @param value
     *     allowed object is
     *     {@link FlagType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setFlag(FlagType value) {
        this.flag = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setEmail(String value) {
        this.email = value;
    }

}
