package com.example.demostar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // button for admin and users
    Button btAdminLogin , btUserLogin , btUserReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assigning id to their corresponding button
        btAdminLogin = findViewById(R.id.btnAdminLogin);
        btUserLogin = findViewById(R.id.btnUserLogin);
        btUserReg = findViewById(R.id.btnUserReg);

        // String name = getIntent().getStringExtra("name");

        // navigating to the next page according the user pressed the button
        // admin Login
        btAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AdminActivity.class));
            }
        });

        // user Login
        btUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, UserLoginAcitivity.class));
            }
        });

        // user Registration
        btUserReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, UserRegActivity.class));
            }
        });

    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
//        // to quit the app when user presses back button
//        Intent start = new Intent(Intent.ACTION_MAIN);
//        start.addCategory(Intent.CATEGORY_HOME);
//        start.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(start);
    }
}