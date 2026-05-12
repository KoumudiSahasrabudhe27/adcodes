package com.example.mobileapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText name;
    Spinner spinner;
    RadioGroup genderGroup;
    CheckBox grad, postgrad, phd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        spinner = findViewById(R.id.spinner);
        genderGroup = findViewById(R.id.genderGroup);
        grad = findViewById(R.id.grad);
        postgrad = findViewById(R.id.postgrad);
        phd = findViewById(R.id.phd);

        String[] subjects = {"Computer Science", "IT", "ENTC", "Mechanical"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, subjects);

        spinner.setAdapter(adapter);
    }

    public void submitForm(View view) {
        String uname = name.getText().toString();
        String subject = spinner.getSelectedItem().toString();

        int selectedId = genderGroup.getCheckedRadioButtonId();
        String gender = "";

        if (selectedId != -1) {
            RadioButton rb = findViewById(selectedId);
            gender = rb.getText().toString();
        }

        String qualification = "";

        if (grad.isChecked()) qualification += "Graduate ";
        if (postgrad.isChecked()) qualification += "Post Graduate ";
        if (phd.isChecked()) qualification += "PhD ";

        Intent i = new Intent(MainActivity.this, DetailsActivity.class);
        i.putExtra("name", uname);
        i.putExtra("subject", subject);
        i.putExtra("gender", gender);
        i.putExtra("qualification", qualification);

        startActivity(i);
    }
}