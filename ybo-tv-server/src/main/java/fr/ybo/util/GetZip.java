package fr.ybo.util;


import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;
import com.google.common.io.InputSupplier;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.zip.ZipInputStream;

public class GetZip {

    public final static String FILE_URL = "http://kevinpato.free.fr/xmltv/download/tnt.zip";

    public static Reader getFile() throws IOException {
        StringBuilder contentXml = new StringBuilder();
        /*URL url = new URL(FILE_URL);
        URLConnection connection = url.openConnection();
        connection.connect();
        InputStream stream = connection.getInputStream();*/

        InputSupplier<InputStream> supplier = new InputSupplier<InputStream>() {

            ZipInputStream stream = null;

            @Override
            public InputStream getInput() throws IOException {
                if (stream == null) {
                    stream = new ZipInputStream(GetZip.class.getResourceAsStream("/tnt_lite.zip"));
                    stream.getNextEntry();
                }
                return stream;
            }
        };

        InputSupplier<InputStreamReader> reader = CharStreams.newReaderSupplier(supplier, Charset.forName("utf-8"));

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
