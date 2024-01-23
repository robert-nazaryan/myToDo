package org.example.mytodo.servlet;

import org.example.mytodo.enums.Status;
import org.example.mytodo.manager.ToDoManager;
import org.example.mytodo.model.ToDo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(urlPatterns = "/editToDo")
public class EditToDoServlet extends HttpServlet {
    private final ToDoManager toDoManager = new ToDoManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ToDo todo = toDoManager.get(Integer.parseInt(req.getParameter("todoId")));
        req.setAttribute("todo", todo);
        req.getRequestDispatcher("/WEB-INF/editToDo.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        toDoManager.update(ToDo.builder()
                .id(Integer.parseInt(req.getParameter("todoId")))
                .title(req.getParameter("todoTitle"))
                .finishedDate(Date.valueOf(req.getParameter("todoFinishDate")))
                .status(Status.valueOf(req.getParameter("todoStatus")))
                .build());
        resp.sendRedirect("/todo");
    }
}
