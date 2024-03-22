package front;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import back.Dao.AcademicUnitDAO;
import back.Dao.CourseDAO;
import back.Dao.SemesterDAO;
import back.Dao.StudentDAO;
import back.Dao.StudentregistrationDAO;
import back.Models.Academicunit;
import back.Models.Course;
import back.Models.Semester;
import back.Models.Student;
import back.Models.Studentregistration;

/**
 * Servlet implementation class DataFilterServlet
 */
@WebServlet("/DataFilterServlet/*")
public class DataFilterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private List<Student> filterStudentBySemester(String semester) {
        StudentDAO regdao = new StudentDAO();
        return regdao.filterStudentBySemester(semester);
    }
    
    private List<Student> filterStudentByDepartmentAndSemester(String semester, int department) {
        StudentDAO regdao = new StudentDAO();
        return regdao.filterStudentBySemesterAndDepartment(semester, department);
    }
    
    private List<Student> filterStudentByCourseAndSemester(String semester, int course) {
        StudentDAO regdao = new StudentDAO();
        return regdao.filterStudentBySemesterAndCourse(semester, course);
    }

    private List<Course> filterCourseBySemesterAndDepartment(String sem, int dep) {
        CourseDAO crsdao = new CourseDAO();
        return crsdao.getAllCoursesByDepartmentAndSemester(sem, dep);
    }
    
    private List<Course> filterCourseByStudent(String std) {
        CourseDAO crsdao = new CourseDAO();
        return crsdao.getAllCoursesByStudent(std);
    }

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sem = request.getParameter("semester");
        String dep = request.getParameter("department");
        String crs = request.getParameter("course");
        List<Student> std = null;
        
		CourseDAO crsdao = new CourseDAO();
		SemesterDAO semdao = new SemesterDAO();
		AcademicUnitDAO acddao = new AcademicUnitDAO();
        
		List<Course> courses = crsdao.getAllCourses();
		List<Semester> semesters = semdao.getAllSemesters();
		List<Academicunit> departments = acddao.getAcademicUnitsByType("DEPARTMENT");
        
		request.setAttribute("courses", courses);
		request.setAttribute("semesters", semesters);
		request.setAttribute("departments", departments);
        
        if(!sem.isEmpty()) {
        	if(!dep.isEmpty()) {
        		System.out.println("by dep, sem");
            	std = filterStudentByDepartmentAndSemester(sem,Integer.parseInt(dep));
        		request.setAttribute("studentList", std);	
        	}else if(!crs.isEmpty()) {
        		System.out.println("by coures, sem");
            	std = filterStudentByCourseAndSemester(sem, Integer.parseInt(crs));
        		request.setAttribute("studentList", std);        		
        	}else {
        		System.out.println("by sem");
            	std = filterStudentBySemester(sem);
      			request.setAttribute("studentList", std);
        	}
        }   
		RequestDispatcher dsp = request.getRequestDispatcher("all-students.jsp");
		dsp.forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sem = request.getParameter("semester");
        String dep = request.getParameter("department");
        String std = request.getParameter("student");
        List<Course> crs = null;
        
		StudentDAO stddao = new StudentDAO();
		SemesterDAO semdao = new SemesterDAO();
		AcademicUnitDAO acddao = new AcademicUnitDAO();
        
		List<Student> students = stddao.getAllStudents();
		List<Semester> semesters = semdao.getAllSemesters();
		List<Academicunit> departments = acddao.getAcademicUnitsByType("DEPARTMENT");
        
		request.setAttribute("students", students);
		request.setAttribute("semesters", semesters);
		request.setAttribute("departments", departments);
		
		if(!sem.isEmpty() && !dep.isEmpty() && std.isEmpty()) {
			crs = filterCourseBySemesterAndDepartment(sem, Integer.parseInt(dep));
			request.setAttribute("courseList", crs);
		}else if(!std.isEmpty()) {
			crs = filterCourseByStudent(std);
			request.setAttribute("courseList", crs);
		}

    	System.out.println(crs);    
		RequestDispatcher dsp = request.getRequestDispatcher("assigned-courses.jsp");
		dsp.forward(request, response);
    }

}
