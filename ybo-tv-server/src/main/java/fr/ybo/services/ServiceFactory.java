package fr.ybo.services;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {

    private static Map<String, DataService<?>> services = new HashMap<String, DataService<?>>(){{
        put("channel", new ChannelService());
    }};


    public static DataService<?> getService(String service) {
        return services.get(service);
    }

    public static Object callService(DataService service, String method, String...parameters) throws ServiceExeption {
        if ("GET".equals(method)) {
            // GET : on a soit le getAll, soit le getBy
            if (parameters.length == 0) {
                return service.getAll();
            } else if (parameters.length == 1) {
                return service.getById(parameters[0]);
            }
        }

        return null;
    }


}
