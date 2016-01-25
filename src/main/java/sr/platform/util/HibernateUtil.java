package sr.platform.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by tantop01 on 18/01/16.
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();
//    private static final ServiceRegistry serviceRegistry;

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
//            return new Configuration().configure("/resources/hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    public synchronized static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
