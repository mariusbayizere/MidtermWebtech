package front;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

import back.Dao.SemesterDAO;
import back.Models.Semester;

/**
 * Servlet implementation class AddSemester
 */
public class AddSemester extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dsp = request.getRequestDispatcher("add-semester.jsp");
		dsp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SemesterDAO dao = new SemesterDAO();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		Date startDate = Date.valueOf(request.getParameter("startDate"));
		Date endDate = Date.valueOf(request.getParameter("endDate"));
		
		Semester std = new Semester();
		std.setId(id);
		std.setName(name);
		std.setStartDate(startDate);
		std.setEndDate(endDate);
		
		if(dao.saveOrUpdateSemester(std)) {
			response.sendRedirect("allsemesters");
		}else {
			response.sendError(500, "faild to save semester");
		}
	}

}
