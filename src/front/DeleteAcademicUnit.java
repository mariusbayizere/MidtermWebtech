package front;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import back.Dao.AcademicUnitDAO;

/**
 * Servlet implementation class DeleteAcademicUnit
 */
public class DeleteAcademicUnit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AcademicUnitDAO dao = new AcademicUnitDAO();
		
		if (dao.deleteAcadentunit(Integer.parseInt(request.getParameter("id")))) {
			response.sendRedirect("allacademicunit");
		}else {
			response.sendError(500, "Deletion Failed");
		}
	}

}
