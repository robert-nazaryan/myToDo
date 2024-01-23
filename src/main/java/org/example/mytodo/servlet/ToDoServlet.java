package org.example.mytodo.servlet;

import org.example.mytodo.manager.ToDoManager;
import org.example.mytodo.manager.UserManager;
import org.example.mytodo.model.ToDo;
import org.example.mytodo.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/todo")
public class ToDoServlet extends HttpServlet {
    private final ToDoManager toDoManager = new ToDoManager();
    private final UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<ToDo> todoList = toDoManager.getByUserId(user.getId());
        req.setAttribute("todoList", todoList);
        req.getRequestDispatcher("/WEB-INF/todo.jsp").forward(req, resp);
    }
}
