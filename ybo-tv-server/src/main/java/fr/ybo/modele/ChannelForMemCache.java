package fr.ybo.modele;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.ybo.xmltv.Channel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ChannelForMemCache implements Serializable {

    private String id;
    private String displayName;


    @JsonProperty("icon")
    public String getOneIcon() {
        return mapChaineLogo.get(id);
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public static ChannelForMemCache fromChannel(Channel channel) {
        ChannelForMemCache channelForMemCache = new ChannelForMemCache();
        channelForMemCache.setDisplayName(channel.getOneDisplayName());
        channelForMemCache.setId(channel.getId());
        return channelForMemCache;
    }

    private transient ProgrammeForMemCache currentProgramme;

    public ProgrammeForMemCache getCurrentProgramme() {
        return currentProgramme;
    }

    public void setCurrentProgramme(ProgrammeForMemCache currentProgramme) {
        this.currentProgramme = currentProgramme;
    }
}
