package com.rakibofc.recyclerflow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.rakibofc.recyclerflow.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static int ITEM_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        setContentView(binding.getRoot());
        viewModel.loadListData();

        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.tvTitle.setText(String.format(getString(R.string.total_data_size), ITEM_SIZE));

        viewModel.getListLiveData().observe(this, strings -> {

            MyAdapter myAdapter = new MyAdapter(strings);
            binding.recyclerview.setAdapter(myAdapter);
        });

        binding.fab.setOnClickListener(v -> recreate());
    }
}