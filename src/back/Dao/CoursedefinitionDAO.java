package back.Dao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import back.Models.Coursedefinition;

import java.util.List;

public class CoursedefinitionDAO {


    public boolean saveOrUpdateCoursedefinition(Coursedefinition coursedefinition) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(coursedefinition);
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


    public boolean updateCoursedefinition(Coursedefinition coursedefinition) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.update(coursedefinition);
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

    
    public boolean deleteCoursedefinition(String code) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Coursedefinition coursedefinition = (Coursedefinition) session.get(Coursedefinition.class, code);
            if (coursedefinition != null) {
                session.delete(coursedefinition);
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


    public Coursedefinition getCoursedefinitionByCode(String code) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Coursedefinition coursedefinition = null;

        try {
            coursedefinition = (Coursedefinition) session.get(Coursedefinition.class, code);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return coursedefinition;
    }


    public List<Coursedefinition> getAllCoursedefinitions() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Coursedefinition> coursedefinitions = null;

        try {
            coursedefinitions = session.createQuery("select crsdef from Coursedefinition crsdef").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return coursedefinitions;
    }
}
