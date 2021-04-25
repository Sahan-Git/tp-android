package com.sahan.androidv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RendezVous extends AppCompatActivity {

    private Object Menu;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rendez_vous);

        //Toolbar
        this.configureToolbar();

        //Navigation View
        this.configureNavigation();

        //Configuration FAB
        this.configurationFAB();

        @Override
        public boolean onCreateOptionsMenu(Menu menu){
            //Toolbar
            getMenuInflater().inflate(R.menu.menu_activity, menu);
            return true;
        }

        private void configureToolbar(){
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle("Mes rendez-vous");
            setSupportActionBar(toolbar);
        }

        private void configureNavigation(){
            BottomNavigationView bottomNavigationView;
            bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
            bottomNavigationView.setSelectedItemId(R.id.action_accueil);
            bottomNavigationView.setOnNavigationItemSelectedListener(
                    (item)->{
                        Intent intent;
                        switch (item.getItemId()) {
                            case R.id.action_medecine:{
                                intent = new Intent(RendezVous.this, Medicament.class);
                                startActivity(intent);
                                overridePendingTransition(0,0);
                                break;
                            }
                            case R.id.action_settings:{
                                intent = new Intent(RendezVous.this, Profil.class);
                                startActivity(intent);
                                overridePendingTransition(0,0);
                                break;
                            }
                        }
                        return true;
                    }
            );
        }

        private void configurationFAB(){
            ((FloatingActionButton) this.findViewById(R.id.fabAjouter)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    Toast.makeText(RendezVous.this, "Prendre rendez-vous", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(RendezVous.this, PrendeRDV.class);
                    startActivity(i);
                }
            });
        }
    }

}
