package com.example.ifsc_ddm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

        button = findViewById(R.id.button);
        altura = findViewById(R.id.altura);
        peso = findViewById(R.id.peso);

        button.setOnClickListener(v -> {
            try {
                String stringPeso = peso.getText().toString().replace(",", ".");
                String stringAltura = altura.getText().toString().replace(",", ".");

                float valorPeso = Float.parseFloat(stringPeso);
                float valorAltura = Float.parseFloat(stringAltura);

                float imc = valorPeso / (valorAltura * valorAltura);

                Intent i = new Intent(getApplicationContext(), IMC.class);
                i.putExtra("msg", imc);
                startActivity(i);

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Preencha o peso e a altura corretamente", Toast.LENGTH_SHORT).show();
            }
        });
    }
}