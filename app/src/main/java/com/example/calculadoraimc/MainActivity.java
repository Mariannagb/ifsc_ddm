package com.example.calculadoraimc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    Button button;
    EditText altura;
    EditText peso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        button.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), SegundaTela.class);
            altura = findViewById(R.id.altura);
            peso = findViewById(R.id.peso);
            float imc = Float.parseFloat(peso.getText().toString()) / (Float.parseFloat(altura.getText().toString()) * (Float.parseFloat(altura.getText().toString())));
            i.putExtra("msg", imc);
            startActivity(i);
        });
    }

}