package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NombreAnnonces extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nombre_annonces);

        // Récupérer la ville mentionnée dans le formulaire depuis l'intent
        String ville = getIntent().getStringExtra("villementionee");

        // Afficher le nombre d'annonces pour la ville sélectionnée
        afficherNombreAnnonces(ville);
    }

    // Méthode pour afficher le nombre d'annonces pour une ville spécifique
    private void afficherNombreAnnonces(String ville) {
        // Vérifier si la ville est non nulle
        if (ville != null) {
            // Créer une instance de DatabaseHelper
            DatabaseHelper db = new DatabaseHelper(this);

            // Obtenir le nombre d'annonces pour la ville spécifiée
            int nombreAnnonces = db.compterAnnoncesPourVille(ville);

            // Trouver le TextView dans le layout
            TextView textView = findViewById(R.id.text1);

            // Afficher le nombre d'annonces dans le TextView
            textView.setText("Il y'a actuellement " + nombreAnnonces+ " annonce(s) sur " + ville);
        } else {
            // Afficher un message d'erreur si la ville n'est pas spécifiée
            TextView textView = findViewById(R.id.text1);
            textView.setText("Erreur: Aucune ville spécifiée");
        }
    }
}
