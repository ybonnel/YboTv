package fr.ybo.tv.server.util;

import com.google.common.io.ByteStreams;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class GetZip {

    public final static String FILE_URL = "http://kevinpato.free.fr/xmltv/download/tnt.zip";

    public static byte[] getFile() throws IOException {
        URL url = new URL(FILE_URL);
        URLConnection connection = url.openConnection();
        connection.connect();
        InputStream stream = connection.getInputStream();
        byte[] retour = ByteStreams.toByteArray(stream);
        stream.close();
        return retour;
    }

}
