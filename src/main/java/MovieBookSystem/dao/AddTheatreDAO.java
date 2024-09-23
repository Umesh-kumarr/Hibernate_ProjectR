package MovieBookSystem.dao;



import MovieBookSystem.util.HibernateUtil;
import model.AddTheatre;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AddTheatreDAO {

    // Save Theatre to Database
    public static void saveTheatre(AddTheatre theatre) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(theatre);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Update theatre in Database
    public static void updateTheatre(AddTheatre theatre) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(theatre);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Delete theatre from Database
    public static void deleteTheatre(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            AddTheatre theatre = session.get(AddTheatre.class, id);
            if (theatre != null) {
                session.delete(theatre);
                System.out.println("Theatre deleted successfully.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Get theatre by ID
    public static AddTheatre getTheatreById(int id) {
        Transaction transaction = null;
        AddTheatre theatre = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            theatre = session.get(AddTheatre.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return theatre;
    }

    // Get All theatres
    public static List<AddTheatre> getAllTheatre() {
        Transaction transaction = null;
        List<AddTheatre> theatres = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<AddTheatre> query = session.createQuery("FROM Theatre", AddTheatre.class);
            theatres = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return theatres;
    }
}
