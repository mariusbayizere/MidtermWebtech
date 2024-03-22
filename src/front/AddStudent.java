package front;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import back.Dao.StudentDAO;
import back.Models.Student;

/**
 * Servlet implementation class AddStudent
 */
public class AddStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dsp = request.getRequestDispatcher("add-student.jsp");
		dsp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentDAO dao = new StudentDAO();
		String RegNo = request.getParameter("regNo");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String dob = request.getParameter("DOB");
		
		Student std = new Student();
		std.setDateOfBirth(dob);
		std.setFirstName(firstName);
		std.setLastName(lastName);
		std.setRegNo(RegNo);
		
		if(dao.saveOrUpdateStudent(std)) {
			response.sendRedirect("allstudents");
		}else {
			response.sendError(500, "faild to save student");
		}
	}

}
