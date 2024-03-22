package front;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import back.Dao.TeacherDAO;
import back.Models.Teacher;

/**
 * Servlet implementation class AddTeacher
 */
public class AddTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dsp = request.getRequestDispatcher("add-teacher.jsp");
		dsp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TeacherDAO dao = new TeacherDAO();
		String code = request.getParameter("regNo");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String qualification = request.getParameter("qualification");
		
		Teacher tch = new Teacher(code, firstName, lastName, qualification);
		
		if(dao.saveOrUpdateTeacher(tch)) {
			response.sendRedirect("allteachers");
		}else {
			response.sendError(500, "faild to save teacher");
		}
	}

}
