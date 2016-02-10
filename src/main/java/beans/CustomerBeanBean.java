package beans;

import model.CustomerEntity;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by Kim on 2016-02-10.
 */
@Named("CustomerBeanEJB")
@SessionScoped
public class CustomerBeanBean implements Serializable {

    private CustomerEntity customer;

    public CustomerBeanBean() {
    }
}
