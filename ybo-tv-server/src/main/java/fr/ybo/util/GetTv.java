package fr.ybo.util;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import fr.ybo.modele.ChannelForMemCache;
import fr.ybo.modele.ProgrammeForMemCache;
import fr.ybo.modele.TvForMemCache;
import fr.ybo.xmltv.Tv;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


public class GetTv {

    @SuppressWarnings("unchecked")
    public static TvForMemCache getCurrentTv() throws JAXBException, IOException {
        String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());

        MemcacheService service = MemcacheServiceFactory.getMemcacheService();

        List<ChannelForMemCache> channels = (List<ChannelForMemCache>) service.get(currentDate + "_channels");
        TvForMemCache tv = null;

        if (channels == null) {
            tv = getTvFromNetwork(currentDate, service);
        } else {
            tv = new TvForMemCache();
            tv.getChannel().addAll(channels);
            for (ChannelForMemCache channel : tv.getChannel()) {
                List<ProgrammeForMemCache> programmeForMemCaches = (List<ProgrammeForMemCache>) service.get(currentDate + "_programmes_" + channel.getId());
                if (programmeForMemCaches == null || programmeForMemCaches.isEmpty()) {
                    return getTvFromNetwork(currentDate, service);
                }
                tv.getProgramme().addAll(programmeForMemCaches);
            }
        }
        return tv;
    }

    private static TvForMemCache getTvFromNetwork(String currentDate, MemcacheService service) throws JAXBException, IOException {
        TvForMemCache tv;JAXBContext jc = JAXBContext.newInstance("fr.ybo.xmltv");
        Unmarshaller um = jc.createUnmarshaller();

        tv = TvForMemCache.fromTv((Tv) um.unmarshal(GetZip.getFile()));

        service.put(currentDate + "_channels", tv.getChannel());
        Map<String, List<ProgrammeForMemCache>> mapProgrammes = new HashMap<String, List<ProgrammeForMemCache>>();
        for (ProgrammeForMemCache programme : tv.getProgramme()) {
            if (!mapProgrammes.containsKey(programme.getChannel())) {
                mapProgrammes.put(programme.getChannel(), new ArrayList<ProgrammeForMemCache>());
            }
            mapProgrammes.get(programme.getChannel()).add(programme);
        }

        for (Map.Entry<String, List<ProgrammeForMemCache>> entry : mapProgrammes.entrySet()) {
            service.put(currentDate + "_programmes_" + entry.getKey(), entry.getValue());
        }
        return tv;
    }
}
