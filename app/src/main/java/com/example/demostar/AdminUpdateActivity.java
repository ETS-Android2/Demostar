package com.example.demostar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import android.Manifest;
import android.app.MediaRouteButton;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AdminUpdateActivity extends AppCompatActivity {

    ImageView imgUpload;
    EditText etAdminMovieName , etAdminMovieRating;
    EditText etAdminMovieYear;
    Spinner spinAdminMovieGenre, spinAdminMovieLanguage;
    String genre , language;
    private static int PICK_IMAGE = 123;
    Uri imagePath;
    Button btUpload , btNext;
    Bitmap bitmap;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update);

        imgUpload = findViewById(R.id.imgViewUpload);
        etAdminMovieName = findViewById(R.id.edtAdminUpdateMovieName);
        etAdminMovieRating = findViewById(R.id.edtAdminUpdateMovieRating);
        spinAdminMovieGenre = findViewById(R.id.spinnerGenre);
        spinAdminMovieLanguage = findViewById(R.id.spinnerLanguage);
        etAdminMovieYear = findViewById(R.id.edtAdminUpdateMovieYear);
        btUpload = findViewById(R.id.btnUpload);
        btNext = findViewById(R.id.btnNext);

        // for genre Spinner
        ArrayAdapter<CharSequence> genAdapter = ArrayAdapter.createFromResource(AdminUpdateActivity.this, R.array.Genre, android.R.layout.simple_spinner_item);
        genAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinAdminMovieGenre.setAdapter(genAdapter);
        String [] genres = {"Action", "Education", "Motivation", "Horror", "Adventure", "Thriller", "Sports", "Comedy", "Science Fiction"};
        spinAdminMovieGenre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                genre = genres[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        // For languages spinner
       ArrayAdapter<CharSequence> langAdapter = ArrayAdapter.createFromResource(AdminUpdateActivity.this, R.array.Languages, android.R.layout.simple_spinner_item);
        langAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinAdminMovieLanguage.setAdapter(langAdapter);
        String [] languages = {"English", "Tamil", "Telugu", "Malayalam", "Kannadam","Japanese"};
        spinAdminMovieLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                language = languages[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminUpdateActivity.this, HomeActivity.class));
            }
        });

        imgUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, PICK_IMAGE);
            }
        });

        final ImageDao imageDao = ImageDatabase.getImageDatabase(this).imageDao();

        btUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(etAdminMovieName.getText().toString().isEmpty() || imagePath == null)
               {
                   Toast.makeText(AdminUpdateActivity.this,"Fill all detials of images",Toast.LENGTH_SHORT).show();
               }
               else{
                   MovieImage movieImage = new MovieImage();
                   Bitmap bitmap = null;
                   try {
                       bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                       movieImage.setMovieImage(DataConverter.convertImageToByteArray(bitmap));
                       movieImage.setMovieName(etAdminMovieName.getText().toString());
                       movieImage.setMovieRating(etAdminMovieRating.getText().toString());
                       movieImage.setMovieGenre(genre);
                       movieImage.setMovieLanguage(language);
                       movieImage.setMovieYear(etAdminMovieYear.getText().toString());
                       imageDao.insertImage(movieImage);
                       Toast.makeText(AdminUpdateActivity.this,"Uploaded Successfully",Toast.LENGTH_LONG).show();
                   }
                   catch (IOException e) {
                       e.printStackTrace();
                   }
               }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData() != null){
            imagePath = data.getData();
            try {
                 bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                imgUpload.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }


}


