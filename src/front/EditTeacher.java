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
 * Servlet implementation class EditTeacher
 */
public class EditTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TeacherDAO dao = new TeacherDAO();
		String id = request.getParameter("id");
		Teacher tch = dao.getTeacherByCode(id);
		
		if (tch != null) {
			request.setAttribute("teacher", tch);
			RequestDispatcher dsp = request.getRequestDispatcher("edit-teacher.jsp");
			dsp.forward(request, response);
			
		}else {
			response.sendError(500, "Teacher retieval Failed");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TeacherDAO dao = new TeacherDAO();
		String RegNo = request.getParameter("regNo");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String qualification = request.getParameter("qualification");
		
		Teacher tch = new Teacher(RegNo, firstName, lastName, qualification);
		
		if(dao.saveOrUpdateTeacher(tch)) {
			response.sendRedirect("allteachers");
		}else {
			response.sendError(500, "faild to save Teacher");
		}
	}

}
