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
import back.Models.Academicunit;
import back.Models.Semester;

/**
 * Servlet implementation class AssignedCourses
 */
public class AssignedCourses extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssignedCourses() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CourseDAO dao = new CourseDAO();
		SemesterDAO semdao = new SemesterDAO();
		AcademicUnitDAO acddao = new AcademicUnitDAO();
		StudentDAO stddao = new StudentDAO();
		
		List<Semester> semesters = semdao.getAllSemesters();
		List<Academicunit> departments = acddao.getAcademicUnitsByType("DEPARTMENT");

		
		request.setAttribute("semesters", semesters);
		request.setAttribute("departments", departments);
		request.setAttribute("students", stddao.getAllStudents());
		request.setAttribute("courseList", dao.getAllCourses());
		
		RequestDispatcher dsp = request.getRequestDispatcher("assigned-courses.jsp");
		dsp.forward(request, response);
	}

}
