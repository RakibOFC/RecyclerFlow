package com.rakibofc.recyclerflow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.rakibofc.recyclerflow.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static int ITEM_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        setContentView(binding.getRoot());

        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.tvTitle.setText(String.format(getString(R.string.total_data_size), ITEM_SIZE));

        EmployeeRecyclerViewAdapter adapter = new EmployeeRecyclerViewAdapter(getEmployeeList());
        binding.recyclerview.setAdapter(adapter);

        /*viewModel.loadListData();
        viewModel.getListLiveData().observe(this, strings -> {

            MyAdapter myAdapter = new MyAdapter(strings);
            binding.recyclerview.setAdapter(myAdapter);
        });*/

        binding.fab.setOnClickListener(v -> {

            Log.e("Click", "Fab");
            recreate();
        });
        binding.fab2.setOnClickListener(v -> Log.e("Click", "Fab 2"));

        Log.e("Info", "End of code");
    }

    private List<Employee> getEmployeeList() {

        List<Employee> employeeList = new ArrayList<>();

        for (int i = 1; i <= ITEM_SIZE; i++)
            employeeList.add(new Employee(i, "Employee " + i, "Developer"));

        return employeeList;
    }
}