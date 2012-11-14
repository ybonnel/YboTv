
package fr.ybo.xmltv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "present",
    "stereo"
})
@XmlRootElement(name = "audio")
public class Audio implements Serializable {

    protected String present;
    protected String stereo;

    /**
     * Gets the value of the present property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPresent() {
        return present;
    }

    /**
     * Sets the value of the present property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPresent(String value) {
        this.present = value;
    }

    /**
     * Gets the value of the stereo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStereo() {
        return stereo;
    }

    /**
     * Sets the value of the stereo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStereo(String value) {
        this.stereo = value;
    }

}
