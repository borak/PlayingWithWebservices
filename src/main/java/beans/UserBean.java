package beans;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * This bean is used for logging in service and to store the user's personal
 * information.
 *
 * @author Kim
 */
@Named("userBean")
@SessionScoped
public class UserBean implements Serializable {

    private String username;
    private String password;
    /**
     * Logs in the user and fetches the user's information. Username and
     * password need to be set before calling this method. This method will also
     * clear the password after the database call.
     *
     * @return A status text which will be handled by the JSF.
     */
    public String login() {
        if (username == null || password == null) {
            return "fail_1";
        }

        try {
            //person = controller.login(username, password);
            password = null;

            return "success";
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Logouts the user by resetting the beans state.
     *
     * @return The page to redirect to.
     */
    public String logout() {
        username = null;
        password = null;
        return "success";
    }

    public String getUsername() {
        return username;
    }

    /**
     * Store the username for further use
     *
     * @param username The username to use for operations.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Store the password for further use.
     *
     * @param password The password to use for operations.
     */
    public void setPassword(String password) {
        this.password = password;
    }


}

