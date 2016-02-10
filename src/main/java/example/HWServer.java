package example;

import beans.UserBean;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.json.Json;
import javax.ws.rs.*;
import javax.ws.rs.core.MultivaluedMap;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Kim on 2016-02-07.
 */
@Path("/helloworldserver")
public class HWServer {

    //@EJB
    //private UserBean userBean;

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getUsername() {
        System.out.println("SERVICE MATCHED: getUsername()");
        return "Your profile name";
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public void post(MultivaluedMap<String, String> formParams) {
        System.out.println("Received POST for user="+formParams.getFirst("username"));
    }

    @POST
    public void post(@BeanParam UserBean userBean, String entity) {
        System.out.println("Received POST for user="+userBean.getUsername());
    }

    @GET
    @Path("/view/json")
    @Produces({"application/json"})
    //@Produces(MediaType.APPLICATION_JSON)
    public String getUserDataAsJson(//@BeanParam UserBean userBean
    ) {
        String json = Json.createObjectBuilder()
                .add("username", "")
                .build()
                .toString();
        return json;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Server starting...");

        HttpServer server = HttpServerFactory.create("http://localhost:9998/");

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = null;
        con = DriverManager.getConnection("jdbc:mysql://localhost/sakila?user=admin&password=passw");

        SessionFactory sessions = new Configuration()
                //.addClass(org.hibernate.auction.model.Item.class)
                //.addClass(org.hibernate.auction.model.Category.class)
                //.addClass(org.hibernate.auction.model.Bid.class)
                .setProperties( System.getProperties() )
                .buildSessionFactory();

        System.out.println("Database connection established.");

        server.start();

        System.out.println("Server running");
        System.out.println("Visit: http://localhost:9998/helloworldserver");
        System.out.println("Hit return to stop...");
        System.in.read();
        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");
    }

}
