package com.example.demostar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserRegActivity extends AppCompatActivity {
    // button for user sign up
    Button btSignupUser;

    //button for navigating from reg to login
    Button btLoginUserNav;

    // user informations
    EditText etUserName , etUserEmail , etUserMobile , etUserPwd ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reg);

        //assiging id according their action
        btSignupUser = findViewById(R.id.btnSignupUser);
        btLoginUserNav = findViewById(R.id.btnLoginUserNav);
        etUserName = findViewById(R.id.edtUserSignupName);
        etUserEmail = findViewById(R.id.edtUserSignupEmail);
        etUserMobile = findViewById(R.id.edtUserSignupMobile);
        etUserPwd = findViewById(R.id.edtUserSignupPwd);

        btLoginUserNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserRegActivity.this, UserLoginAcitivity.class));
            }
        });

        btSignupUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //creating user Entity
                final UserEntity userEntity = new UserEntity();
                userEntity.setName(etUserName.getText().toString());
                userEntity.setEmail(etUserEmail.getText().toString());
                userEntity.setPassword(etUserPwd.getText().toString());
                userEntity.setMobile(etUserMobile.getText().toString());

                if(validateInput(userEntity))
                {
                    //do insert operation
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    final UserDao userDao = userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //register user
                            userDao.registerUser(userEntity);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(UserRegActivity.this,"User Registered Successfully!",Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(UserRegActivity.this,UserLoginAcitivity.class));
                                }
                            });

                        }
                    }).start();

                }
                else{
                    Toast.makeText(UserRegActivity.this,"Fill all Fields",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Boolean validateInput(UserEntity userEntity)
    {
        if(userEntity.getName().isEmpty() || userEntity.getPassword().isEmpty() || userEntity.getEmail().isEmpty() || userEntity.getMobile().isEmpty())
            return false;

        return true;
    }
}