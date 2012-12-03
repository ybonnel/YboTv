package fr.ybo.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import fr.ybo.services.DataService;
import fr.ybo.services.ServiceExeption;
import fr.ybo.services.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DataServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(HttpServlet.class);

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        Iterable<String> paths = Splitter.on('/').trimResults().omitEmptyStrings().split(req.getPathInfo());

        String nomService = Iterables.getFirst(paths, null);

        if (nomService == null) {
            resp.setStatus(404);
            return;
        }

        DataService service = ServiceFactory.getService(nomService);
        if (service == null) {
            resp.setStatus(404);
            return;
        }

        String[] parameters = Iterables.toArray(Iterables.skip(paths, 1), String.class);

        try {
            String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
            String memCacheId = "jsonResponse/" + currentDate + req.getPathInfo();
            MemcacheService cacheService = MemcacheServiceFactory.getMemcacheService();

            String jsonResponse = (String) cacheService.get(memCacheId);

            if (jsonResponse == null) {
                Object result = ServiceFactory.callService(service, req.getMethod(), parameters);

                ObjectMapper mapper = new ObjectMapper();
                mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
                jsonResponse = mapper.writeValueAsString(result);
                // On ne met en cache la réponse que si elle fait moins de 500Ko.
                if (jsonResponse.length() < 500000) {
                    cacheService.put(memCacheId, jsonResponse, Expiration.byDeltaMillis((int) TimeUnit.DAYS.toMillis(2)));
                }
            }
            resp.getWriter().print(jsonResponse);
        } catch (ServiceExeption serviceExeption) {
            resp.setStatus(500);
            logger.info("ServiceException ", serviceExeption);
            logger.error(serviceExeption);
            serviceExeption.printStackTrace(resp.getWriter());
        }
    }

}
