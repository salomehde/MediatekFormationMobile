package com.example.mediatekformationmobile.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mediatekformationmobile.R;
import com.example.mediatekformationmobile.controleur.Controle;
import com.example.mediatekformationmobile.modele.Formation;

import java.util.ArrayList;
import java.util.Collections;

public class FormationsActivity extends AppCompatActivity {

    private Controle controle;
    private Button btnFiltrer;
    private EditText txtFiltre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formations);
        init();
    }

    /**
     * initialisations
     */
    private void init(){
        controle = Controle.getInstance();
        btnFiltrer = (Button) findViewById(R.id.btnFiltrer);
        txtFiltre = (EditText) findViewById(R.id.txtFiltre);
        creerListe();
        ecouteFiltrer();
    }

    /**
     * création de la liste adapter
     */
    private void creerListe(){
        ArrayList<Formation> lesFormations = controle.getLesFormations();
        if(lesFormations != null){
            Collections.sort(lesFormations, Collections.<Formation>reverseOrder());
            ListView listView = (ListView)findViewById(R.id.lstFormations);
            FormationListAdapter adapter = new FormationListAdapter(lesFormations,FormationsActivity.this);
            listView.setAdapter(adapter);
        }
    }

    /**
     * création de la liste adapter filtrée
     */
    private void creerListeFiltre(String filtre){
        ArrayList<Formation> lesFormations = controle.getLesFormationsFiltre(filtre);
        if(lesFormations != null){
            Collections.sort(lesFormations, Collections.<Formation>reverseOrder());
            ListView listView = (ListView)findViewById(R.id.lstFormations);
            FormationListAdapter adapter = new FormationListAdapter(lesFormations,FormationsActivity.this);
            listView.setAdapter(adapter);
        }
    }

    /**
     * méthode événementielle sur le clic du bouton "filtrer"
     */
    private void ecouteFiltrer() {
        btnFiltrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filtre = txtFiltre.getText().toString();
                if (!filtre.isEmpty()) {
                    creerListeFiltre(filtre);
                } else {
                    creerListe();
                }
            }
        });
    }
}