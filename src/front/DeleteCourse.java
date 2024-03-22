package front;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import back.Dao.CoursedefinitionDAO;

/**
 * Servlet implementation class DeleteCourse
 */
public class DeleteCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CoursedefinitionDAO dao = new CoursedefinitionDAO();
		
		if (dao.deleteCoursedefinition(request.getParameter("id"))) {
			response.sendRedirect("allcourses");
		}else {
			response.sendError(500, "Deletion Failed");
		}
	}

}
