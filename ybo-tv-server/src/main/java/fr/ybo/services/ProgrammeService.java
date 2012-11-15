package fr.ybo.services;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import fr.ybo.util.GetTv;
import fr.ybo.xmltv.Channel;
import fr.ybo.xmltv.Programme;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ProgrammeService extends DataService<Programme> {

    @SuppressWarnings("unchecked")
    @Override
    public List<Programme> getAll() throws ServiceExeption {
        try {
            return GetTv.getCurrentTv().getProgramme();
        } catch (JAXBException jaxbException) {
            throw new ServiceExeption(jaxbException);
        } catch (IOException ioException) {
            throw new ServiceExeption(ioException);

        }
    }

    @Override
    public Programme getById(String id) throws ServiceExeption {
        String idMemCache = "programme_" + id;
        MemcacheService service = MemcacheServiceFactory.getMemcacheService();
        Programme programme = (Programme) service.get(idMemCache);
        if (programme == null) {
            for (Programme oneProgramme : getAll()) {
                if (id.equals(oneProgramme.getId())) {
                    programme = oneProgramme;
                    break;
                }
            }
            if (programme != null) {
                service.put(idMemCache, programme);
            }
        }
        return programme;
    }

    private List<Programme> getByChannel(String channel) throws ServiceExeption {
        String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String memCacheId = "programmeByChannel_" + channel + "_" + currentDate;
        MemcacheService service = MemcacheServiceFactory.getMemcacheService();
        List<Programme> programmes = (List<Programme>) service.get(memCacheId);
        if (programmes == null) {
            programmes = new ArrayList<Programme>();
            for (Programme programme : getAll()) {
                if (channel.equals(programme.getChannel())) {
                    programmes.add(programme);
                }
            }
            service.put(memCacheId, programmes);
        }
        return programmes;
    }

    @Override
    public List<Programme> getBy(String parameterName, String parameterValue) throws ServiceExeption {
        if ("id".equals(parameterName)) {
            return Collections.singletonList(getById(parameterValue));
        } else if ("channel".equals(parameterName)) {
            return getByChannel(parameterValue);
        }
        return null;
    }


}
