package com.example.ifsc_ddm;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textViewResult;
    EditText editTextMin, editTextMax;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        editTextMin = findViewById(R.id.edMin);
        editTextMax = findViewById(R.id.edMax);
        textViewResult = findViewById(R.id.tvResult);

        button.setOnClickListener((v) -> {
            int min = Integer.parseInt(editTextMin.getText().toString());
            int max = Integer.parseInt(editTextMax.getText().toString());
            int sorteado = 0;
            Random random = new Random();
            sorteado = (int) (Math.random() * (max - min) + min);

            textViewResult.setText(Integer.toString(sorteado));
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("sorteado", textViewResult.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            textViewResult.setText(savedInstanceState.getString("sorteado"));
        }
    }
}