package fr.ybo.model;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import fr.ybo.util.GetZip;
import fr.ybo.xmltv.Tv;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Programme {

    public static Tv getCurrentTv() throws JAXBException, IOException {
        String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());

        MemcacheService service = MemcacheServiceFactory.getMemcacheService();

        Tv tv = (Tv) service.get(currentDate);

        if (tv == null) {
            JAXBContext jc = JAXBContext.newInstance("fr.ybo.xmltv");
            Unmarshaller um = jc.createUnmarshaller();

            tv = (Tv) um.unmarshal(GetZip.getFile());
            service.put(currentDate, tv);
        }
        return tv;
    }
}
