package front;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import back.Dao.CoursedefinitionDAO;
import back.Models.Coursedefinition;

/**
 * Servlet implementation class AddCourse
 */
public class AddCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dsp = request.getRequestDispatcher("add-course.jsp");
		dsp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CoursedefinitionDAO dao = new CoursedefinitionDAO();
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		
		Coursedefinition std = new Coursedefinition();
		std.setCode(code);
		std.setName(name);
		std.setDescription(description);
		
		if(dao.saveOrUpdateCoursedefinition(std)) {
			response.sendRedirect("allcourses");
		}else {
			response.sendError(500, "faild to save Course");
		}
	}

}
