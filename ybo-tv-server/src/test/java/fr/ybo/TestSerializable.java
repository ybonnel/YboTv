package fr.ybo;


import com.google.common.io.CharStreams;
import com.google.common.io.InputSupplier;
import fr.ybo.modele.TvForMemCache;
import fr.ybo.xmltv.Tv;
import org.junit.Test;
import org.mortbay.io.WriterOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.charset.Charset;
import java.util.zip.ZipInputStream;

public class TestSerializable {

    @Test
    public void testSerializable() throws JAXBException, IOException {
        JAXBContext jc = JAXBContext.newInstance("fr.ybo.xmltv");
        Unmarshaller um = jc.createUnmarshaller();
        Tv tv = (Tv) um.unmarshal(getFile());
        System.out.println(tv);
        TvForMemCache tvForMemCache = TvForMemCache.fromTv(tv);

        ObjectOutput ObjOut = new ObjectOutputStream(new WriterOutputStream(new StringWriter()));

        ObjOut.writeObject(tvForMemCache);

        ObjOut.close();

    }


    public static Reader getFile() throws IOException {
        InputSupplier<InputStream> supplier = new InputSupplier<InputStream>() {

            ZipInputStream stream = null;

            @Override
            public InputStream getInput() throws IOException {
                if (stream == null) {
                    InputStream inputStream = TestSerializable.class.getResourceAsStream("/tnt_lite.zip");
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
