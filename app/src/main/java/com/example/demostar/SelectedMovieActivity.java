package com.example.demostar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SelectedMovieActivity extends AppCompatActivity {
    TextView tvSelectedMovieName, tvSelectedMovieRating, tvSelectedMovieGenre ;
    TextView tvSelectedMovieLanguage, tvSelectedMovieYear;
    ImageView selectedMovieImg;
    Toolbar toolbar;
    RatingBar ratingBar;
    String message;
    MovieImage movieImage;
    ImageDao imageDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_movie);

        toolbar = findViewById(R.id.toolbar);

        // setting title as toolbar title
        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setTitle("");

        selectedMovieImg = findViewById(R.id.selectedMovieImage);
        tvSelectedMovieName = findViewById(R.id.selectedMovieName);
        tvSelectedMovieRating = findViewById(R.id.selectedMovieRating);
        tvSelectedMovieGenre = findViewById(R.id.selectedMovieGenre);
        tvSelectedMovieLanguage = findViewById(R.id.selectedMovieLanguage);
        tvSelectedMovieYear = findViewById(R.id.selectedMovieYear);
        ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setRating(load());


        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            MovieImage movieImage = (MovieImage) intent.getSerializableExtra("data");
            tvSelectedMovieName.setText(movieImage.getMovieName());
            tvSelectedMovieRating.setText(movieImage.getMovieRating());
            tvSelectedMovieGenre.setText(movieImage.getMovieGenre());
            tvSelectedMovieLanguage.setText(movieImage.getMovieLanguage());
            tvSelectedMovieYear.setText(movieImage.getMovieYear());
        //    selectedMovieImg.setImageResource(DataConverter.convertImageToByteArray(movieImage.getMovieImage()));

            Toast.makeText(this,"You Selected " + movieImage.getMovieName().toString(),Toast.LENGTH_SHORT).show();
        }

        imageDao = ImageDatabase.getImageDatabase(this).imageDao();

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                int rating = (int) v;
                message = null;


                switch (rating) {
                    case 1:
                        message = "WORST";
                        break;
                    case 2:
                        message = "BETTER";
                        break;
                    case 3:
                        message = "GOOD";
                        break;
                    case 4:
                        message = "GREAT";
                        break;
                    case 5:
                        message = "AWESOME";
                        break;
                }

                save(v);

            }
        });


    }

    public void save(float f)
    {
        Toast.makeText(this,""+ String.valueOf(f),Toast.LENGTH_SHORT).show();
        SharedPreferences sharedPreferences = getSharedPreferences("folder", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("rating",f);
        editor.commit();
    }
    public float load()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("folder", MODE_PRIVATE);
        float f = sharedPreferences.getFloat("rating",0f);
        return f;
    }


}