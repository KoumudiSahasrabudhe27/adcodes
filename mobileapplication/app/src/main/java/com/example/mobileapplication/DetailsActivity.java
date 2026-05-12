package com.example.mobileapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        output = findViewById(R.id.output);

        String name = getIntent().getStringExtra("name");
        String subject = getIntent().getStringExtra("subject");
        String gender = getIntent().getStringExtra("gender");
        String qual = getIntent().getStringExtra("qualification");

        output.setText(name + "\n" + subject + "\n" + gender + "\n[" + qual + "]");
    }

    public void goBack(View view) {
        finish();
    }
}