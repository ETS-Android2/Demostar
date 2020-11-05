package com.example.demostar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class DataConverter {

    public static byte[] convertImageToByteArray(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,0,stream);
        return stream.toByteArray();
    }

    public static Bitmap convertByteArrayToImage(byte[] arr)
    {
        return BitmapFactory.decodeByteArray(arr,0,arr.length);
    }
}
