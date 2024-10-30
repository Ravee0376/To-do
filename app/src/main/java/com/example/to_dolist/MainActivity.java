package com.example.to_dolist;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up Edge-to-Edge behavior
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI components
        ListView listView = findViewById(R.id.listView);
        EditText inputText = findViewById(R.id.inputText);
        Button addButton = findViewById(R.id.addButton);

        // Initialize list and adapter
        items = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        // Add button click listener to add items
        addButton.setOnClickListener(view -> {
            String itemText = inputText.getText().toString();
            if (!itemText.isEmpty()) {
                items.add(itemText); // Add item to list
                adapter.notifyDataSetChanged(); // Update adapter
                inputText.setText(""); // Clear input field
            }
        });

        // Set long-click listener to delete items
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String removedItem = items.remove(position); // Remove the item
                adapter.notifyDataSetChanged(); // Update adapter
                Toast.makeText(MainActivity.this, "Deleted: " + removedItem, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
