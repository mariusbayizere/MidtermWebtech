package back.Dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import back.Models.Academicunit;

import java.util.List;

public class AcademicUnitDAO {


    public boolean saveOrUpdateAcadentunit(Academicunit academicunit) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(academicunit);
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


    public boolean deleteAcadentunit(int code) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Academicunit academicunit = (Academicunit) session.get(Academicunit.class, code);
            if (academicunit != null) {
                session.delete(academicunit);
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


    public Academicunit getAcadentunitByCode(int code) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Academicunit academicunit = null;

        try {
            academicunit = (Academicunit) session.get(Academicunit.class, code);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return academicunit;
    }

    // Method to retrieve all academic units
    public List<Academicunit> getAllAcadentunits() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Academicunit> academicunits = null;

        try {
            academicunits = session.createQuery("select acd from Acadentunit acd").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return academicunits;
    }
    
    public List<Academicunit> getAcademicUnitsByType(String academicType) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Academicunit> academicunits = null;
        try {
        	academicunits = session.createQuery("SELECT acd FROM Acadentunit acd WHERE acd.academicType = :academicType")
        			.setParameter("academicType", academicType).list();
        	session.close();
        	return academicunits;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
        	return null;
        }
    }
}
