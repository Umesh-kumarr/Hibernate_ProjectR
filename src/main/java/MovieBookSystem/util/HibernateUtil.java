package MovieBookSystem.util;


import model.AddMovie;
import model.Admin;
import model.AddTheatre;
import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Admin.class)
                    .addAnnotatedClass(AddTheatre.class)
                    .addAnnotatedClass(AddMovie.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
