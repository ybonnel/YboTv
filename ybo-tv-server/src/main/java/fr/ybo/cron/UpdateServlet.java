package fr.ybo.cron;

import com.google.common.base.Throwables;
import fr.ybo.modele.TvForMemCache;
import fr.ybo.util.GetTv;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;

public class UpdateServlet extends HttpServlet {

    private final static Logger logger = Logger.getLogger(UpdateServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("UpdateServlet - begin");
        try {
            GetTv.getCurrentTv();
        } catch (JAXBException e) {
            Throwables.propagate(e);
        }
        logger.info("Update Servlet - end");
    }
}
