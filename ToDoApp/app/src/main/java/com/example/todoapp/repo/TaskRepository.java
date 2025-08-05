package com.example.todoapp.repo;

import com.example.todoapp.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private List<Task> tasks = new ArrayList<>();

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(0, task); // Thêm đầu danh sách
    }

    public void removeTask(int position) {
        if (position >= 0 && position < tasks.size()) {
            tasks.remove(position);
        }
    }

}
