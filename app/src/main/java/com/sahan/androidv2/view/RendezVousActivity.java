package com.sahan.androidv2.view;

import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sahan.androidv2.R;
import com.sahan.androidv2.controller.RendezVousController;
import com.sahan.androidv2.model.RendezVous;

import java.util.ArrayList;


public class RendezVousActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList<RendezVous> lesRendezVous;
    RendezVousListViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rendez_vous);

        //Toolbar
        this.configureToolbar();

        //Navigation View
        this.configureNavigation();

        //Configuration FAB
        this.configurationFAB();

        creerListe();
    }
    private void creerListe() {
        Intent intent = getIntent();
        RendezVousController rendezVousController = RendezVousController.getInstance(getBaseContext());
        lesRendezVous = rendezVousController.rendezVous();
        if (lesRendezVous != null) {
            ListView lstHisto = (ListView) this.findViewById(R.id.lVRendezVous);
            adapter = new RendezVousListViewAdapter(this, lesRendezVous);
            lstHisto.setAdapter(adapter);
            lstHisto.setOnItemClickListener(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Toolbar
        getMenuInflater().inflate(R.menu.menu_activity, menu);
        return true;
    }

    private void configureToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Mes rendez-vous");
        setSupportActionBar(toolbar);
    }

    private void configureNavigation() {
        BottomNavigationView bottomNavigationView;
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.action_accueil);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                (item) -> {
                    Intent intent;
                    switch (item.getItemId()) {
                        case R.id.action_medecine: {
                            intent = new Intent(RendezVousActivity.this, MedicamentActivity.class);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                            break;
                        }
                        case R.id.action_settings: {
                            intent = new Intent(RendezVousActivity.this, ProfilActivity.class);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                            break;
                        }
                    }
                    return true;
                }
        );
    }

    private void configurationFAB() {
        ((FloatingActionButton) this.findViewById(R.id.fabAjouter)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RendezVousActivity.this, "Prendre rendez-vous", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(RendezVousActivity.this, PrendreRdvActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}