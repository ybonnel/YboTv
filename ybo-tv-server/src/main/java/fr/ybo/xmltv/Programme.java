
package fr.ybo.xmltv;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.io.Serializable;
import java.util.ArrayList;
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
    "title",
    "subTitle",
    "desc",
    "credits",
    "date",
    "category",
    "language",
    "origLanguage",
    "length",
    "icon",
    "url",
    "country",
    "episodeNum",
    "video",
    "audio",
    "previouslyShown",
    "premiere",
    "lastChance",
    "_new",
    "subtitles",
    "rating",
    "starRating",
    "review"
})
@XmlRootElement(name = "programme")
public class Programme implements Serializable {
    @JsonProperty("id")
    public String getId() {
        return start + channel;
    }

    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String start;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String stop;
    @XmlAttribute(name = "pdc-start")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String pdcStart;
    @XmlAttribute(name = "vps-start")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String vpsStart;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String showview;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String videoplus;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String channel;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @JsonIgnore
    protected String clumpidx;
    @XmlElement(required = true)
    @JsonIgnore
    protected List<Title> title;
    @XmlElement(name = "sub-title")
    @JsonIgnore
    protected List<SubTitle> subTitle;
    @JsonIgnore
    protected List<Desc> desc;
    protected Credits credits;
    protected String date;
    @JsonIgnore
    protected List<Category> category;
    protected Language language;
    @XmlElement(name = "orig-language")
    protected OrigLanguage origLanguage;
    protected Length length;
    @JsonIgnore
    protected List<Icon> icon;
    protected List<Url> url;
    protected List<Country> country;
    @XmlElement(name = "episode-num")
    @JsonIgnore
    protected List<EpisodeNum> episodeNum;
    protected Video video;
    protected Audio audio;
    @XmlElement(name = "previously-shown")
    protected PreviouslyShown previouslyShown;
    protected Premiere premiere;
    @XmlElement(name = "last-chance")
    protected LastChance lastChance;
    @XmlElement(name = "new")
    protected New _new;
    protected List<Subtitles> subtitles;
    @JsonIgnore
    protected List<Rating> rating;
    @XmlElement(name = "star-rating")
    @JsonIgnore
    protected List<StarRating> starRating;
    protected List<Review> review;


    @JsonProperty("title")
    public String getOneTitle() {
        return Optional.fromNullable(Iterables.getFirst(getTitle(), null)).transform(new Function<Title, String>() {
            @Override
            public String apply(Title input) {
                return input.getvalue();
            }
        }).orNull();
    }

    @JsonProperty("subTitle")
    public String getOneSubTitle() {
        return Optional.fromNullable(Iterables.getFirst(getSubTitle(), null)).transform(new Function<SubTitle, String>() {
            @Override
            public String apply(SubTitle input) {
                return input.getvalue();
            }
        }).orNull();
    }

    @JsonProperty("desc")
    public String getOneDesc() {
        return Optional.fromNullable(Iterables.getFirst(getDesc(), null)).transform(new Function<Desc, String>() {
            @Override
            public String apply(Desc input) {
                return input.getvalue();
            }
        }).orNull();
    }

    @JsonProperty("categories")
    public List<String> getCategories() {
        return Lists.transform(getCategory(), new Function<Category, String>() {
            @Override
            public String apply(Category input) {
                return input.getvalue();
            }
        });
    }

    @JsonProperty("ratings")
    public Map<String, String> getRatings() {
        return Maps.transformValues(Maps.uniqueIndex(getRating(), new Function<Rating, String>() {
            @Override
            public String apply(Rating input) {
                return input.getSystem();
            }
        }), new Function<Rating, String>() {
            @Override
            public String apply(Rating input) {
                return input.getValue();
            }
        });
    }

    @JsonProperty("episodeNum")
    public String getOneEpisodeNum() {
        return Optional.fromNullable(Iterables.getFirst(getEpisodeNum(), null)).transform(new Function<EpisodeNum, String>() {
            @Override
            public String apply(EpisodeNum input) {
                return input.getvalue();
            }
        }).orNull();
    }

    @JsonProperty("starRating")
    public String getOneStarRating() {
        return Optional.fromNullable(Iterables.getFirst(getStarRating(), null)).transform(new Function<StarRating, String>() {
            @Override
            public String apply(StarRating input) {
                return input.getValue();
            }
        }).orNull();
    }

    @JsonProperty("icon")
    public String getOneIcon() {
        return Optional.fromNullable(Iterables.getFirst(getIcon(), null)).transform(new Function<Icon, String>() {
            @Override
            public String apply(Icon input) {
                return input.getSrc();
            }
        }).orNull();
    }

    /**
     * Gets the value of the start property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStart() {
        return start;
    }

    /**
     * Sets the value of the start property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStart(String value) {
        this.start = value;
    }

    /**
     * Gets the value of the stop property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStop() {
        return stop;
    }

    /**
     * Sets the value of the stop property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStop(String value) {
        this.stop = value;
    }

    /**
     * Gets the value of the pdcStart property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPdcStart() {
        return pdcStart;
    }

    /**
     * Sets the value of the pdcStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPdcStart(String value) {
        this.pdcStart = value;
    }

    /**
     * Gets the value of the vpsStart property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVpsStart() {
        return vpsStart;
    }

    /**
     * Sets the value of the vpsStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVpsStart(String value) {
        this.vpsStart = value;
    }

    /**
     * Gets the value of the showview property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShowview() {
        return showview;
    }

    /**
     * Sets the value of the showview property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShowview(String value) {
        this.showview = value;
    }

    /**
     * Gets the value of the videoplus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVideoplus() {
        return videoplus;
    }

    /**
     * Sets the value of the videoplus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVideoplus(String value) {
        this.videoplus = value;
    }

    /**
     * Gets the value of the channel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannel() {
        return channel;
    }

    /**
     * Sets the value of the channel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannel(String value) {
        this.channel = value;
    }

    /**
     * Gets the value of the clumpidx property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClumpidx() {
        if (clumpidx == null) {
            return "0/1";
        } else {
            return clumpidx;
        }
    }

    /**
     * Sets the value of the clumpidx property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClumpidx(String value) {
        this.clumpidx = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the title property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTitle().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Title }
     * 
     * 
     */
    public List<Title> getTitle() {
        if (title == null) {
            title = new ArrayList<Title>();
        }
        return this.title;
    }

    /**
     * Gets the value of the subTitle property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subTitle property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubTitle().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubTitle }
     * 
     * 
     */
    public List<SubTitle> getSubTitle() {
        if (subTitle == null) {
            subTitle = new ArrayList<SubTitle>();
        }
        return this.subTitle;
    }

    /**
     * Gets the value of the desc property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the desc property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDesc().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Desc }
     * 
     * 
     */
    public List<Desc> getDesc() {
        if (desc == null) {
            desc = new ArrayList<Desc>();
        }
        return this.desc;
    }

    /**
     * Gets the value of the credits property.
     * 
     * @return
     *     possible object is
     *     {@link Credits }
     *     
     */
    public Credits getCredits() {
        return credits;
    }

    /**
     * Sets the value of the credits property.
     * 
     * @param value
     *     allowed object is
     *     {@link Credits }
     *     
     */
    public void setCredits(Credits value) {
        this.credits = value;
    }

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
     * Gets the value of the category property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the category property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCategory().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Category }
     * 
     * 
     */
    public List<Category> getCategory() {
        if (category == null) {
            category = new ArrayList<Category>();
        }
        return this.category;
    }

    /**
     * Gets the value of the language property.
     * 
     * @return
     *     possible object is
     *     {@link Language }
     *     
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     * 
     * @param value
     *     allowed object is
     *     {@link Language }
     *     
     */
    public void setLanguage(Language value) {
        this.language = value;
    }

    /**
     * Gets the value of the origLanguage property.
     * 
     * @return
     *     possible object is
     *     {@link OrigLanguage }
     *     
     */
    public OrigLanguage getOrigLanguage() {
        return origLanguage;
    }

    /**
     * Sets the value of the origLanguage property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrigLanguage }
     *     
     */
    public void setOrigLanguage(OrigLanguage value) {
        this.origLanguage = value;
    }

    /**
     * Gets the value of the length property.
     * 
     * @return
     *     possible object is
     *     {@link Length }
     *     
     */
    public Length getLength() {
        return length;
    }

    /**
     * Sets the value of the length property.
     * 
     * @param value
     *     allowed object is
     *     {@link Length }
     *     
     */
    public void setLength(Length value) {
        this.length = value;
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

    /**
     * Gets the value of the country property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the country property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCountry().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Country }
     * 
     * 
     */
    public List<Country> getCountry() {
        if (country == null) {
            country = new ArrayList<Country>();
        }
        return this.country;
    }

    /**
     * Gets the value of the episodeNum property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the episodeNum property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEpisodeNum().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EpisodeNum }
     * 
     * 
     */
    public List<EpisodeNum> getEpisodeNum() {
        if (episodeNum == null) {
            episodeNum = new ArrayList<EpisodeNum>();
        }
        return this.episodeNum;
    }

    /**
     * Gets the value of the video property.
     * 
     * @return
     *     possible object is
     *     {@link Video }
     *     
     */
    public Video getVideo() {
        return video;
    }

    /**
     * Sets the value of the video property.
     * 
     * @param value
     *     allowed object is
     *     {@link Video }
     *     
     */
    public void setVideo(Video value) {
        this.video = value;
    }

    /**
     * Gets the value of the audio property.
     * 
     * @return
     *     possible object is
     *     {@link Audio }
     *     
     */
    public Audio getAudio() {
        return audio;
    }

    /**
     * Sets the value of the audio property.
     * 
     * @param value
     *     allowed object is
     *     {@link Audio }
     *     
     */
    public void setAudio(Audio value) {
        this.audio = value;
    }

    /**
     * Gets the value of the previouslyShown property.
     * 
     * @return
     *     possible object is
     *     {@link PreviouslyShown }
     *     
     */
    public PreviouslyShown getPreviouslyShown() {
        return previouslyShown;
    }

    /**
     * Sets the value of the previouslyShown property.
     * 
     * @param value
     *     allowed object is
     *     {@link PreviouslyShown }
     *     
     */
    public void setPreviouslyShown(PreviouslyShown value) {
        this.previouslyShown = value;
    }

    /**
     * Gets the value of the premiere property.
     * 
     * @return
     *     possible object is
     *     {@link Premiere }
     *     
     */
    public Premiere getPremiere() {
        return premiere;
    }

    /**
     * Sets the value of the premiere property.
     * 
     * @param value
     *     allowed object is
     *     {@link Premiere }
     *     
     */
    public void setPremiere(Premiere value) {
        this.premiere = value;
    }

    /**
     * Gets the value of the lastChance property.
     * 
     * @return
     *     possible object is
     *     {@link LastChance }
     *     
     */
    public LastChance getLastChance() {
        return lastChance;
    }

    /**
     * Sets the value of the lastChance property.
     * 
     * @param value
     *     allowed object is
     *     {@link LastChance }
     *     
     */
    public void setLastChance(LastChance value) {
        this.lastChance = value;
    }

    /**
     * Gets the value of the new property.
     * 
     * @return
     *     possible object is
     *     {@link New }
     *     
     */
    public New getNew() {
        return _new;
    }

    /**
     * Sets the value of the new property.
     * 
     * @param value
     *     allowed object is
     *     {@link New }
     *     
     */
    public void setNew(New value) {
        this._new = value;
    }

    /**
     * Gets the value of the subtitles property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subtitles property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubtitles().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Subtitles }
     * 
     * 
     */
    public List<Subtitles> getSubtitles() {
        if (subtitles == null) {
            subtitles = new ArrayList<Subtitles>();
        }
        return this.subtitles;
    }

    /**
     * Gets the value of the rating property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rating property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRating().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Rating }
     * 
     * 
     */
    public List<Rating> getRating() {
        if (rating == null) {
            rating = new ArrayList<Rating>();
        }
        return this.rating;
    }

    /**
     * Gets the value of the starRating property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the starRating property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStarRating().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StarRating }
     * 
     * 
     */
    public List<StarRating> getStarRating() {
        if (starRating == null) {
            starRating = new ArrayList<StarRating>();
        }
        return this.starRating;
    }

    /**
     * Gets the value of the review property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the review property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReview().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Review }
     * 
     * 
     */
    public List<Review> getReview() {
        if (review == null) {
            review = new ArrayList<Review>();
        }
        return this.review;
    }

}
