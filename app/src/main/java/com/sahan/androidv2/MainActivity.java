package com.sahan.androidv2;

import android.content.Intent;
import android.view.Menu;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.idApplication);
        editText.setOnEditorActionListener((v,actionId, event) -> {
            if(actionId == EditorInfo.IME_ACTION_DONE){
                String value = editText.getText().toString();
                String marqueur = "9999";
                if(value.compareTo(marqueur) == 0){
                    Intent intent = new Intent(MainActivity.this, RendezVous.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(MainActivity.this, "Code erroné", Toast.LENGTH_SHORT).show();
            }
            return false;
        });
    }
}