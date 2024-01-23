package org.example.mytodo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.mytodo.enums.Status;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToDo {
    private int id;
    private String title;
    private Date createdDate;
    private Date finishedDate;
    private User user;
    private Status status;
}
