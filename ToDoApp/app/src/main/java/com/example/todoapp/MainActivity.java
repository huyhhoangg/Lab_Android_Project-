package com.example.todoapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.todoapp.model.Task;
import com.example.todoapp.viewmodel.TaskViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TaskViewModel taskViewModel;
    private EditText etTask;
    private Button btnAdd;
    private ListView lvTasks;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etTask = findViewById(R.id.etTask);
        btnAdd = findViewById(R.id.btnAdd);
        lvTasks = findViewById(R.id.lvTasks);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        lvTasks.setAdapter(adapter);

        // Khởi tạo
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        // Quan sát LiveData để cập nhật UI mỗi khi dữ liệu thay đổi
        taskViewModel.getTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                // Chuyển List<Task> sang List<String> để hiển thị
                ArrayList<String> taskNames = new ArrayList<>();
                for (Task task : tasks) {
                    taskNames.add(task.getName());
                }
                adapter.clear();
                adapter.addAll(taskNames);
                adapter.notifyDataSetChanged();
            }
        });

        btnAdd.setOnClickListener(v -> {
            String taskName = etTask.getText().toString().trim();
            taskViewModel.addTask(taskName);
            etTask.setText("");
        });

        lvTasks.setOnItemLongClickListener((parent, view, position, id) -> {
            taskViewModel.removeTask(position);
            return true;
        });
    }


}