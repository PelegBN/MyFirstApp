package com.example.peleg.myfirstapp;

import java.util.List;

/**
 * Created by Peleg on 12/12/2015.
 */
public interface IDataAcces {
    List<TaskItem> getAllTasks();
    TaskItem addTask(TaskItem task);
    void removeTask(TaskItem task);
    TaskItem editTask(TaskItem task);

    TaskItem changeStatus(TaskItem task);
}
