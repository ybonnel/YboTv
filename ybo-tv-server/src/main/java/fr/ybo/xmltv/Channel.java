
package fr.ybo.xmltv;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "displayName",
    "icon",
    "url"
})
@XmlRootElement(name = "channel")
public class Channel implements Serializable {

    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String id;
    @XmlElement(name = "display-name", required = true)
    @JsonIgnore
    protected List<DisplayName> displayName;
    @JsonIgnore
    protected List<Icon> icon;
    @JsonIgnore
    protected List<Url> url;

    private transient Programme currentProgramme;

    public Programme getCurrentProgramme() {
        return currentProgramme;
    }

    public void setCurrentProgramme(Programme currentProgramme) {
        this.currentProgramme = currentProgramme;
    }

    private final static Map<String, String> mapChaineLogo = new HashMap<String, String>(){{
        put("1", "tf1.gif");
        put("2", "france2.gif");
        put("3", "france3.gif");
        put("4", "canal.gif");
        put("5", "france5.gif");
        put("6", "m6.gif");
        put("7", "arte.gif");
        put("8", "d8.png");
        put("9", "w9.gif");
        put("10", "tmc.gif");
        put("11", "nt1.png");
        put("12", "nrj12.gif");
        put("13", "lachaineparlementaire.gif");
        put("14", "france4.gif");
        put("15", "bfmtv.gif");
        put("16", "itele.gif");
        put("17", "d17.png");
        put("18", "gulli.gif");
        put("999", "franceo.gif");
    }};

    @JsonProperty("icon")
    public String getOneIcon() {
        return mapChaineLogo.get(id);
    }

    @JsonProperty("displayName")
    public String getOneDisplayName() {
        DisplayName oneDisplayName = Iterables.getFirst(displayName, null);
        if (oneDisplayName == null) {
            return null;
        }
        return oneDisplayName.getvalue();
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the displayName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the displayName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDisplayName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DisplayName }
     * 
     * 
     */
    public List<DisplayName> getDisplayName() {
        if (displayName == null) {
            displayName = new ArrayList<DisplayName>();
        }
        return this.displayName;
    }

    /**
     * Gets the value of the icon property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the icon property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIcon().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Icon }
     * 
     * 
     */
    public List<Icon> getIcon() {
        if (icon == null) {
            icon = new ArrayList<Icon>();
        }
        return this.icon;
    }

    /**
     * Gets the value of the url property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the url property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUrl().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Url }
     * 
     * 
     */
    public List<Url> getUrl() {
        if (url == null) {
            url = new ArrayList<Url>();
        }
        return this.url;
    }

}
