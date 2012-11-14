package fr.ybo.web;

import com.google.gson.Gson;
import fr.ybo.util.GetZip;
import fr.ybo.xmltv.Tv;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;

public class DataServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        Tv tv = null;

        try {
            JAXBContext jc = JAXBContext.newInstance("fr.ybo.xmltv");
            Unmarshaller um = jc.createUnmarshaller();

            tv = (Tv) um.unmarshal(GetZip.getFile());
        } catch (JAXBException e) {
            e.printStackTrace();
        }


        Gson gson = new Gson();
        resp.getWriter().println(gson.toJson(tv));
    }

}
