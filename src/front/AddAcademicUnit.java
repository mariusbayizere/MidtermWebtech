package front;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import back.Dao.AcademicUnitDAO;
import back.Models.Academicunit;

/**
 * Servlet implementation class AddAcademicUnit
 */
public class AddAcademicUnit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AcademicUnitDAO dao = new AcademicUnitDAO();
		List<Academicunit> faculties = dao.getAcademicUnitsByType("FACULTY");
		List<Academicunit> programs = dao.getAcademicUnitsByType("PROGRAM");
		
		System.out.println(faculties);
		System.out.println(programs);
		
		request.setAttribute("faculties", faculties);
		request.setAttribute("programs", programs);
		
		RequestDispatcher dsp = request.getRequestDispatcher("add-academic.jsp");
		dsp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AcademicUnitDAO dao = new AcademicUnitDAO();
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String type = request.getParameter("academicType");
		String parentId = request.getParameter("parentId");
		
		Academicunit std = new Academicunit();
		std.setCode(code);
		std.setName(name);
		std.setEacademicUnit(type);
		
		if (parentId != null) {
			Academicunit parent = dao.getAcadentunitByCode(Integer.parseInt(parentId));
			std.setAcadentunit(parent);
		}
		
		if(dao.saveOrUpdateAcadentunit(std)) {
			response.sendRedirect("allacademicunit");
		}else {
			response.sendError(500, "faild to save Acadentunit");
		}
	}

}
