package front;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import back.Dao.SemesterDAO;

/**
 * Servlet implementation class DeleteSemester
 */
public class DeleteSemester extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SemesterDAO dao = new SemesterDAO();
		
		if (dao.deleteSemester(request.getParameter("id"))) {
			response.sendRedirect("allsemesters");
		}else {
			response.sendError(500, "Deletion Failed");
		}
	}

}
