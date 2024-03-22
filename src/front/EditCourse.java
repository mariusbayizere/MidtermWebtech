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
 * Servlet implementation class EditCourse
 */
public class EditCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CoursedefinitionDAO dao = new CoursedefinitionDAO();
		String id = request.getParameter("id");
		Coursedefinition std = dao.getCoursedefinitionByCode(id);
		
		if (std != null) {
			request.setAttribute("course", std);
			RequestDispatcher dsp = request.getRequestDispatcher("edit-course.jsp");
			dsp.forward(request, response);
			
		}else {
			response.sendError(500, "Course retieval Failed");
		}
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
