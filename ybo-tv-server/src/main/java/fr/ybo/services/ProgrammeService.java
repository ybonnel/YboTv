package fr.ybo.services;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import fr.ybo.modele.ProgrammeForMemCache;
import fr.ybo.util.GetTv;
import fr.ybo.xmltv.Channel;
import fr.ybo.xmltv.Programme;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ProgrammeService extends DataService<ProgrammeForMemCache> {

    @SuppressWarnings("unchecked")
    @Override
    public List<ProgrammeForMemCache> getAll() throws ServiceExeption {
        try {
            return GetTv.getCurrentTv().getProgramme();
        } catch (JAXBException jaxbException) {
            throw new ServiceExeption(jaxbException);
        } catch (IOException ioException) {
            throw new ServiceExeption(ioException);

        }
    }

    @Override
    public ProgrammeForMemCache getById(String id) throws ServiceExeption {
        String idMemCache = "programme_" + id;
        MemcacheService service = MemcacheServiceFactory.getMemcacheService();
        ProgrammeForMemCache programme = (ProgrammeForMemCache) service.get(idMemCache);
        if (programme == null) {
            for (ProgrammeForMemCache oneProgramme : getAll()) {
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

    private List<ProgrammeForMemCache> getByChannel(String channel) throws ServiceExeption {
        String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String memCacheId = "programmeByChannel_" + channel + "_" + currentDate;
        MemcacheService service = MemcacheServiceFactory.getMemcacheService();
        List<ProgrammeForMemCache> programmes = (List<ProgrammeForMemCache>) service.get(memCacheId);
        if (programmes == null) {
            programmes = new ArrayList<ProgrammeForMemCache>();
            for (ProgrammeForMemCache programme : getAll()) {
                if (channel.equals(programme.getChannel())) {
                    programmes.add(programme);
                }
            }
            service.put(memCacheId, programmes);
        }
        return programmes;
    }

    @Override
    public List<ProgrammeForMemCache> getBy(String parameterName, String parameterValue) throws ServiceExeption {
        if ("id".equals(parameterName)) {
            return Collections.singletonList(getById(parameterValue));
        } else if ("channel".equals(parameterName)) {
            return getByChannel(parameterValue);
        }
        return null;
    }

    private Collection<ProgrammeForMemCache> getByChannelAndDate(String channel, final String date) throws ServiceExeption {
        return Collections2.filter(getByChannel(channel), new Predicate<ProgrammeForMemCache>() {
            @Override
            public boolean apply(ProgrammeForMemCache programme) {
                return programme.getStart().compareTo(date) <= 0
                        && programme.getStop().compareTo(date) >= 0;
            }
        });
    }

    @Override
    public List<ProgrammeForMemCache> get(String... parameters) throws ServiceExeption {
        if (parameters.length == 4) {
            if ("channel".equals(parameters[0])
                    && "date".equals(parameters[2])) {
                return new ArrayList<ProgrammeForMemCache>(getByChannelAndDate(parameters[1], parameters[3]));

            }
        }
        return null;
    }


}
