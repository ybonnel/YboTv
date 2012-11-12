package fr.ybo.tv.server;

import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestController extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        resp.getWriter().println(gson.toJson("Path : " + req.getPathInfo()));
    }
}
