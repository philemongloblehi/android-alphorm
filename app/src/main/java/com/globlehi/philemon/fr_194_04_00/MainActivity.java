package com.globlehi.philemon.fr_194_04_00;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

enum EtatVue {STOPPE, ENCOURS};

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> spinnerAdaptateur;
    EtatVue etat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = majSpinner(new ArrayList<String>());

        etat = EtatVue.STOPPE;

        Button soumission = (Button) findViewById(R.id.soumission);
        soumission.setOnClickListener((v) -> {
            EditText edt = (EditText) findViewById(R.id.lieu_txt);
            String nouveauTexte = edt.getText().toString().toUpperCase();

            int nb = spinnerAdaptateur.getPosition(nouveauTexte);
            if (nb < 0)
                spinnerAdaptateur.add(nouveauTexte);

            changeEtat();

        });
    }

    private void changeEtat() {

        Button soumission = (Button) findViewById(R.id.soumission);

        if (etat == EtatVue.ENCOURS) {
            etat = EtatVue.STOPPE;
            soumission.setText(R.string.demarrer);
            soumission.setGravity(Gravity.LEFT);
        } else if (etat == EtatVue.STOPPE) {
            etat = EtatVue.ENCOURS;
            soumission.setText(R.string.stopper);
            soumission.setGravity(Gravity.RIGHT);
        }
    }

    private Spinner majSpinner(ArrayList<String> lesLieux) {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinnerAdaptateur = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lesLieux);
        spinnerAdaptateur.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        return spinner;
    }
}
