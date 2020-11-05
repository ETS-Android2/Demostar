package com.example.demostar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdminActivity extends AppCompatActivity {
    // button for admin login
    Button btAdminLogIn ;

    // EditText for admin email and password
    EditText etAdminLogin , etAdminPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        //assigning values according to their purpose
        btAdminLogIn = findViewById(R.id.btnAdminSignIn);
        etAdminLogin = findViewById(R.id.edtAdminLoginEmail);
        etAdminPwd = findViewById(R.id.edtAdminLoginPwd);

        btAdminLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, HomeActivity.class));
            }
        });
    }
}