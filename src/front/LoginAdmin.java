package front;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import back.*;
import back.Models.User;
import back.Dao.*;


@WebServlet("/LoginAdmin")
public class LoginAdmin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String username = request.getParameter("email");
            String password = request.getParameter("password");

            UserDao dao = new UserDao();
            User user = dao.getUserByUsername(username);

            if (user != null && user.getPassword().equals(password)) {
                HttpSession session = request.getSession(true);
                session.setAttribute("user", user);
                System.out.println("user"+ user);
                session.setAttribute("role", user.getRole());
                System.out.println("password "+ user.getPassword());
                session.setAttribute("lastAccessed", LocalDateTime.now());

                // Redirect based on user role
                String redirectPage = getRedirectPageForRole(user.getRole());
                response.sendRedirect(redirectPage);
            } else {
                request.setAttribute("error", "Incorrect username or password");
                request.getRequestDispatcher("login.html").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An unexpected error occurred");
            request.getRequestDispatcher("login.html").forward(request, response);
        }
    }

    private String getRedirectPageForRole(String role) {
        if (role == null || role.isEmpty()) {
            return "login.html"; 
        }
        else if (role.equalsIgnoreCase("admin")) {
            return "index.html"; 
        } 
    else if (role.equalsIgnoreCase("student")) {
            return "Addacadentunit.html"; 
        } else if (role.equalsIgnoreCase("teacher")) {
            return "AddLectureT.html";
        } else {
            return "index.html"; 
        }
    }
}
