package example;

import database.SakilaDatabase;
import model.UserEntity;
import org.springframework.stereotype.Component;

import javax.enterprise.context.SessionScoped;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

/**
 * Created by Kim on 2016-02-18.
 */
@Component
@SessionScoped
@Path("/login")
public class LoginResource implements Serializable {
    UserEntity userEntity;
    SakilaDatabase db;
    public static final int ExpireTimeInMinutes = 30 * 60 * 1000;

    @POST
    @Produces("application/json")
    public Response login(String username, String password) {
        userEntity = db.login(username, password);

        if(userEntity != null) {
            Date expireDate = new Date(Calendar.getInstance().getTime().getTime() + ExpireTimeInMinutes);
            return Response.ok().entity(userEntity).expires(expireDate).build();
        }

        return Response.serverError().build();
    }

}
