package com.yousef.sharedpref;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.chip.Chip;

public class MainActivity extends AppCompatActivity {
EditText eTName, eTEmail;
Button button, buttonNextpage;
SharedPreferences sp;
String name, email;
Boolean ReceiveUpdates;
Chip chip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        sp=getSharedPreferences("MyUserPrefs",MODE_PRIVATE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        name=eTName.getText().toString();
        email=eTEmail.getText().toString();
        if (chip.isChecked()){ReceiveUpdates=true;}
        else {ReceiveUpdates=false;}
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("name",name);
        editor.putString("email",email);
        editor.putBoolean("ReceiveUpdates",ReceiveUpdates);
        editor.commit();
                Toast.makeText(MainActivity.this, "Info saved", Toast.LENGTH_SHORT).show();
            }
        });
        buttonNextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initComponents() {
        eTName = findViewById(R.id.eTName);
        eTEmail = findViewById(R.id.eTEmail);
        button = findViewById(R.id.button);
        buttonNextpage = findViewById(R.id.buttonNextpage);
        chip = findViewById(R.id.chip);

    }
}