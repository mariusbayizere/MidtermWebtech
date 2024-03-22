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
import back.Dao.CoursedefinitionDAO;
import back.Dao.SemesterDAO;
import back.Dao.StudentDAO;
import back.Models.Semester;

/**
 * Servlet implementation class AllCourses
 */
public class AllCourses extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CoursedefinitionDAO dao = new CoursedefinitionDAO();

		request.setAttribute("courseList", dao.getAllCoursedefinitions());
		
		RequestDispatcher dsp = request.getRequestDispatcher("all-courses.jsp");
		dsp.forward(request, response);
	}

}
