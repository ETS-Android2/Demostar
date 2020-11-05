package com.example.demostar;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

@Database(entities = MovieImage.class, version = 1, exportSchema = false)
public abstract class ImageDatabase extends RoomDatabase {
    private static ImageDatabase imageDatabase = null;
    private static final String databaseName = "movies";

    public abstract ImageDao imageDao();

    public static synchronized ImageDatabase getImageDatabase(Context context)
    {
        if(imageDatabase == null)
            imageDatabase = Room.databaseBuilder(context.getApplicationContext(),ImageDatabase.class,databaseName).
                            allowMainThreadQueries().build();

        return imageDatabase;
    }
}
