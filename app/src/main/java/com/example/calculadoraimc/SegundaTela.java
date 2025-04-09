package com.example.calculadoraimc;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SegundaTela extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_tela);

        TextView textoSaude = findViewById(R.id.estado);
        ImageView imageView = findViewById(R.id.imcImagemResult);
        float imc = getIntent().getFloatExtra("msg", 0);
        String resultado = String.format("Seu IMC: %.2f", imc);

        if (imc < 18.5) {
            textoSaude.setText("Você está abaixo do peso");
            imageView.setImageResource(R.drawable.abaixopeso);
        } else if (imc >= 18.6 && imc <= 24.9) {
            textoSaude.setText("Você está no seu peso");
            imageView.setImageResource(R.drawable.normal);
        } else if (imc >= 25.0 && imc <= 29.9) {
            textoSaude.setText("Você está com um sobre peso");
            imageView.setImageResource(R.drawable.sobrepeso);
        } else if (imc >= 30.0 && imc <= 34.9) {
            textoSaude.setText("Você está com obesidade grau 1");
            imageView.setImageResource(R.drawable.obesidade1);
        } else if (imc >= 35.0 && imc <= 39.9) {
            textoSaude.setText("Você está com obesidade grau 2");
            imageView.setImageResource(R.drawable.obesidade2);
        } else {
            textoSaude.setText("Você está com obesidade grau 3");
            imageView.setImageResource(R.drawable.obesidade3);
        }

        TextView textView = findViewById(R.id.segundatela);
        textView.setText(resultado);
    }
}