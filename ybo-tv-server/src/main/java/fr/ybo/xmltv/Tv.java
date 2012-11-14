
package fr.ybo.xmltv;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "channel",
    "programme"
})
@XmlRootElement(name = "tv")
public class Tv implements Serializable {

    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String date;
    @XmlAttribute(name = "source-info-url")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String sourceInfoUrl;
    @XmlAttribute(name = "source-info-name")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String sourceInfoName;
    @XmlAttribute(name = "source-data-url")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String sourceDataUrl;
    @XmlAttribute(name = "generator-info-name")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String generatorInfoName;
    @XmlAttribute(name = "generator-info-url")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String generatorInfoUrl;
    protected List<Channel> channel;
    protected List<Programme> programme;

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(String value) {
        this.date = value;
    }

    /**
     * Gets the value of the sourceInfoUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceInfoUrl() {
        return sourceInfoUrl;
    }

    /**
     * Sets the value of the sourceInfoUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceInfoUrl(String value) {
        this.sourceInfoUrl = value;
    }

    /**
     * Gets the value of the sourceInfoName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceInfoName() {
        return sourceInfoName;
    }

    /**
     * Sets the value of the sourceInfoName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceInfoName(String value) {
        this.sourceInfoName = value;
    }

    /**
     * Gets the value of the sourceDataUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceDataUrl() {
        return sourceDataUrl;
    }

    /**
     * Sets the value of the sourceDataUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceDataUrl(String value) {
        this.sourceDataUrl = value;
    }

    /**
     * Gets the value of the generatorInfoName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeneratorInfoName() {
        return generatorInfoName;
    }

    /**
     * Sets the value of the generatorInfoName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeneratorInfoName(String value) {
        this.generatorInfoName = value;
    }

    /**
     * Gets the value of the generatorInfoUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeneratorInfoUrl() {
        return generatorInfoUrl;
    }

    /**
     * Sets the value of the generatorInfoUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeneratorInfoUrl(String value) {
        this.generatorInfoUrl = value;
    }

    /**
     * Gets the value of the channel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the channel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChannel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Channel }
     * 
     * 
     */
    public List<Channel> getChannel() {
        if (channel == null) {
            channel = new ArrayList<Channel>();
        }
        return this.channel;
    }

    /**
     * Gets the value of the programme property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the programme property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProgramme().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Programme }
     * 
     * 
     */
    public List<Programme> getProgramme() {
        if (programme == null) {
            programme = new ArrayList<Programme>();
        }
        return this.programme;
    }

}
