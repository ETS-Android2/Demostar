package com.example.demostar;

import android.widget.RatingBar;
import android.widget.Toast;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

@Entity(tableName = "Movies")
public class MovieImage implements Serializable  {
    @PrimaryKey(autoGenerate = true)
    int uid;

    @ColumnInfo(name = "movie_name")
    String movieName;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    byte[] movieImage;

    @ColumnInfo(name = "movie_rating")
    String movieRating;

    @ColumnInfo(name = "movie_genre")
    String movieGenre;

    @ColumnInfo(name = "movie_language")
    String movieLanguage;

    @ColumnInfo(name = "movie_year")
    String movieYear;


    public String getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(String movieYear) {
        this.movieYear = movieYear;
    }

    public static final Comparator<MovieImage> A_Z = new Comparator<MovieImage>() {
        @Override
        public int compare(MovieImage o1, MovieImage o2) {
            return o1.movieName.compareTo(o2.movieName);
        }
    };

    public static final Comparator<MovieImage> Z_A = new Comparator<MovieImage>() {
        @Override
        public int compare(MovieImage o1, MovieImage o2) {
            return o2.movieName.compareTo(o1.movieName);
        }
    };

    public static final Comparator<MovieImage> RATING = new Comparator<MovieImage>() {
        @Override
        public int compare(MovieImage o1, MovieImage o2) {
            return o2.getMovieRating().compareTo(o1.getMovieRating());
        }
    };

    public static final Comparator<MovieImage> RECENT = new Comparator<MovieImage>() {
        @Override
        public int compare(MovieImage o1, MovieImage o2) {
            return (int)(o2.getUid() - o1.getUid());
        }
    };

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

    public String getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(String movieRating) {
        this.movieRating = movieRating;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public String getMovieLanguage() {
        return movieLanguage;
    }

    public void setMovieLanguage(String movieLanguage) {
        this.movieLanguage = movieLanguage;
    }
}
