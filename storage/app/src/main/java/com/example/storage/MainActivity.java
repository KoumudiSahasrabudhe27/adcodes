package com.example.storage;

import android.os.Bundle;
import android.os.Environment;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.io.*;

public class MainActivity extends AppCompatActivity {

    EditText title, content;
    RadioButton internal, external;
    Button btnSave, btnLoad;

    String fileName = "data.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.title);
        content = findViewById(R.id.content);
        internal = findViewById(R.id.internal);
        external = findViewById(R.id.external);
        btnSave = findViewById(R.id.btnSave);
        btnLoad = findViewById(R.id.btnLoad);

        btnSave.setOnClickListener(v -> saveData());
        btnLoad.setOnClickListener(v -> loadData());
    }

    private void saveData() {
        String data = title.getText().toString() + "\n" +
                content.getText().toString();

        try {
            if (internal.isChecked()) {
                FileOutputStream fos = openFileOutput(fileName, MODE_PRIVATE);
                fos.write(data.getBytes());
                fos.close();
                Toast.makeText(this, "Saved in Internal Storage", Toast.LENGTH_SHORT).show();
            } else {
                File file = new File(getExternalFilesDir(null), fileName);
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(data.getBytes());
                fos.close();
                Toast.makeText(this, "Saved in External Storage", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        try {
            FileInputStream fis;

            if (internal.isChecked()) {
                fis = openFileInput(fileName);
                Toast.makeText(this, "Loaded from Internal", Toast.LENGTH_SHORT).show();
            } else {
                File file = new File(getExternalFilesDir(null), fileName);
                fis = new FileInputStream(file);
                Toast.makeText(this, "Loaded from External", Toast.LENGTH_SHORT).show();
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line;
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

            fis.close();

            String[] parts = sb.toString().split("\n", 2);

            if (parts.length > 0) title.setText(parts[0]);
            if (parts.length > 1) content.setText(parts[1]);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}