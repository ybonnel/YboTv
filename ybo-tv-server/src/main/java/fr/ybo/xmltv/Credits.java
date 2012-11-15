
package fr.ybo.xmltv;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "director",
    "actor",
    "writer",
    "adapter",
    "producer",
    "composer",
    "editor",
    "presenter",
    "commentator",
    "guest"
})
@XmlRootElement(name = "credits")
public class Credits implements Serializable {

    @JsonIgnore
    protected List<Director> director;

    @JsonProperty("directors")
    public List<String> getDirectors() {
        return Lists.transform(getDirector(), new Function<Director, String>() {
            @Override
            public String apply(Director input) {
                return input.getvalue();
            }
        });
    }


    @JsonIgnore
    protected List<Actor> actor;

    @JsonProperty("actors")
    public List<String> getActors() {
        return Lists.transform(getActor(), new Function<Actor, String>() {
            @Override
            public String apply(Actor input) {
                return input.getvalue();
            }
        });
    }

    @JsonIgnore
    protected List<Writer> writer;

    @JsonProperty("writers")
    public List<String> getWriters() {
        return Lists.transform(getWriter(), new Function<Writer, String>() {
            @Override
            public String apply(Writer input) {
                return input.getvalue();
            }
        });
    }

    @JsonIgnore
    protected List<Adapter> adapter;

    @JsonProperty("adapters")
    public List<String> getAdapters() {
        return Lists.transform(getAdapter(), new Function<Adapter, String>() {
            @Override
            public String apply(Adapter input) {
                return input.getvalue();
            }
        });
    }

    @JsonIgnore
    protected List<Producer> producer;

    @JsonProperty("producers")
    public List<String> getProducers() {
        return Lists.transform(getProducer(), new Function<Producer, String>() {
            @Override
            public String apply(Producer input) {
                return input.getvalue();
            }
        });
    }

    @JsonIgnore
    protected List<Composer> composer;

    @JsonProperty("composers")
    public List<String> getComposers() {
        return Lists.transform(getComposer(), new Function<Composer, String>() {
            @Override
            public String apply(Composer input) {
                return input.getvalue();
            }
        });
    }

    @JsonIgnore
    protected List<Editor> editor;

    @JsonProperty("editors")
    public List<String> getEditors() {
        return Lists.transform(getEditor(), new Function<Editor, String>() {
            @Override
            public String apply(Editor input) {
                return input.getvalue();
            }
        });
    }

    @JsonIgnore
    protected List<Presenter> presenter;

    @JsonProperty("presenters")
    public List<String> getPresenters() {
        return Lists.transform(getPresenter(), new Function<Presenter, String>() {
            @Override
            public String apply(Presenter input) {
                return input.getvalue();
            }
        });
    }

    @JsonIgnore
    protected List<Commentator> commentator;

    @JsonProperty("commentators")
    public List<String> getCommentators() {
        return Lists.transform(getCommentator(), new Function<Commentator, String>() {
            @Override
            public String apply(Commentator input) {
                return input.getvalue();
            }
        });
    }

    @JsonIgnore
    protected List<Guest> guest;

    @JsonProperty("guests")
    public List<String> getGuests() {
        return Lists.transform(getGuest(), new Function<Guest, String>() {
            @Override
            public String apply(Guest input) {
                return input.getvalue();
            }
        });
    }


    /**
     * Gets the value of the director property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the director property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDirector().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Director }
     * 
     * 
     */
    public List<Director> getDirector() {
        if (director == null) {
            director = new ArrayList<Director>();
        }
        return this.director;
    }

    /**
     * Gets the value of the actor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the actor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Actor }
     * 
     * 
     */
    public List<Actor> getActor() {
        if (actor == null) {
            actor = new ArrayList<Actor>();
        }
        return this.actor;
    }

    /**
     * Gets the value of the writer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the writer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWriter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Writer }
     * 
     * 
     */
    public List<Writer> getWriter() {
        if (writer == null) {
            writer = new ArrayList<Writer>();
        }
        return this.writer;
    }

    /**
     * Gets the value of the adapter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the adapter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdapter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Adapter }
     * 
     * 
     */
    public List<Adapter> getAdapter() {
        if (adapter == null) {
            adapter = new ArrayList<Adapter>();
        }
        return this.adapter;
    }

    /**
     * Gets the value of the producer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the producer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProducer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Producer }
     * 
     * 
     */
    public List<Producer> getProducer() {
        if (producer == null) {
            producer = new ArrayList<Producer>();
        }
        return this.producer;
    }

    /**
     * Gets the value of the composer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the composer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComposer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Composer }
     * 
     * 
     */
    public List<Composer> getComposer() {
        if (composer == null) {
            composer = new ArrayList<Composer>();
        }
        return this.composer;
    }

    /**
     * Gets the value of the editor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the editor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEditor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Editor }
     * 
     * 
     */
    public List<Editor> getEditor() {
        if (editor == null) {
            editor = new ArrayList<Editor>();
        }
        return this.editor;
    }

    /**
     * Gets the value of the presenter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the presenter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPresenter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Presenter }
     * 
     * 
     */
    public List<Presenter> getPresenter() {
        if (presenter == null) {
            presenter = new ArrayList<Presenter>();
        }
        return this.presenter;
    }

    /**
     * Gets the value of the commentator property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the commentator property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCommentator().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Commentator }
     * 
     * 
     */
    public List<Commentator> getCommentator() {
        if (commentator == null) {
            commentator = new ArrayList<Commentator>();
        }
        return this.commentator;
    }

    /**
     * Gets the value of the guest property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the guest property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGuest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Guest }
     * 
     * 
     */
    public List<Guest> getGuest() {
        if (guest == null) {
            guest = new ArrayList<Guest>();
        }
        return this.guest;
    }

}
