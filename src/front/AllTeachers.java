package front;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import back.Dao.StudentDAO;
import back.Dao.TeacherDAO;

/**
 * Servlet implementation class AllTeachers
 */
public class AllTeachers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TeacherDAO dao = new TeacherDAO();
		
		request.setAttribute("teacherList", dao.getAllTeachers());
		
		RequestDispatcher dsp = request.getRequestDispatcher("all-teachers.jsp");
		dsp.forward(request, response);
	}

}
