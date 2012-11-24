package fr.ybo.modele;

import com.google.common.collect.Lists;
import fr.ybo.xmltv.Tv;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TvForMemCache implements Serializable {

    private List<ChannelForMemCache> channel;


    private List<ProgrammeForMemCache> programme;

    public List<ChannelForMemCache> getChannel() {
        if (channel == null) {
            channel = new ArrayList<ChannelForMemCache>();
        }
        return this.channel;
    }

    public List<ProgrammeForMemCache> getProgramme() {
        if (programme == null) {
            programme = new ArrayList<ProgrammeForMemCache>();
        }
        return programme;
    }

    public static TvForMemCache fromTv(Tv tv) {
        TvForMemCache tvForMemCache = new TvForMemCache();

        tvForMemCache.channel = Lists.transform(tv.getChannel(), new TransformChannels());

        tvForMemCache.programme = Lists.transform(tv.getProgramme(), new TransformProgrammes());
        return tvForMemCache;
    }

}
