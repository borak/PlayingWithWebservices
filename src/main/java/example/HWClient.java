package example;


import beans.UserBean;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by Kim on 2016-02-07.
 */
public class HWClient {

    public static void main(String[] args) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9998").path("resource");

        Form form = new Form();
        form.param("x", "foo");
        form.param("y", "bar");

        UserBean bean =
                target.request(MediaType.APPLICATION_JSON_TYPE)
                        .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE),
                                UserBean.class);
    }
}
