package com.yousef.sharedpref;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    TextView tVName, tVEmail, tVReceiveUpdates;
    Button btnClear;

    UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initComponents();

        userPreferences = new UserPreferences(this);

        // 🔹 Get data using your class
        String name = userPreferences.getName();
        String email = userPreferences.getEmail();
        boolean updates = userPreferences.isReceiveUpdates();

        tVName.setText("Name: " + name);
        tVEmail.setText("Email: " + email);
        tVReceiveUpdates.setText("Receive Updates: " + updates);

        // 🔹 Clear button
        btnClear.setOnClickListener(v -> {
            userPreferences.clear(); // remove all data

            // go back to MainActivity
            Intent intent = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void initComponents() {
        tVName = findViewById(R.id.tVName);
        tVEmail = findViewById(R.id.tVEmail);
        tVReceiveUpdates = findViewById(R.id.tVReceiveUpdates);
        btnClear = findViewById(R.id.btnClear);
    }
}