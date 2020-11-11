package com.example.demostar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class HomeActivity extends AppCompatActivity{
    // Initialising the toolbar and recycler view for displaying movies
    Toolbar toolbar;
    RecyclerView recyclerView;
    ImageDao imageDao;
    ImageAdapter imageAdapter;
    List<MovieImage> movieList , adminMovieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //assigning id to their corresponding attributes
        recyclerView = findViewById(R.id.recyclerView);
        toolbar = findViewById(R.id.toolbar);

        // setting title as toolbar title
        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setTitle("");

        // movie images
        imageDao = ImageDatabase.getImageDatabase(this).imageDao();
        movieList = imageDao.getAllImage();
        imageAdapter = new ImageAdapter(movieList,this::selectedUser);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(imageAdapter);

    }

    // to create options on top right
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.searchView);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // filtering the data
                imageAdapter.getFilter().filter(s);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.searchView) {
            return true;
        }

        else if(id == R.id.filter)
        {
            //startActivity(new Intent(HomeActivity.this, AdminUpdateActivity.class));
            showFilterDialog();
            return true;
        }

        else if (id==R.id.action_sort) {
            showSortDialog();
            return true;
        }

        else if(id == R.id.logout)
        {
            showLogout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showFilterDialog() {
        String [] options = {"Genre", "Languages"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sort By");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(which == 0) {
                    showGenre();
                }
                else if(which == 1) {
                    showLanguages();
                }
            }
        });
        builder.create().show(); //show Dialog
    }
    private void showGenre() {
        String [] options = {"Action", "Education", "Motivation", "Horror", "Adventure", "Thriller", "Sports", "Comedy", "Science Fiction"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sort By");

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                imageAdapter.getFilter().filter(options[which]);
                imageAdapter.notifyDataSetChanged();
            }
        });
        builder.create().show(); //show Dialog
    }



    private void showLanguages() {
        String [] options = {"English", "Tamil", "Telugu", "Malayalam", "Kannadam","Japanese"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sort By");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                imageAdapter.getFilter().filter(options[which]);
                imageAdapter.notifyDataSetChanged();

            }
        });
        builder.create().show(); //show Dialog
    }


    private void showLogout() {
        startActivity(new Intent(HomeActivity.this,MainActivity.class));
        finish();
    }

    private void showSortDialog() {
        String [] options = {"Ascending","Descending","Rating","Recently Added"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sort By");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(which == 0) { //Ascending is clicked
                    Collections.sort(movieList, MovieImage.A_Z);
                    imageAdapter.notifyDataSetChanged();
                }

                if(which == 1) { //Descending is clicked
                    Collections.sort(movieList, MovieImage.Z_A);
                    imageAdapter.notifyDataSetChanged();
                }

                if(which == 2) { //Rating is clicked
                    Collections.sort(movieList, MovieImage.RATING);
                    imageAdapter.notifyDataSetChanged();
                }

                if(which == 3) { //Recently Added is clicked
                    Collections.sort(movieList, MovieImage.RECENT);
                    imageAdapter.notifyDataSetChanged();
                }
            }
        });
        builder.create().show(); //show Dialog
    }

    public void selectedUser(MovieImage movieImage) {
        startActivity(new Intent(HomeActivity.this, SelectedMovieActivity.class).
                putExtra("data",movieImage));
    }


}