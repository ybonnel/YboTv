package fr.ybo.web;

import com.google.gson.Gson;
import fr.ybo.model.Programme;
import fr.ybo.util.GetZip;
import fr.ybo.xmltv.Tv;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;

public class DataServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(HttpServlet.class);

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        Gson gson = new Gson();
        try {
            resp.getWriter().println(gson.toJson(Programme.getCurrentTv().getChannel()));
        } catch (JAXBException e) {
            resp.setStatus(500);
            logger.error("Error durring getTv", e);
        }
    }

}
