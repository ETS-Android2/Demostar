package com.example.demostar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SelectedMovieActivity extends AppCompatActivity {
    TextView tvSelectedMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_movie);

        tvSelectedMovie = findViewById(R.id.selectedMovie);
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            MovieImage movieImage = (MovieImage) intent.getSerializableExtra("data");
            tvSelectedMovie.setText(movieImage.getMovieName());
            Toast.makeText(this,"u clicked"+movieImage.getMovieName().toString(),Toast.LENGTH_SHORT).show();
        }
    }
}