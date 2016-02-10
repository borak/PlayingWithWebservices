package database;

import model.CustomerEntity;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Kim on 2016-02-10.
 */
public class SakilaDatabaseImpl implements Serializable {

    public CustomerEntity getCustomerById(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        //session = sessionFactory.openSession();
        session.beginTransaction();
        List<CustomerEntity> result = session.createQuery("from CustomerEntity where id=" + id).list();
        session.getTransaction().commit();
        //session.close();
        return result.get(0);
    }

    public void newCustomer(CustomerEntity c) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(c);
        session.getTransaction().commit();
    }

}
