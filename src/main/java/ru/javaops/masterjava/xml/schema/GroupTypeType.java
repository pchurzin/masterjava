
package ru.javaops.masterjava.xml.schema;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for groupTypeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="groupTypeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="registering"/>
 *     &lt;enumeration value="current"/>
 *     &lt;enumeration value="finished"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "groupTypeType", namespace = "http://javaops.ru")
@XmlEnum
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-17T09:23:18+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
public enum GroupTypeType {

    @XmlEnumValue("registering")
    REGISTERING("registering"),
    @XmlEnumValue("current")
    CURRENT("current"),
    @XmlEnumValue("finished")
    FINISHED("finished");
    private final String value;

    GroupTypeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GroupTypeType fromValue(String v) {
        for (GroupTypeType c: GroupTypeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
