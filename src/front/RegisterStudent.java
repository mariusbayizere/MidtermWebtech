package front;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import back.Dao.AcademicUnitDAO;
import back.Dao.SemesterDAO;
import back.Dao.StudentDAO;
import back.Dao.StudentregistrationDAO;
import back.Models.Academicunit;
import back.Models.Semester;
import back.Models.Student;
import back.Models.Studentregistration;

/**
 * Servlet implementation class RegisterStudent
 */
public class RegisterStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AcademicUnitDAO dao = new AcademicUnitDAO();
		SemesterDAO semdao = new SemesterDAO();
		StudentDAO stddao = new StudentDAO();
		Student std = stddao.getStudentByRegNo(request.getParameter("id"));
		List<Semester> semesters = semdao.getAllSemesters();
		List<Academicunit> departments = dao.getAcademicUnitsByType("DEPARTMENT");
		
		request.setAttribute("semesters", semesters);
		request.setAttribute("departments", departments);
		request.setAttribute("student", std);
		
		RequestDispatcher dsp = request.getRequestDispatcher("register.jsp");
		dsp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String department = request.getParameter("department");
		String semester = request.getParameter("semester");
		String student = request.getParameter("student");
		
		AcademicUnitDAO dao = new AcademicUnitDAO();
		SemesterDAO semdao = new SemesterDAO();
		StudentDAO stddao = new StudentDAO();
		StudentregistrationDAO regdao = new StudentregistrationDAO();
		
		Academicunit acd = dao.getAcadentunitByCode(Integer.parseInt(department));
		Semester sem = semdao.getSemesterById(semester);
		Student std = stddao.getStudentByRegNo(student);
		
		Studentregistration reg = new Studentregistration(std, sem, acd, Date.valueOf(LocalDate.now()));
		
		if(regdao.saveOrUpdateStudentregistration(reg)){
			response.sendRedirect("admin.jsp");
		}else {
			response.sendError(500, "Failed to register student");
		}
		
	}

}
