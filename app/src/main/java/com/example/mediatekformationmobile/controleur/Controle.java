package com.example.mediatekformationmobile.controleur;

import com.example.mediatekformationmobile.modele.AccesDistant;
import com.example.mediatekformationmobile.modele.Formation;
import com.example.mediatekformationmobile.modele.AccesLocal;
import android.content.Context;
import java.util.ArrayList;

public class Controle {

    private static Controle instance = null ;
    private ArrayList<Formation> lesFormations = new ArrayList<>();
    private Formation formation = null;
    private static AccesLocal accesLocal;

    /**
     * constructeur privé
     */
    private Controle(){
        super();
    }

    /**
     * récupération de l'instance unique de Controle
     * @return instance
     */
    public static final Controle getInstance(){
        if(Controle.instance == null){
            Controle.instance = new Controle();
            AccesDistant accesDistant = new AccesDistant();
            accesDistant.envoi("tous", null);
            accesLocal = new AccesLocal(context);
        }
        return Controle.instance;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public ArrayList<Formation> getLesFormations() {
        return lesFormations;
    }

    /**
     * retourne les formations dont le titre contient le filtre
     * @param filtre
     * @return
     */
    public ArrayList<Formation> getLesFormationsFiltre(String filtre){
        ArrayList<Formation> lesFormationsFiltre = new ArrayList<>();
        for(Formation uneFormation : lesFormations){
            if(uneFormation.getTitle().toUpperCase().contains(filtre.toUpperCase())){
                lesFormationsFiltre.add(uneFormation);
            }
        }
        return lesFormationsFiltre;
    }

    public void setLesFormations(ArrayList<Formation> lesFormations) {
        this.lesFormations = lesFormations;
    }

    /**
     * ajouter un favori dans la BDD locale
     * @param id
     */
    public void ajouterFavori(Integer id){
        accesLocal.ajout(id);
    }

}

