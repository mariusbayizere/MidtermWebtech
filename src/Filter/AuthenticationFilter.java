//package Filter;
//
//import java.io.IOException;
//import java.time.Duration;
//import java.time.LocalDateTime;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import backend.models.User;
//
//@WebFilter("/*")
//public class AuthenticationFilter implements Filter {
//    private static final long TIMEOUT_IN_MILLIS = 60000; // 1 minute
//
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        HttpSession session = httpRequest.getSession(false);
//
//        if (session != null && session.getAttribute("user") != null) {
//            LocalDateTime lastAccessedTime = (LocalDateTime) session.getAttribute("lastAccessed");
//            LocalDateTime currentTime = LocalDateTime.now();
//            Duration duration = Duration.between(lastAccessedTime, currentTime);
//            long elapsedMillis = duration.toMillis();
//
//            if (elapsedMillis > TIMEOUT_IN_MILLIS) {
//                session.invalidate();
//                // Log message when session timeout occurs
//                System.out.println("Session timeout detected. Redirecting to login page.");
//                httpResponse.sendRedirect("login.html"); // Redirect to login page if session timeout
//                return;
//            }
//
//            session.setAttribute("lastAccessed", currentTime); // Update last accessed time
//
//            // Continue with the filter chain
//            chain.doFilter(request, response);
//        } else {
//            // No active session or user object found, continue with the filter chain
//            chain.doFilter(request, response);
//        }
//    }
//
//    @Override
//    public void destroy() {
//        // Cleanup code if needed
//    }
//
//    @Override
//    public void init(FilterConfig arg0) throws ServletException {
//        // Initialization code if needed
//    }
//}




































//package Filter;
//import java.io.IOException;
//import java.time.Duration;
//import java.time.LocalDateTime;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import backend.models.User;
//
//@WebFilter("/*")
//public class AuthenticationFilter implements Filter {
//    private static final long TIMEOUT_IN_MILLIS = 60000; // 1 minute
//
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        HttpSession session = httpRequest.getSession(false);
//
//        if (session != null && session.getAttribute("user") != null) {
//            LocalDateTime lastAccessedTime = (LocalDateTime) session.getAttribute("lastAccessed");
//            LocalDateTime currentTime = LocalDateTime.now();
//            Duration duration = Duration.between(lastAccessedTime, currentTime);
//            long elapsedMillis = duration.toMillis();
//
//            if (elapsedMillis > TIMEOUT_IN_MILLIS) {
//                session.invalidate();
//                httpResponse.sendRedirect("login.html"); // Redirect to login page if session timeout
//                return;
//            }
//
//            session.setAttribute("lastAccessed", currentTime); // Update last accessed time
//
//            // Continue with the filter chain
//            chain.doFilter(request, response);
//        } else {
//            // No active session or user object found, continue with the filter chain
//            chain.doFilter(request, response);
//        }
//    }
//
//    @Override
//    public void destroy() {
//        // Cleanup code if needed
//    }
//
//    @Override
//    public void init(FilterConfig arg0) throws ServletException {
//        // Initialization code if needed
//    }
//}
