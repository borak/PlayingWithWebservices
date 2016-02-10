package example;

import beans.UserBean;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;
import database.SakilaDatabaseImpl;

import javax.json.Json;
import javax.ws.rs.*;
import javax.ws.rs.core.MultivaluedMap;

/**
 * Created by Kim on 2016-02-07.
 */
@Path("/hwserver")
public class HWServer {

    private SakilaDatabaseImpl sakilaDatabase;

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
        sakilaDatabase.getCustomerById(1);
        String json = Json.createObjectBuilder()
                .add("username", "")
                .build()
                .toString();
        return json;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Server starting...");

        HttpServer server = HttpServerFactory.create("http://localhost:9998/");

        System.out.println("Database connection established.");

        Object o = new SakilaDatabaseImpl().getCustomerById(1);
        System.out.println("Found o="+o.toString());

        server.start();

        System.out.println("Server running");
        System.out.println("Visit: http://localhost:9998/hwserver");
        System.out.println("Hit return to stop...");
        System.in.read();
        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");
    }

}
