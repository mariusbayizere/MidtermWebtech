package back.Dao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import back.Models.Course;
import back.Models.Semester;

import java.util.List;

public class CourseDAO {

    public boolean saveOrUpdateCourse(Course course) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(course);
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

    public boolean deleteCourse(int courseId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Course course = (Course) session.get(Course.class, courseId);
            if (course != null) {
                session.delete(course);
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

    public Course getCourseById(int courseId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Course course = null;

        try {
            course = (Course) session.get(Course.class, courseId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return course;
    }

    
    public List<Course> getAllCourses() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Course> courses = null;

        try {
            String sql = "SELECT crs FROM Course crs " +
                         "LEFT JOIN FETCH crs.academicunit " + // Eager load the academicunit
                         "LEFT JOIN FETCH crs.coursedefinition " + // Eager load the coursedefinition
                         "LEFT JOIN FETCH crs.teacher " + // Eager load the teacher
                         "LEFT JOIN FETCH crs.semester"; // Eager load the semester

            courses = session.createQuery(sql).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return courses;
    }
    
    public List<Course> getAllCoursesByStudent(String studentid) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Course> courses = null;

        try {
        	String sql = "SELECT DISTINCT crs "
        			+ "FROM Course crs "
        			+ "JOIN crs.studentcourses stdcrs "
        			+ "JOIN stdcrs.studentregistration reg "
        			+ "JOIN reg.student std "
        			+ "LEFT JOIN FETCH crs.academicunit "
        			+ "LEFT JOIN FETCH crs.coursedefinition "
        			+ "LEFT JOIN FETCH crs.semester "
        			+ "LEFT JOIN FETCH crs.teacher "
        			+ "WHERE std.regNo = :studentID";

        	courses = session.createQuery(sql).setParameter("studentID", studentid).list();
        	
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return courses;
    }
    
    public List<Course> getAllCoursesByDepartmentAndSemester(String sem, int dep) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Course> courses = null;

        try {
        	String sql = "SELECT DISTINCT crs "
        			+ "FROM Course crs "
        			+ "LEFT JOIN FETCH crs.academicunit "
        			+ "LEFT JOIN FETCH crs.coursedefinition "
        			+ "LEFT JOIN FETCH crs.semester "
        			+ "LEFT JOIN FETCH crs.teacher "
        			+ "WHERE crs.academicunit.unitId = :dep AND crs.semester.id = :sem";

        	courses = session.createQuery(sql).setParameter("dep", dep).setParameter("sem", sem).list();
        	
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return courses;
    }
}
