package back.Dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import back.Models.Semester;
import back.Models.Student;
import back.Models.Studentregistration;

public class StudentregistrationDAO {

	
    public boolean saveOrUpdateStudentregistration(Studentregistration studentregistration) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(studentregistration);
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


    public boolean deleteStudentregistration(Studentregistration studentregistration) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.delete(studentregistration);
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

    
    public Studentregistration getStudentregistrationById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Studentregistration studentregistration = null;

        try {
            String hql = "SELECT reg FROM Studentregistration reg " +
                    "LEFT JOIN FETCH reg.student " + // Eager load the student
                    "LEFT JOIN FETCH reg.semester " + // Eager load the semester
                    "LEFT JOIN FETCH reg.academicunit " + // Eager load the academicunit
                    "WHERE reg.id = :id";

            studentregistration = (Studentregistration) session.createQuery(hql)
           .setParameter("id", id)
           .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return studentregistration;
    }
    
    
    public List<Studentregistration> getAllStudentregistrations() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Studentregistration> registrations = null;
        try {
            String sql = "SELECT reg FROM Studentregistration reg " +
                    "LEFT JOIN FETCH reg.student " +
                    "LEFT JOIN FETCH reg.semester " +
                    "LEFT JOIN FETCH reg.academicunit";
        	registrations = session.createQuery(sql).list();
        	
        	session.close();
        	return registrations;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
        	return null;
        }
    }
    
    public List<Studentregistration> getAllregistrationForStudent(Student student) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Studentregistration> registrations = null;
        try {
        	registrations = session.createQuery("SELECT reg FROM Studentregistration reg where reg.Student = :student")
        			.setParameter("student", registrations).list();
        	session.close();
        	return registrations;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
        	return null;
        }
    }


}
