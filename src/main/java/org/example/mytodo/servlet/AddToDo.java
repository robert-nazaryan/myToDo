package org.example.mytodo.servlet;

import org.example.mytodo.enums.Status;
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
import java.sql.Date;

@WebServlet(urlPatterns = "/addToDo")
public class AddToDo extends HttpServlet {
    private final ToDoManager toDoManager = new ToDoManager();
    private final UserManager userManager = new UserManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        Date createdDate = new java.sql.Date(new java.util.Date().getTime());
        Date finishDate = Date.valueOf(req.getParameter("finishDate"));
        User user = (User) req.getSession().getAttribute("user");
        int userId = user.getId();
        toDoManager.add(ToDo.builder()
                .title(title)
                .createdDate(createdDate)
                .finishedDate(finishDate)
                .user(userManager.get(userId))
                .status(Status.NEW)
                .build());
        resp.sendRedirect("/todo");
    }
}
