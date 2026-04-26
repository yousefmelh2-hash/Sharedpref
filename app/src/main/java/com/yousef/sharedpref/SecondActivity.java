package com.yousef.sharedpref;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {
TextView tVName, tVEmail, tVReceiveUpdates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initComponents();
        SharedPreferences sp=getSharedPreferences("MyUserPrefs",MODE_PRIVATE);
        String name=sp.getString("name","");
        String email=sp.getString("email","");
        tVName.setText("Name: "+name);
        tVEmail.setText("Email: "+email);
        tVReceiveUpdates.setText("Receive Updates: "+sp.getBoolean("ReceiveUpdates",false));
    }

    private void initComponents() {
        tVName = findViewById(R.id.tVName);
        tVEmail = findViewById(R.id.tVEmail);
        tVReceiveUpdates=findViewById(R.id.tVReceiveUpdates);
    }
}