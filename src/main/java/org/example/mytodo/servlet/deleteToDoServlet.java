package org.example.mytodo.servlet;

import org.example.mytodo.manager.ToDoManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteToDo")
public class deleteToDoServlet extends HttpServlet {
    private final ToDoManager toDoManager = new ToDoManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        toDoManager.delete(Integer.parseInt(req.getParameter("todoId")));
        resp.sendRedirect("/todo");
    }
}
