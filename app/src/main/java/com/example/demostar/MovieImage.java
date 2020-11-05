package com.example.demostar;

import android.widget.Toast;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Comparator;

@Entity(tableName = "Movies")
public class MovieImage  {
    @PrimaryKey(autoGenerate = true)
    int uid;

    @ColumnInfo(name = "image_name")
    String movieName;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    byte[] movieImage;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public byte[] getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(byte[] movieImage) {
        this.movieImage = movieImage;
    }


}
