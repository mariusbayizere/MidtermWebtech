package front;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import back.Dao.AcademicUnitDAO;
import back.Models.Academicunit;

/**
 * Servlet implementation class AllAcademicUnit
 */
public class AllAcademicUnit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AcademicUnitDAO dao = new AcademicUnitDAO();
		List<Academicunit> academics = dao.getAllAcadentunits();
		request.setAttribute("academicList", academics);
		
		RequestDispatcher dsp = request.getRequestDispatcher("all-academics.jsp");
		dsp.forward(request, response);
	}

}
