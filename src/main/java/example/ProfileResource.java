package example;

import database.SakilaDatabase;
import interceptors.LoginAuthorizationFilter;
import model.CustomerEntity;
import model.StaffEntity;
import model.StoreEntity;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.interceptor.Interceptors;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by Kim on 2016-02-18.
 */
@Component
@Path("/myprofile")
public class ProfileResource {

    private SakilaDatabase sakilaDatabase = SakilaDatabase.getInstance();

    @GET
    @Path("{data}")
    @Interceptors(LoginAuthorizationFilter.class)
    @Produces("application/json")
    public JSONObject getProfileDataAsJSON(@QueryParam("id") int id) throws JSONException {
        CustomerEntity ce = sakilaDatabase.getCustomerById(id);

        StoreEntity se = null;
        if(ce.getStoreId() >= 0)
            se = sakilaDatabase.getStoreById(ce.getStoreId());

        JSONObject object = new JSONObject();

        if(ce == null) {
            return object;
        }

        object.put("customerId".toLowerCase()   , ce.getCustomerId());
        object.put("storeId".toLowerCase()      , ce.getStoreId());
        object.put("firstName".toLowerCase()    , ce.getFirstName());
        object.put("lastName".toLowerCase()     , ce.getLastName());
        object.put("email".toLowerCase()        , ce.getEmail());
        object.put("addressId".toLowerCase()    , ce.getAddressId());
        object.put("active".toLowerCase()       , ce.getActive());
        object.put("lastUpdate".toLowerCase()   , ce.getLastUpdate());

        if(se == null && se.getManagerStaffId() >= 0) {
            return object;
        }

        object.put("customerId", se.getAddressId());

        StaffEntity staffe = sakilaDatabase.getManagerStaffById(se.getManagerStaffId());
        object.put("managerfirstName".toLowerCase(), staffe.getFirstName());
        object.put("managerlastName".toLowerCase(), staffe.getLastName());
        object.put("manageremail", staffe.getEmail());

        return object;
    }

    @POST
    @Path("logout")
    @Produces("application/json")
    public Response postLogout(/*@Context HttpServletRequest req*/) {
        //req.getSession().invalidate();
        return Response.ok().build();
    }

}
