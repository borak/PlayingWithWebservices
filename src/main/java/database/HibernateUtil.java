package database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Kim on 2016-02-10.
 */
class HibernateUtil {

    static SessionFactory createFactory() {
        try {
            SessionFactory sessionFactory = new Configuration()
                    .configure()
                    .buildSessionFactory();
            return sessionFactory;
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

}
