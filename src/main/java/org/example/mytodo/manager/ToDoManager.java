package org.example.mytodo.manager;

import org.example.mytodo.db.DBConnectionProvider;
import org.example.mytodo.enums.Status;
import org.example.mytodo.model.ToDo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToDoManager {
    private final Connection connection = DBConnectionProvider.getInstance().getConnection();
    private final UserManager userManager = new UserManager();

    public void update(ToDo toDo) {
        String query = "UPDATE todo SET title = ?, finish_date = ?, status = ? WHERE id =" + toDo.getId();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, toDo.getTitle());
            preparedStatement.setDate(2, toDo.getFinishedDate());
            preparedStatement.setString(3, String.valueOf(toDo.getStatus()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ToDo get(int id) {
        String query = "SELECT * FROM todo WHERE id=" + id;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                return (ToDo.builder()
                        .id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .createdDate(resultSet.getDate("created_date"))
                        .finishedDate(resultSet.getDate("finish_date"))
                        .user(userManager.get(resultSet.getInt("user_id")))
                        .status(Status.valueOf(resultSet.getString("status")))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void changeStatusToDone(int id) {
        String query = "UPDATE todo SET status = ? WHERE id =" + id;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, Status.DONE.name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ToDo> getByUserId(int userId) {
        String query = "SELECT * FROM todo WHERE user_id = " + userId;
        List<ToDo> todoList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                todoList.add(ToDo.builder()
                        .id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .createdDate(resultSet.getDate("created_date"))
                        .finishedDate(resultSet.getDate("finish_date"))
                        .user(userManager.get(userId))
                        .status(Status.valueOf(resultSet.getString("status")))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoList;
    }

    public void add(ToDo toDo) {
        String query = "INSERT INTO todo(title,created_date,finish_date,user_id,status) VALUES(?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, toDo.getTitle());
            preparedStatement.setDate(2, toDo.getCreatedDate());
            preparedStatement.setDate(3, toDo.getFinishedDate());
            preparedStatement.setInt(4, toDo.getUser().getId());
            preparedStatement.setString(5, toDo.getStatus().name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String query = "DELETE FROM todo WHERE id=" + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
