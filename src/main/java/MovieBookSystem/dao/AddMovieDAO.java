package MovieBookSystem.dao;


import MovieBookSystem.util.HibernateUtil;
import model.AddMovie;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AddMovieDAO {

    // Add a new movie
    public static void saveMovie(AddMovie movie) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(movie);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Get all Movies
    public static List<AddMovie> getAllMovies() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from AddMovie", AddMovie.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get an appointment by ID
    public static AddMovie getMoviesById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(AddMovie.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Update an existing movie
    public static void updateMovie(AddMovie movies) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(movies);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Delete an appointment by ID
    public static void deleteMovies(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            AddMovie movies = session.get(AddMovie.class, id);
            if (movies != null) {
                transaction = session.beginTransaction();
                session.delete(movies);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
