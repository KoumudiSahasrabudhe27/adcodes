package com.example.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText username, password, repassword;
    Button btnRegister, btnSignin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        btnRegister = findViewById(R.id.btnsignup);
        btnSignin = findViewById(R.id.btnsignin);

        DB = new DBHelper(this);

        btnRegister.setOnClickListener(v -> {
            String user = username.getText().toString();
            String pass = password.getText().toString();
            String repass = repassword.getText().toString();

            if(user.equals("") || pass.equals("") || repass.equals("")) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            } else {
                if(pass.equals(repass)) {
                    Boolean checkUser = DB.checkusername(user);
                    if(!checkUser) {
                        Boolean insert = DB.insertData(user, pass);
                        if(insert) {
                            Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(this, loginActivity.class));
                        } else {
                            Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "User already exists", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSignin.setOnClickListener(v ->
                startActivity(new Intent(this, loginActivity.class))
        );
    }
}