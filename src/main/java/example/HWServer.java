package example;

import beans.UserBean;
import com.google.gson.Gson;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;
import database.SakilaDatabaseImpl;
import model.CustomerEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MultivaluedMap;

/**
 * Created by Kim on 2016-02-07.
 */
@Path("/helloworld-webapp")
public class HWServer {

    private SakilaDatabaseImpl sakilaDatabase = new SakilaDatabaseImpl();

    // The Java method will process HTTP GET requests
    @GET
    @Path("/servermessage")
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getServerMessage() {
        return "HWServer v0.1";
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
    @Produces("application/json")
    public String getUserDataAsJson(@QueryParam("id") int id) {
        CustomerEntity c = sakilaDatabase.getCustomerById(id);
        Gson gson = new Gson();
        return gson.toJson(c);
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Server starting...");

        HttpServer server = HttpServerFactory.create("http://localhost:9998/");

        System.out.println("Database connection established.");

        server.start();

        System.out.println("Server running");
        System.out.println("Visit: http://localhost:9998/helloworld-webapp"); // hwserver
        System.out.println("Hit return to stop...");
        System.in.read();
        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");
    }

}
