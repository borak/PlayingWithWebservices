package database;

import model.CustomerEntity;
import model.StaffEntity;
import model.StoreEntity;
import model.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Kim on 2016-02-10.
 */
public class SakilaDatabase implements Serializable {

    private static final SakilaDatabase instance = new SakilaDatabase();
    private ReentrantReadWriteLock customerLock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock staffLock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock storeLock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock userLock = new ReentrantReadWriteLock();
    private final SessionFactory sessionFactory;

    private SakilaDatabase() {
        sessionFactory = HibernateUtil.createFactory();
    }

    public static SakilaDatabase getInstance() {
        return instance;
    }

    public CustomerEntity getCustomerById(int id) {
        try {
            customerLock.readLock().lock();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            List<CustomerEntity> result = session.createQuery("from CustomerEntity where id=" + id).list();
            session.getTransaction().commit();
            session.close();
            return result.isEmpty() ? null : result.get(0);
        } finally {
            customerLock.readLock().unlock();
        }
    }

    public void newCustomer(CustomerEntity c) {
        try {
            customerLock.writeLock().lock();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(c);
            session.getTransaction().commit();
            session.close();
        } finally {
            customerLock.writeLock().unlock();
        }
    }

    public StoreEntity getStoreById(int id) {
        try {
            storeLock.readLock().lock();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            List<StoreEntity> result = session.createQuery("from StoreEntity where id=" + id).list();
            session.getTransaction().commit();
            session.close();
            return result.isEmpty() ? null : result.get(0);
        } finally {
            storeLock.readLock().unlock();
        }
    }


    public StaffEntity getManagerStaffById(int id) {
        try {
            staffLock.readLock().lock();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            List<StaffEntity> result = session.createQuery("from StaffEntity where id=" + id).list();
            session.getTransaction().commit();
            session.close();
            return result.isEmpty() ? null : result.get(0);
        } finally {
            staffLock.readLock().unlock();
        }
    }

    public UserEntity login(String username, String password) {
        try {
            userLock.readLock().lock();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            List<UserEntity> result = session.createQuery("from UserEntity where username=" + username
                    + " and password="+password).list();
            session.getTransaction().commit();
            session.close();
            return result.isEmpty() ? null : result.get(0);
        } finally {
            userLock.readLock().unlock();
        }
    }

    public boolean register(String username, String password, StaffEntity staffEntity, CustomerEntity customerEntity) {
        return false;
    }
}
