package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "my_database.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Créer la table "users"
        String createUserTableQuery = "CREATE TABLE IF NOT EXISTS users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "email TEXT, "
                + "password TEXT)";
        db.execSQL(createUserTableQuery);

        // Créer la table "Annonces"
        String createAnnoncesTableQuery = "CREATE TABLE IF NOT EXISTS Annonces ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "titre TEXT, "
                + "categorie TEXT, "
                + "secteur TEXT, "
                + "typeContrat TEXT, "
                + "description TEXT, "
                + "ville TEXT)";
        db.execSQL(createAnnoncesTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Gérer la mise à jour de la base de données si nécessaire
    }

    // Méthodes pour gérer les utilisateurs
    public void ajouterUtilisateur(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("password", password);
        db.insert("users", null, values);
        db.close();
    }

    public boolean verifierConnexion(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("users", new String[]{"email", "password"},
                "email = ? AND password = ?", new String[]{email, password},
                null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    // Méthodes pour gérer les annonces
    public void ajouterAnnonce(String titre, String categorie, String secteur, String typeContrat, String description, String ville) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("titre", titre);
        values.put("categorie", categorie);
        values.put("secteur", secteur);
        values.put("typeContrat", typeContrat);
        values.put("description", description);
        values.put("ville", ville);
        db.insert("Annonces", null, values);
        db.close();
    }

    public int compterAnnoncesPourVille(String ville) {
        int nombreAnnonces = 0;
        try (SQLiteDatabase db = this.getReadableDatabase();
             Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM Annonces WHERE ville=?", new String[]{ville})) {
            if (cursor != null && cursor.moveToFirst()) {
                nombreAnnonces = cursor.getInt(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nombreAnnonces;
    }
}

