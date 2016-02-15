package beans;

import model.CustomerEntity;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by Kim on 2016-02-10.
 */
@Named("customerBean")
@SessionScoped
public class CustomerBean implements Serializable {

    private CustomerEntity customer;

    public CustomerBean() {
    }
}
