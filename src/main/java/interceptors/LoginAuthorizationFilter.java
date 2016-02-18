package interceptors;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import example.LoginResource;
import model.UserEntity;

import javax.ws.rs.core.Response.Status;
import java.sql.Date;
import java.util.Calendar;

/**
 * Created by Kim on 2016-02-18.
 */
public class LoginAuthorizationFilter implements ContainerResponseFilter {

    @Override
    public ContainerResponse filter(ContainerRequest containerRequest, ContainerResponse containerResponse) {
        UserEntity entity = (UserEntity) containerResponse.getEntity();
        Date currentTime = new Date(Calendar.getInstance().getTime().getTime() + LoginResource.ExpireTimeInMinutes);

        if(entity == null || entity.getLastLogin().after(currentTime)) {
            containerResponse.setStatus(Status.UNAUTHORIZED.getStatusCode());
            containerResponse.setEntity("User cannot access the resource.");
        }

        return containerResponse;
    }
}