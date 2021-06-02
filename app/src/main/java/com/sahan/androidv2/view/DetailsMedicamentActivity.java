package com.sahan.androidv2.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import com.sahan.androidv2.R;
import com.sahan.androidv2.model.DatePickerFragment;

public class DetailsMedicamentActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    TextView txtNomCommercial;
    TextView txtEffet;
    TextView txtPrix;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_medicament);
        txtNomCommercial = (TextView) this.findViewById(R.id.txtNomCommercial);
        txtEffet = (TextView) this.findViewById(R.id.txtEffet);
        txtPrix = (TextView) this.findViewById(R.id.txtPrix);
        txtNomCommercial.setText(this.getIntent().getExtras().getString("nom"));
        txtEffet.setText(this.getIntent().getExtras().getString("effet"));
        Double prix = this.getIntent().getDoubleExtra("prix", 0);
        txtPrix.setText(prix.toString());
        gestionClic();
    }

    private void gestionClic() {
        ((Button) this.findViewById(R.id.BTNRetour)).setOnClickListener((v) -> {
            Intent i = new Intent(DetailsMedicamentActivity.this, MedicamentActivity.class);
            startActivity(i);
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
