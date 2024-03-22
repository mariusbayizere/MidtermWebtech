package back.Dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import back.Models.Course;
import back.Models.Semester;
import back.Models.Student;
import back.Models.Studentcourse;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class StudentcourseDAO {

    // Method to save a new student course
    public boolean saveOrUpdateStudentcourse(Studentcourse studentcourse) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(studentcourse);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            session.close();
            return true;
        }
    }


    // Method to delete a student course by its composite key (StudentcourseId)
    public boolean deleteStudentcourse(int studentcourseId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Studentcourse studentcourse = (Studentcourse) session.get(Studentcourse.class, studentcourseId);
            if (studentcourse != null) {
                session.delete(studentcourse);
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
            return true;
        }
    }

    // Method to retrieve a student course by its composite key (StudentcourseId)
    public Studentcourse getStudentcourseById(int studentcourseId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Studentcourse studentcourse = null;

        try {
            studentcourse = (Studentcourse) session.get(Studentcourse.class, studentcourseId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return studentcourse;
    }

    // Method to retrieve all student courses
    public List<Studentcourse> getAllStudentcourses() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Studentcourse> studentcourses = null;

        try {
            studentcourses = session.createQuery("select sdtcrs from Studentcourse stdcrs").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return studentcourses;
    }
    
    
}
