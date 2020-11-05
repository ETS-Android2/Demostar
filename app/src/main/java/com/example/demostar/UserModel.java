package com.example.demostar;

import android.icu.lang.UScript;

import java.io.Serializable;
import java.util.Comparator;

public class UserModel implements Serializable {

    // initializing movie image and their corresponding image
    private int imageMovie;
    private String movieName;

    // constructor for assigning values
    public UserModel(String movieName, int imageMovie) {
        this.imageMovie = imageMovie;
        this.movieName = movieName;
    }

    // Getter and setter method for movie name and their image
    public int getImageMovie() {
        return imageMovie;
    }

    public void setImageMovie(int imageMovie) {
        this.imageMovie = imageMovie;
    }

    public String getUserName() {
        return movieName;
    }

    public void setUserName(String movieName) {
        this.movieName = movieName;
    }

    public static final Comparator<UserModel> BY_TITLE_ASCENDING = new Comparator<UserModel>() {
        @Override
        public int compare(UserModel o1, UserModel o2) {
            return o1.getUserName().compareTo(o2.getUserName());
        }
    };

    public static final Comparator<UserModel> BY_TITLE_DESCENDING = new Comparator<UserModel> (){
        @Override
        public int compare(UserModel o1, UserModel o2) {
            //Descending
            return o2.getUserName().compareTo(o1.getUserName());
        }
    };
}
