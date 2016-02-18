package example;

import com.google.gson.Gson;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;
import database.SakilaDatabase;
import model.CustomerEntity;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by Kim on 2016-02-07.
 */
@Component
@Path("/helloworld-webapp")
public class HWServer {

    private SakilaDatabase sakilaDatabase = SakilaDatabase.getInstance();

    @GET
    @Path("/servermessage")
    @Produces("text/plain")
    public String getServerMessage() {
        return "HWServer v0.1";
    }

    @GET
    @Path("/view/json")
    @Produces("application/json")
    public String getUserDataAsJson(@QueryParam("id") int id) {
        CustomerEntity c = sakilaDatabase.getCustomerById(id);
        Gson gson = new Gson();
        return gson.toJson(c);
    }

    @POST
    @Path("{customer}")
    @Produces("application/json")
    public Response postCustomer(@BeanParam CustomerEntity customerEntity) {
        System.out.println("Received POST for customer="+customerEntity.getCustomerId());

        if(customerEntity == null)
            return Response.serverError().build();

        sakilaDatabase.newCustomer(customerEntity);
        return Response.ok().build();
    }

    @GET
    @Path("{customer}")
    @Produces("application/json")
    public Response getCustomer(@PathParam("id") int id) {
        System.out.println("Received GET for customer="+id);
        CustomerEntity c = sakilaDatabase.getCustomerById(id);

        if(c == null)
            return Response.serverError().build();

        Gson gson = new Gson();
        return Response.ok(gson.toJson(c)).build();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Server starting...");

        HttpServer server = HttpServerFactory.create("http://localhost:9998/");

        server.start();

        System.out.println("Server running");
        System.out.println("Visit: http://localhost:9998/helloworld-webapp");
        System.out.println("TEST: http://localhost:9998/helloworld-webapp/application.wadl");
        System.out.println("Hit return to stop...");
        System.in.read();
        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");
    }

}
