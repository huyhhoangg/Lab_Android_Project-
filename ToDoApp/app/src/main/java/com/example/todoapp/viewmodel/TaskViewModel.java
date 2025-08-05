package com.example.todoapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.todoapp.model.Task;
import com.example.todoapp.repo.TaskRepository;

import java.util.List;


public class TaskViewModel extends ViewModel {
    private TaskRepository repository = new TaskRepository();
    private MutableLiveData<List<Task>> tasksLiveData = new MutableLiveData<>();

    public TaskViewModel() {
        tasksLiveData.setValue(repository.getTasks());
    }

    public LiveData<List<Task>> getTasks() {
        return tasksLiveData;
    }

    public void addTask(String name) {
        if (name == null || name.trim().isEmpty()) return;
        repository.addTask(new Task(name));
        tasksLiveData.setValue(repository.getTasks());
    }

    public void removeTask(int position) {
        repository.removeTask(position);
        tasksLiveData.setValue(repository.getTasks());
    }

}
