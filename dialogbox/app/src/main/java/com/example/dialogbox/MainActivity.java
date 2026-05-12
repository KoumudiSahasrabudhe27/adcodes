package com.example.dialogbox;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button btnAdd;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        btnAdd = findViewById(R.id.btnAdd);

        list = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        // ADD NEW ITEM
        btnAdd.setOnClickListener(v -> showDialog(null));

        // LONG PRESS → UPDATE / DELETE
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            selectedPosition = position;

            PopupMenu popup = new PopupMenu(MainActivity.this, view);
            popup.getMenu().add("Update");
            popup.getMenu().add("Delete");

            popup.setOnMenuItemClickListener(item -> {
                String title = item.getTitle().toString();

                if (title.equals("Update")) {
                    showDialog(list.get(selectedPosition));
                } else if (title.equals("Delete")) {
                    list.remove(selectedPosition);
                    adapter.notifyDataSetChanged();
                }
                return true;
            });

            popup.show();
            return true;
        });
    }

    // DIALOG (ADD / UPDATE)
    private void showDialog(String oldText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View view = getLayoutInflater().inflate(R.layout.dialog_input, null);
        EditText editText = view.findViewById(R.id.editText);

        if (oldText != null) {
            editText.setText(oldText);
        }

        builder.setView(view);

        builder.setPositiveButton("Save", (dialog, which) -> {
            String text = editText.getText().toString();

            if (oldText == null) {
                list.add(text); // ADD
            } else {
                list.set(selectedPosition, text); // UPDATE
            }

            adapter.notifyDataSetChanged();
        });

        builder.setNegativeButton("Cancel", null);

        builder.show();
    }
}