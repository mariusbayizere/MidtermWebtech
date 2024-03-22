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
import back.Dao.CourseDAO;
import back.Dao.SemesterDAO;
import back.Dao.StudentDAO;
import back.Dao.StudentcourseDAO;
import back.Dao.StudentregistrationDAO;
import back.Models.Course;
import back.Models.Semester;
import back.Models.Student;
import back.Models.Studentcourse;
import back.Models.Studentregistration;

/**
 * Servlet implementation class TakeCourse
 */
public class TakeCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentregistrationDAO regdao = new StudentregistrationDAO();
		CourseDAO crsdao = new CourseDAO();
		Studentregistration reg = regdao.getStudentregistrationById(Integer.parseInt(request.getParameter("id")));
		
		List<Course> courses = crsdao.getAllCourses();
		
		request.setAttribute("courses", courses);
		request.setAttribute("registration", reg);
		
		RequestDispatcher dsp = request.getRequestDispatcher("take-course.jsp");
		dsp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String course = request.getParameter("course");
		String registration = request.getParameter("regId");
		String credits = request.getParameter("credits");
		String result = request.getParameter("results");		
		
		StudentregistrationDAO regdao = new StudentregistrationDAO();
		CourseDAO crsdao = new CourseDAO();
		StudentcourseDAO stdcrsdao = new StudentcourseDAO();
		
		Course acd = crsdao.getCourseById(Integer.parseInt(course));
		Studentregistration reg = regdao.getStudentregistrationById(Integer.parseInt(registration));

		
		String crsId = "";
		Studentcourse stdcourse = new Studentcourse(crsId , acd, reg);
		
		if(stdcrsdao.saveOrUpdateStudentcourse(stdcourse)){
			response.sendRedirect("admin.jsp");
		}else {
			response.sendError(500, "Failed to take course");
		}
	}

}
