package com.rakibofc.recyclerflow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.rakibofc.recyclerflow.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int ITEMS_PER_PAGE = 20; // Number of items to load per page
    private MainViewModel viewModel;
    private MyAdapter myAdapter;
    private int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        setContentView(binding.getRoot());

        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.tvTitle.setText(String.format(getString(R.string.total_data_size), MainViewModel.ITEM_SIZE));

        myAdapter = new MyAdapter(new ArrayList<>());
        binding.recyclerview.setAdapter(myAdapter);

        viewModel.getListLiveData().observe(this, strings -> {
            myAdapter.addData(strings);
        });

        binding.fab.setOnClickListener(v -> {

            Log.e("Info", "Click Fab");
            recreate();
        });

        binding.fab2.setOnClickListener(v -> {

            Log.e("Info", "Click Fab 2");
            Toast.makeText(this, "Fab 2", Toast.LENGTH_SHORT).show();
        });

        binding.recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                int totalItemCount = layoutManager.getItemCount();

                if (lastVisibleItemPosition == totalItemCount - 1) {
                    // Load more data when reaching the end of the list
                    loadMoreData();
                }
            }
        });

        loadMoreData(); // Initial load
    }

    private void loadMoreData() {
        viewModel.loadMoreListData(currentPage, ITEMS_PER_PAGE);
        currentPage++;
    }
}