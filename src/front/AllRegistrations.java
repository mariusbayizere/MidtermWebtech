package front;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import back.Dao.CourseDAO;
import back.Dao.StudentregistrationDAO;
import back.Models.Course;
import back.Models.Studentregistration;

/**
 * Servlet implementation class AllRegistrations
 */
public class AllRegistrations extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentregistrationDAO regdao = new StudentregistrationDAO();
		
		List<Studentregistration> registrations = regdao.getAllStudentregistrations();

		request.setAttribute("registrations", registrations);
		
		RequestDispatcher dsp = request.getRequestDispatcher("all-registrations.jsp");
		dsp.forward(request, response);
			
	}

}
