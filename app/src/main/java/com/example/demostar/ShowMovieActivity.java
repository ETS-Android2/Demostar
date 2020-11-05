package com.example.demostar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class ShowMovieActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageDao imageDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_movie);

        recyclerView = findViewById(R.id.movieRecyclerView);
        imageDao = ImageDatabase.getImageDatabase(this).imageDao();
        ImageAdapter imageAdapter = new ImageAdapter(imageDao.getAllImage());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(imageAdapter);
    }
}