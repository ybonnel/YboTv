package fr.ybo.services;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import fr.ybo.model.Programme;
import fr.ybo.xmltv.Channel;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public class ChannelService extends DataService<Channel> {

    @SuppressWarnings("unchecked")
    @Override
    public List<Channel> getAll() throws ServiceExeption {
        try {
            MemcacheService service = MemcacheServiceFactory.getMemcacheService();
            List<Channel> channels = (List<Channel>) service.get("channels");
            if (channels == null) {
                channels = Programme.getCurrentTv().getChannel();
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
    public Channel getById(String id) throws ServiceExeption {
        String idMemCache = "channel_" + id;
        MemcacheService service = MemcacheServiceFactory.getMemcacheService();
        Channel channel = (Channel) service.get(idMemCache);
        if (channel == null) {
            for (Channel oneChannel : getAll()) {
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


}
