package back.Dao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import back.Models.Teacher;

import java.util.List;

public class TeacherDAO {


    public boolean saveOrUpdateTeacher(Teacher teacher) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(teacher);
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

    public boolean deleteTeacher(String regNo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Teacher teacher = (Teacher) session.get(Teacher.class, regNo);
            if (teacher != null) {
                session.delete(teacher);
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


    public Teacher getTeacherByCode(String code) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Teacher teacher = null;

        try {
            teacher = (Teacher) session.get(Teacher.class, code);
            session.close();
            return teacher;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return null;
        }
    }


    public List<Teacher> getAllTeachers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Teacher> teachers = null;

        try {
            teachers = session.createQuery("select tch from Teacher tch").list();
            session.close();
            return teachers;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return null;
        }
    }
}
