package back.Dao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import back.Models.Semester;

import java.util.List;

public class SemesterDAO {


    public boolean saveOrUpdateSemester(Semester semester) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(semester);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            session.close();
            return false;
        }
    }


    public boolean deleteSemester(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Semester semester = (Semester) session.get(Semester.class, id);
            if (semester != null) {
                session.delete(semester);
            }
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            session.close();
            return false;
        }
    }

    public Semester getSemesterById(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Semester semester = null;

        try {
            semester = (Semester) session.get(Semester.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return semester;
    }

    public List<Semester> getAllSemesters() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Semester> semesters = null;

        try {
            semesters = session.createQuery("select sem from Semester sem").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return semesters;
    }
}
