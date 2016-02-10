package server;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;
import beans.UserBean;

import javax.json.Json;
import javax.ws.rs.*;
import javax.ws.rs.core.MultivaluedMap;

/**
 * Created by Kim on 2016-02-07.
 */
@Path("/helloworldserver")
public class HWServer {
/*
    private HttpServer server;

    public HWServer() throws IOException {
        HttpServer server = HttpServerFactory.create("http://localhost:9998/");
    }

    public void start() {
        server.start();
    }

    public void stop() {
        server.stop(0);
    }

    private SakilaDatabaseImpl sakilaDB = null;

    public static void setSakilaDB(SakilaDatabaseImpl sakilaDB) {
        this.sakilaDB = sakilaDB;
    }
*/
    //public SakilaDatabaseImpl getSakilaDB() {
    //    return this.sakilaDB;
    //}

    @GET
    @Produces("text/plain")
    public String getUsername() {
        System.out.println("SERVICE MATCHED: getUsername()");
        /*if(sakilaDB == null) {
            return "";
        }
        CustomerEntity c = sakilaDB.getCustomerById(0);
        return c.getEmail();
        */
        return "Username Test";
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
    public String getCustomerDataAsJson(//@BeanParam UserBean userBean
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

        //HWServer hwServer = new HWServer();

        //setSakilaDB(new SakilaDatabaseImpl());

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