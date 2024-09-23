package MovieBookSystem.dao;



import MovieBookSystem.util.HibernateUtil;
import model.Admin;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class AdminDAO {
    public static void saveadmin(Admin admin) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(admin);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static boolean authenticate(String username, String password) {
        Transaction transaction = null;
        Admin adminn = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Admin> query = session.createQuery("FROM Admin WHERE username = :username AND password = :password", Admin.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            adminn = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return adminn != null;
    }
}
