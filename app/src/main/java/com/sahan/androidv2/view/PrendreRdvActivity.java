package com.sahan.androidv2.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.icu.text.DateFormat;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import com.sahan.androidv2.R;
import com.sahan.androidv2.controller.RendezVousController;
import com.sahan.androidv2.model.DatePickerFragment;
import com.sahan.androidv2.model.RendezVous;
import com.sahan.androidv2.model.RendezVousDao;
import com.sahan.androidv2.model.TimePickerFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class PrendreRdvActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prendre_rdv);
        String languageToLoad  = "fr";
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.configureToolbar();
        
        Button button = (Button) findViewById(R.id.BTNDate);
        button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               DialogFragment datePicker = new DatePickerFragment();
               datePicker.show(getSupportFragmentManager(), "date picker");
           }
        });

        Button buttonhour = (Button) findViewById(R.id.BTNHeure);
        buttonhour.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        Button buttonValider = (Button) findViewById(R.id.BTNValider);
        buttonValider.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                TextView txtHeure = (TextView) findViewById(R.id.txtHeure);
                TextView txtDate = (TextView) findViewById(R.id.txtDate);

                String Heure = txtHeure.getText().toString();
                String Date = txtDate.getText().toString();

                if(!txtDate.getText().toString().equals("Date sélectionnée") && !txtHeure.getText().toString().equals("Heure sélectionnée")) {


                    RendezVousController rendezVousController = RendezVousController.getInstance(getBaseContext());
                    rendezVousController.ajouterRendezVous(Date, Heure, "1");

                    Toast.makeText(PrendreRdvActivity.this, "Rendez-vous validé !", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(PrendreRdvActivity.this, RendezVousActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(PrendreRdvActivity.this, "La date ou l'heure n'est pas valide !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView txtHeure = (TextView) findViewById(R.id.txtHeure);
        if (String.valueOf(hourOfDay).length() == 1){
            txtHeure.setText("0" + hourOfDay + ":");
        }
        else {
            if (String.valueOf(hourOfDay).length() == 0){
                txtHeure.setText("00:");
            }
            else
            {
                txtHeure.setText(hourOfDay + ":");
            }
        }

        if (String.valueOf(minute).length() == 1){
            txtHeure.setText(txtHeure.getText() + "0" + minute);
        }
        else {
            if (String.valueOf(hourOfDay).length() == 0) {
                txtHeure.setText(txtHeure.getText() + "00");
            } else {
                txtHeure.setText(txtHeure.getText() + String.valueOf(minute));
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String dateString = dateFormat.format(calendar.getTime());
        TextView txtDate = (TextView) findViewById(R.id.txtDate);
        txtDate.setText(dateString);
    }
    private void configureToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Prendre rendez-vous");
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
