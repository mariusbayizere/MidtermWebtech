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
import back.Dao.CoursedefinitionDAO;
import back.Dao.SemesterDAO;
import back.Dao.StudentDAO;
import back.Dao.StudentregistrationDAO;
import back.Dao.TeacherDAO;
import back.Models.Academicunit;
import back.Models.Course;
import back.Models.Coursedefinition;
import back.Models.Semester;
import back.Models.Student;
import back.Models.Studentregistration;
import back.Models.Teacher;

/**
 * Servlet implementation class AssignCourse
 */
public class AssignCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AcademicUnitDAO dao = new AcademicUnitDAO();
		SemesterDAO semdao = new SemesterDAO();
		CoursedefinitionDAO defdao = new CoursedefinitionDAO();
		TeacherDAO tchdao = new TeacherDAO();
		
		Teacher std = tchdao.getTeacherByCode(request.getParameter("id"));
		
		List<Semester> semesters = semdao.getAllSemesters();
		List<Academicunit> departments = dao.getAcademicUnitsByType("DEPARTMENT");
		List<Coursedefinition> courses = defdao.getAllCoursedefinitions();
		
		request.setAttribute("semesters", semesters);
		request.setAttribute("departments", departments);
		request.setAttribute("courses", courses);
		request.setAttribute("teacher", std);
		
		RequestDispatcher dsp = request.getRequestDispatcher("assign-course.jsp");
		dsp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String department = request.getParameter("department");
		String semester = request.getParameter("semester");
		String teacher = request.getParameter("teacher");
		String course = request.getParameter("course");
		
		AcademicUnitDAO dao = new AcademicUnitDAO();
		SemesterDAO semdao = new SemesterDAO();
		CoursedefinitionDAO defdao = new CoursedefinitionDAO();
		TeacherDAO tchdao = new TeacherDAO();
		CourseDAO crsdao = new CourseDAO();
		
		Academicunit acd = dao.getAcadentunitByCode(Integer.parseInt(department));
		Semester sem = semdao.getSemesterById(semester);
		Coursedefinition def = defdao.getCoursedefinitionByCode(course);
		Teacher tcr = tchdao.getTeacherByCode(teacher);
		
		Course crs = new Course(tcr, def, acd);
		
		if(crsdao.saveOrUpdateCourse(crs)){
			response.sendRedirect("admin.jsp");
		}else {
			response.sendError(500, "Failed to Assign course");
		}
	}

}
