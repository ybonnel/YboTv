package fr.ybo.util;


import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;
import com.google.common.io.InputSupplier;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.zip.ZipInputStream;

public class GetZip {

    private static Logger logger = Logger.getLogger(GetZip.class);

    public final static String FILE_URL = "http://kevinpato.free.fr/xmltv/download/tnt_lite.zip";

    public static Reader getFile() throws IOException {

        logger.info("getFile");


        InputSupplier<InputStream> supplier = new InputSupplier<InputStream>() {

            ZipInputStream stream = null;

            @Override
            public InputStream getInput() throws IOException {
                if (stream == null) {
                    URL url = new URL(FILE_URL);
                    URLConnection connection = url.openConnection();
                    connection.setConnectTimeout(0);
                    connection.setReadTimeout(0);
                    connection.connect();
                    InputStream inputStream = connection.getInputStream();
                    //InputStream inputStream = GetZip.class.getResourceAsStream("/tnt_lite.zip");
                    stream = new ZipInputStream(inputStream);
                    stream.getNextEntry();
                }
                return stream;
            }
        };

        InputSupplier<InputStreamReader> reader = CharStreams.newReaderSupplier(supplier, Charset.forName("utf-8"));

        StringBuilder contentXml = new StringBuilder();
        for (String line : CharStreams.readLines(reader)) {
            if (!line.startsWith("<!DOCTYPE")) {
                contentXml.append(line);
                contentXml.append('\n');
            }
        }

        reader.getInput().close();

        return new StringReader(contentXml.toString());
    }
}
