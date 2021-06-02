package com.sahan.androidv2.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sahan.androidv2.R;
import com.sahan.androidv2.controller.MedicamentController;
import com.sahan.androidv2.model.Medicament;

import java.util.ArrayList;

public class MedicamentActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList<Medicament> medicaments;
    com.sahan.androidv2.view.MedicamentListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicament);
        creerListe();
        //Toolbar
        this.configureToolbar();

        //Navigation View
        this.configureNavigation();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        //Toolbar
        getMenuInflater().inflate(R.menu.menu_activity, menu);
        return true;
    }

    private void configureToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Liste des médicaments");
        setSupportActionBar(toolbar);
    }

    private void configureNavigation() {
        BottomNavigationView bottomNavigationView;
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.action_medecine);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                (item) -> {
                    Intent intent;
                    switch (item.getItemId()) {
                        case R.id.action_settings: {
                            intent = new Intent(MedicamentActivity.this, ProfilActivity.class);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                            break;
                        }
                        case R.id.action_medecine: {
                            intent = new Intent(MedicamentActivity.this, MedicamentActivity.class);
                            startActivity(intent);
                            overridePendingTransition(0,0);
                            break;
                        }
                        case R.id.action_accueil: {
                            intent = new Intent(MedicamentActivity.this, RendezVousActivity.class);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                            break;
                        }
                    }
                    return true;
                }
        );
    }

    private void creerListe() {
        Intent intent = getIntent();
        MedicamentController medicamentController = MedicamentController.getInstance(getBaseContext());
        medicaments = medicamentController.medicaments();
        if (medicaments != null) {
            ListView lstHisto = (ListView) this.findViewById(R.id.lVMedicaments);
            adapter = new com.sahan.androidv2.view.MedicamentListViewAdapter(this, medicaments);
            lstHisto.setAdapter(adapter);
            lstHisto.setOnItemClickListener(this);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("Medoc","Position " + String.valueOf(position));
        Medicament medicament = (Medicament)adapter.getItem(position);
        String value = medicament.getMNomCommercial();
        Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MedicamentActivity.this, DetailsMedicamentActivity.class);
        i.putExtra("nom", medicament.getMNomCommercial());
        i.putExtra("effet", medicament.getMEffet());
        i.putExtra("prix", medicament.getMPrixEchant());
        startActivity(i);
    }
}
