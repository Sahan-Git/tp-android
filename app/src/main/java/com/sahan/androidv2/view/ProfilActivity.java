package com.sahan.androidv2.view;

import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sahan.androidv2.R;

public class ProfilActivity extends AppCompatActivity {

    Button BTNValiderMDP;
    EditText txtOldMDP, txtNewMDP, txtRepeatMDP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        //Toolbar
        this.configureToolbar();

        //Navigation View
        this.configureNavigation();

        txtOldMDP = (EditText) findViewById(R.id.txtOldMDP);
        txtNewMDP = (EditText) findViewById(R.id.txtNewMDP);
        txtRepeatMDP = (EditText) findViewById(R.id.txtRepeatMDP);

        BTNValiderMDP = (Button) findViewById(R.id.BTNValiderMDP);
        BTNValiderMDP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtOldMDP.getText().toString().equals("ancienmdp") && txtNewMDP.getText().toString().equals(txtRepeatMDP.getText().toString())){

                    Toast.makeText(ProfilActivity.this, "Mot de passe modifié", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Toolbar
        getMenuInflater().inflate(R.menu.menu_activity, menu);
        return true;
    }

    private void configureToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Paramètres");
        setSupportActionBar(toolbar);
    }

    private void configureNavigation() {
        BottomNavigationView bottomNavigationView;
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.action_settings);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                (item) -> {
                    Intent intent;
                    switch (item.getItemId()) {
                        case R.id.action_medecine: {
                            intent = new Intent(ProfilActivity.this, MedicamentActivity.class);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                            break;
                        }
                        case R.id.action_accueil: {
                            intent = new Intent(ProfilActivity.this, RendezVousActivity.class);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                            break;
                        }
                    }
                    return true;
                }
        );
    }
}
