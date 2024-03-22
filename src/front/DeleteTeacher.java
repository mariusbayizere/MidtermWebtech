package front;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import back.Dao.StudentDAO;
import back.Dao.TeacherDAO;

/**
 * Servlet implementation class DeleteTeacher
 */
public class DeleteTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TeacherDAO dao = new TeacherDAO();
		
		if (dao.deleteTeacher(request.getParameter("id"))) {
			response.sendRedirect("allteachers");
		}else {
			response.sendError(500, "Deletion Failed");
		}
	}

}
