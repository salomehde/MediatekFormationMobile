package com.example.mediatekformationmobile.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.mediatekformationmobile.outils.MySQLiteOpenHelper;
import java.sql.PreparedStatement;
import java.util.Date;

public class AccesLocal {

    private String nomBase = "bdFavoris.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase bd;

    /**
     * constructeur : valorise l'accès à la BDD
     * @param context
     */
    public AccesLocal(Context context){
        accesBD = new MySQLiteOpenHelper(context, nomBase, versionBase);
    }

    /**
     * ajout d'un favori dans la BDD
     * @param id
     */
    public void ajout(Integer id){
        bd = accesBD.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idFavoris", id);
        bd.insert("Favoris", null, values);
        bd.close();
    }

    /**
     * suppression d'un favori dans la BDD
     * @param id
     */
    public void suppr(Integer id){
        bd = accesBD.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idFavoris", id);
        bd.delete("Favoris", null, values);
        bd.close();
    }

    /**
     * retourne les favoris de l'utilisateur
     * @return les favoris
     */
    public ArrayList<Integer> recupFavoris(){
        ArrayList<Integer> lesFavoris = new ArrayList<Integer>();
        bd = accesBD.getReadableDatabase();
        String req = "select idFavoris from Favoris";
        Cursor curseur = bd.rawQuery(req, null);
        while(curseur.moveToNext()) {
            int id = curseur.getInt(0);
            lesFavoris.add(id);
        }
        curseur.close();
        bd.close();
        return lesFavoris;
    }

}
