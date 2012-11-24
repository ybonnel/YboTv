package fr.ybo.services;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import fr.ybo.modele.ChannelForMemCache;
import fr.ybo.modele.ProgrammeForMemCache;
import fr.ybo.util.GetTv;
import fr.ybo.xmltv.Channel;
import fr.ybo.xmltv.Programme;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChannelService extends DataService<ChannelForMemCache> {

    @SuppressWarnings("unchecked")
    @Override
    public List<ChannelForMemCache> getAll() throws ServiceExeption {
        try {
            MemcacheService service = MemcacheServiceFactory.getMemcacheService();
            List<ChannelForMemCache> channels = (List<ChannelForMemCache>) service.get("channels");
            if (channels == null) {
                channels = GetTv.getCurrentTv().getChannel();
                service.put("channels", channels);
            }
            return channels;
        } catch (JAXBException jaxbException) {
            throw new ServiceExeption(jaxbException);
        } catch (IOException ioException) {
            throw new ServiceExeption(ioException);

        }
    }

    @Override
    public ChannelForMemCache getById(String id) throws ServiceExeption {
        String idMemCache = "channel_" + id;
        MemcacheService service = MemcacheServiceFactory.getMemcacheService();
        ChannelForMemCache channel = (ChannelForMemCache) service.get(idMemCache);
        if (channel == null) {
            for (ChannelForMemCache oneChannel : getAll()) {
                if (id.equals(oneChannel.getId())) {
                    channel = oneChannel;
                    break;
                }
            }
            if (channel != null) {
                service.put(idMemCache, channel);
            }
        }
        return channel;
    }

    @Override
    public List<ChannelForMemCache> getBy(String parameterName, String parameterValue) throws ServiceExeption {
        if ("id".equals(parameterName)) {
            return Collections.singletonList(getById(parameterValue));
        } else if ("date".equals(parameterName)) {
            List<ChannelForMemCache> returnChannels = new ArrayList<ChannelForMemCache>();
            List<ChannelForMemCache> channels = getAll();
            for (ChannelForMemCache channel : channels) {
                List<ProgrammeForMemCache> programmes = ((ProgrammeService) ServiceFactory.getService("programme")).get("channel", channel.getId(), "date", parameterValue);
                if (!programmes.isEmpty()) {
                    channel.setCurrentProgramme(programmes.get(0));
                }
                returnChannels.add(channel);
            }
            return returnChannels;
        }
        return null;
    }

    @Override
    public List<ChannelForMemCache> get(String... parameters) throws ServiceExeption {
        return null;
    }


}
