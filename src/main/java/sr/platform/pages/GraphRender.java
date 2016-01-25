package sr.platform.pages;

import sr.platform.util.FileUtil;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by tantop01 on 19/01/16.
 */
@Path("/pages")
public class GraphRender {
    FileUtil util = new FileUtil();

    @Path("/graph")
    @GET
    public Response graph() {
        return util.returnFileAsResponse("/html/graph.html", "text/html");
    }

    @Path("/js/vis.js")
    @GET
    public Response visJs() {
        return util.returnFileAsResponse("/js/vis.js", "text/javascript");
    }

    @Path("/js/jQuery-2.1.3.min.js")
    @GET
    public Response jQuery() {
        return util.returnFileAsResponse("/js/jQuery-2.1.3.min.js", "text/javascript");
    }

    @Path("/css/vis.css")
    @GET
    public Response visCss() {
        return util.returnFileAsResponse("/css/vis.css", "text/css");
    }


        
}
