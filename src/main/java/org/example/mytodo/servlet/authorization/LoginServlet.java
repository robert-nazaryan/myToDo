package org.example.mytodo.servlet.authorization;

import org.example.mytodo.manager.UserManager;
import org.example.mytodo.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private final UserManager userManager = new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = userManager.get(email);
        if (user != null && password.equals(user.getPassword())) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/todo");
        } else {
            req.getSession().setAttribute("msg", "Invalid email or password!");
            resp.sendRedirect("/");
        }
    }
}
