package back.Dao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import back.Models.Student;

import java.util.List;

public class StudentDAO {


    public boolean saveOrUpdateStudent(Student student) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(student);
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


    public boolean deleteStudent(String regNo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Student student = (Student) session.get(Student.class, regNo);
            if (student != null) {
                session.delete(student);
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


    public Student getStudentByRegNo(String regNo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Student student = null;

        try {
            student = (Student) session.get(Student.class, regNo);
            session.close();
            return student;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return null;
        }
    }


    public List<Student> getAllStudents() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Student> students = null;

        try {
            students = session.createQuery("select std from Student std").list();
            session.close();
            return students;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return null;
        }
    }
    
    public List<Student> filterStudentBySemester(String semId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Student> students = null;

        try {
        	String querry = "SELECT DISTINCT std " +
        			"FROM Student std " +
        			"INNER JOIN std.studentregistrations reg " +
        			"WHERE reg.semester.id = :semesterId ";
            students = session.createQuery(querry).setParameter("semesterId", semId).list();
            session.close();
            return students;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return null;
        }
    }
    
    public List<Student> filterStudentBySemesterAndDepartment(String semId, int departId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Student> students = null;

        try {
        	String querry = "SELECT DISTINCT std "+
        			"FROM Student std "+
        			"INNER JOIN std.studentregistrations reg "+
        			"WHERE reg.semester.id = :semId AND reg.academicunit.unitId = :departId ";
            students = session.createQuery(querry).setParameter("semId", semId)
            		.setParameter("departId", departId).list();
            session.close();
            return students;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return null;
        }
    }
    
    public List<Student> filterStudentBySemesterAndCourse(String semId, int courseId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Student> students = null;

        try {
        	String querry = "SELECT DISTINCT std "
        			+ "FROM Student std "
        			+ "INNER JOIN std.studentregistrations reg "
        			+ "INNER JOIN reg.studentcourses stdcrs "
        			+ "INNER JOIN stdcrs.course crs "
        			+ "WHERE crs.id = :courseId AND crs.semester.id = :semId";
            students = session.createQuery(querry).setParameter("semId", semId)
            		.setParameter("courseId", courseId).list();
            session.close();
            return students;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return null;
        }
    }
}
