package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DatabaseHelper(this); // Initialiser DatabaseHelper
    }

    public void gererConnexion(View v) {
        EditText emailEditText = findViewById(R.id.email);
        EditText motdepasseEditText = findViewById(R.id.motdepasse);
        String mail = emailEditText.getText().toString();
        String password = motdepasseEditText.getText().toString();

        if (dbHelper.verifierConnexion(mail, password)) {
            Intent intent = new Intent(this, InterfaceAnnonce.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "L'email ou le mot de passe saisi est incorrect!", Toast.LENGTH_SHORT).show();
        }
    }

    public void inscrire(View v) {
        Intent intent = new Intent(MainActivity.this, Inscription2.class);
        startActivity(intent);
    }
}

