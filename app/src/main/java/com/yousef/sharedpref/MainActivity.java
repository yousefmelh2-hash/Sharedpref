package com.yousef.sharedpref;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText eTName, eTEmail;
    Button button, buttonNextpage;
    String name, email;
    Boolean ReceiveUpdates;
    CheckBox chip, rememeberCheckbox;

    UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userPreferences = new UserPreferences(this);

        // ✅ Auto redirect if already logged in
        if (userPreferences.isLoggedIn()) {
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_main);
        initComponents();

        button.setOnClickListener(v -> {

            name = eTName.getText().toString();
            email = eTEmail.getText().toString();
            ReceiveUpdates = chip.isChecked();

            // ❌ Empty validation (important)
            if (email.isEmpty() || name.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // 🔹 If user doesn't exist → register
            if (!userPreferences.isUserRegistered()) {

                userPreferences.register(name, email, ReceiveUpdates);
                Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show();

            } else {

                // 🔹 login
                boolean success = userPreferences.login(name,email);

                if (!success) {
                    Toast.makeText(this, "One of the details is wrong", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
            }

            // 🔹 Remember me logic (IMPORTANT FIX)
            if (rememeberCheckbox.isChecked()) {
                // keep logged in (do nothing)
            } else {
                userPreferences.logout();
            }

            // 🔹 Go to second activity
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
            finish();
        });

        buttonNextpage.setOnClickListener(v -> {

            if (userPreferences.isLoggedIn()) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            } else {
                Toast.makeText(this, "Please login first", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void initComponents() {
        eTName = findViewById(R.id.eTName);
        eTEmail = findViewById(R.id.eTEmail);
        button = findViewById(R.id.button);
        buttonNextpage = findViewById(R.id.buttonNextpage);
        chip = findViewById(R.id.chip);
        rememeberCheckbox = findViewById(R.id.RememberCheckbox);
    }
}