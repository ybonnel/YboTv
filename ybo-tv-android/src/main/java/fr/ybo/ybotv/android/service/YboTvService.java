package fr.ybo.ybotv.android.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import fr.ybo.ybotv.android.exception.YboTvException;
import fr.ybo.ybotv.android.modele.Channel;
import fr.ybo.ybotv.android.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.util.List;

public class YboTvService {

    private static final YboTvService instance = new YboTvService();

    private static final String SERVEUR = "http://ybo-tv.appspot.com/";

    private static final String CHANNEL_SERVICE_URL = "data/channel";

    private YboTvService() {
    }

    public static YboTvService getInstance() {
        return instance;
    }

    public List<Channel> getChannels()  {
        try {
            HttpClient client = HttpUtils.getHttpClient();
            HttpUriRequest request = new HttpGet(SERVEUR + CHANNEL_SERVICE_URL);
            HttpResponse reponse = client.execute(request);
            Reader reader = new InputStreamReader(reponse.getEntity().getContent());
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            List<Channel> channels = gson.fromJson(reader, new TypeToken<List<Channel>>(){}.getType());
            reader.close();
            return channels;
        } catch (MalformedURLException e) {
            throw new YboTvException(e);
        } catch (IOException e) {
            throw new YboTvException(e);
        }
    }
}
