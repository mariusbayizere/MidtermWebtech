package front;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import back.Dao.SemesterDAO;

/**
 * Servlet implementation class AllSemester
 */
public class AllSemesters extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SemesterDAO dao = new SemesterDAO();
		
		request.setAttribute("semesterList", dao.getAllSemesters());
		
		RequestDispatcher dsp = request.getRequestDispatcher("all-semesters.jsp");
		dsp.forward(request, response);
	}

}
