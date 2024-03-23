package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;
import java.util.List;

public class InterfaceAnnonce extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface_annonce);

        // Initialiser les spinners
        setupSpinners();
    }

    private void setupSpinners() {
        List<String> categories = Arrays.asList("Informatique et Technologies", "Ventes et Marketing", "Finance et Comptabilité", "Ressources Humaines", "Service à la clientèle", "Administration et Secrétariat", "Ingénierie", "Santé et Services sociaux");
        Spinner spinnerCategorie = findViewById(R.id.catSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategorie.setAdapter(adapter);

        List<String> secteur = Arrays.asList("Technologies de l'information et des communications (TIC)", "Vente au détail", "Services financiers", "Ressources Humaines", "Santé et bien-être", "Éducation et formation", "Fabrication", "Tourisme et hôtellerie");
        Spinner spinnerSecteur = findViewById(R.id.secteurSpinner);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, secteur);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSecteur.setAdapter(adapter1);

        List<String> citys = Arrays.asList("Casablanca", "Rabat", "Marrakech", "Fes", "Tangier", "Agadir", "Essaouira", "Chefchaouen", "Ouarzazate", "Fez");
        Spinner spinnerCity = findViewById(R.id.citySpinner);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, citys);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(adapter2);
    }

    public void creerAnnonce(View v) {
        Spinner secteurs = findViewById(R.id.secteurSpinner);
        EditText titre = findViewById(R.id.titre);
        EditText Contrat = findViewById(R.id.contrat);
        Spinner villes = findViewById(R.id.citySpinner);
        EditText description = findViewById(R.id.description);
        Spinner categorie = findViewById(R.id.catSpinner);

        String title = titre.getText().toString();
        String selectedCategorie = categorie.getSelectedItem().toString();
        String secteur = secteurs.getSelectedItem().toString();
        String typeContrat = Contrat.getText().toString();
        String desc = description.getText().toString();
        String ville = villes.getSelectedItem().toString();

        if (title.trim().isEmpty() || selectedCategorie.isEmpty() || secteur.isEmpty() || typeContrat.trim().isEmpty() || desc.trim().isEmpty() || ville.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
        } else {
            // Créer une instance de DatabaseHelper
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            dbHelper.ajouterAnnonce(title, selectedCategorie, secteur, typeContrat, desc, ville);
            Intent intent = new Intent(this, NombreAnnonces.class);
            intent.putExtra("villementionee", ville);
            startActivity(intent);
        }
    }
}
