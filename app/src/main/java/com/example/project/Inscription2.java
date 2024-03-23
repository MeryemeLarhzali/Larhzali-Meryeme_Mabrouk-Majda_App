package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;
import java.util.List;

public class Inscription2 extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription2);
        dbHelper = new DatabaseHelper(this); // Initialiser DatabaseHelper
        setupSpinner();
    }

    public void creerCompte(View v) {
        EditText email = findViewById(R.id.email);
        EditText motdepasse = findViewById(R.id.motdepasse);
        EditText confirmermotdepasse = findViewById(R.id.confirmermdps);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        Spinner citySpinner = findViewById(R.id.citySpinner);

        String mail = email.getText().toString();
        String mdps = motdepasse.getText().toString();
        String confirmermdps = confirmermotdepasse.getText().toString();
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        String selectedCity = citySpinner.getSelectedItem().toString();

        if (mail.trim().isEmpty() || mdps.trim().isEmpty() || confirmermdps.trim().isEmpty() || selectedRadioButtonId == -1 || selectedCity.equals("")) {
            Toast.makeText(this, "Veuillez remplir tous les champs ", Toast.LENGTH_SHORT).show();
        } else if (!mdps.equals(confirmermdps)) {
            Toast.makeText(this, "Les mots de passe ne correspondent pas!", Toast.LENGTH_SHORT).show();
        } else {
            // Enregistrer l'utilisateur dans la base de données
            dbHelper.ajouterUtilisateur(mail, mdps);

            // Rediriger vers l'activité suivante
            Intent intent = new Intent(Inscription2.this, InterfaceAnnonce.class);
            startActivity(intent);
        }
    }

    private void setupSpinner() {
        List<String> cityNames = Arrays.asList("Casablanca", "Rabat", "Marrakech", "Fes", "Tangier", "Agadir", "Essaouira", "Chefchaouen", "Ouarzazate", "Fez");
        Spinner spinnerCity = findViewById(R.id.citySpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cityNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(adapter);
    }
}
