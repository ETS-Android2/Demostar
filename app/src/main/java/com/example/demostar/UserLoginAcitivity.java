package com.example.demostar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserLoginAcitivity extends AppCompatActivity {

    // button for user sign up
    Button btSignupUserNav;

    //button for navigating from reg to login
    Button btLoginUser;

    // user credentials for login
    EditText etUserLoginMail , etUserLoginPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login_acitivity);

        //assigning id's according to their purpose
        btSignupUserNav = findViewById(R.id.btnSignupUserNav);
        btLoginUser = findViewById(R.id.btnSigninUser);
        etUserLoginMail = findViewById(R.id.edtUserLoginEmail);
        etUserLoginPwd = findViewById(R.id.edtUserLoginPwd);

        btSignupUserNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserLoginAcitivity.this, UserRegActivity.class));
            }
        });

        btLoginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String userEmail = etUserLoginMail.getText().toString();
                final String userPassword = etUserLoginPwd.getText().toString();
                //login validation
                if(userEmail.isEmpty() || userPassword.isEmpty())
                {
                    Toast.makeText(UserLoginAcitivity.this,"Fill all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    //perform query
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    final UserDao userDao = userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            UserEntity userEntity = userDao.login(userEmail, userPassword);
                            if(userEntity == null)
                            {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(UserLoginAcitivity.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            else{
                                String name = userEntity.name;
                                startActivity(new Intent(UserLoginAcitivity.this,HomeActivity.class).
                                putExtra("name",name));

                            }
                        }
                    }).start();
                }
            }
        });
    }
}