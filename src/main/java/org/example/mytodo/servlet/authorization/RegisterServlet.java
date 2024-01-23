package org.example.mytodo.servlet.authorization;

import org.example.mytodo.manager.UserManager;
import org.example.mytodo.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    private final UserManager userManager = new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        if (userManager.get(email) != null) {
            req.getSession().setAttribute("msg", "This email is already used!");
            resp.sendRedirect("/authorization");
            return;
        }
        userManager.add(User.builder()
                .name(req.getParameter("name"))
                .surname(req.getParameter("surname"))
                .email(email)
                .password(req.getParameter("password"))
                .build());
        req.getSession().setAttribute("msg", "You are registered!");
        resp.sendRedirect("/todo");
    }
}
