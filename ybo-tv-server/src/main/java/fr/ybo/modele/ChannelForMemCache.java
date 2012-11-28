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
        put("1", "tf1.png");
        put("2", "france2.png");
        put("3", "france3.png");
        put("4", "canal.png");
        put("5", "france5.png");
        put("6", "m6.png");
        put("7", "arte.png");
        put("8", "d8.png");
        put("9", "w9.png");
        put("10", "tmc.png");
        put("11", "nt1.png");
        put("12", "nrj12.png");
        put("13", "lachaineparlementaire.png");
        put("14", "france4.png");
        put("15", "bfmtv.png");
        put("16", "itele.png");
        put("17", "d17.png");
        put("18", "gulli.png");
        put("999", "franceo.png");
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
