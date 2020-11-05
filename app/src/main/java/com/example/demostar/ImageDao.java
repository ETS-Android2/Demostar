package com.example.demostar;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ImageDao {
    @Query("Select * from Movies")
    List<MovieImage> getAllImage();

    @Insert
    void insertImage(MovieImage movieImage);

    @Update
    void updateImage(MovieImage movieImage);

    @Delete
    void deleteImage(MovieImage movieImage);

}
