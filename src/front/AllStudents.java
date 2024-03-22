package front;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import back.Dao.AcademicUnitDAO;
import back.Dao.CourseDAO;
import back.Dao.SemesterDAO;
import back.Dao.StudentDAO;
import back.Models.Academicunit;
import back.Models.Course;
import back.Models.Semester;

/**
 * Servlet implementation class AllStudents
 */
public class AllStudents extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentDAO dao = new StudentDAO();
		CourseDAO crsdao = new CourseDAO();
		SemesterDAO semdao = new SemesterDAO();
		AcademicUnitDAO acddao = new AcademicUnitDAO();
		
		List<Course> courses = crsdao.getAllCourses();
		List<Semester> semesters = semdao.getAllSemesters();
		List<Academicunit> departments = acddao.getAcademicUnitsByType("DEPARTMENT");
		
		request.setAttribute("courses", courses);
		request.setAttribute("semesters", semesters);
		request.setAttribute("departments", departments);
		request.setAttribute("studentList", dao.getAllStudents());
		
		RequestDispatcher dsp = request.getRequestDispatcher("all-students.jsp");
		dsp.forward(request, response);
	}
}
