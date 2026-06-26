package com.example.ifsc_ddm;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ControllerSQL controladorNotas;
    private EditText campoTextoNota;
    private ListView listaExibicaoNotas;
    private Button botaoSalvar, botaoLimpar, botaoVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarComponentes();
        configurarAcoesDosBotoes();
        atualizarListaNaTela();
    }

    private void inicializarComponentes() {
        controladorNotas = new ControllerSQL(this);

        campoTextoNota = findViewById(R.id.edNota);
        listaExibicaoNotas = findViewById(R.id.lvNotas);
        botaoSalvar = findViewById(R.id.btnSalvar);
        botaoLimpar = findViewById(R.id.btnLimpar);
        botaoVoltar = findViewById(R.id.btnVoltar);
    }

    private void configurarAcoesDosBotoes() {
        botaoSalvar.setOnClickListener(view -> {
            String textoDigitado = campoTextoNota.getText().toString();
            controladorNotas.salvarNota(textoDigitado);
            campoTextoNota.setText("");
            atualizarListaNaTela();
        });

        botaoLimpar.setOnClickListener(view -> {
            controladorNotas.limparNotas();
            atualizarListaNaTela();
        });

        botaoVoltar.setOnClickListener(view -> finish());
    }

    private void atualizarListaNaTela() {
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                controladorNotas.listarTitulos()
        );
        listaExibicaoNotas.setAdapter(adaptador);
    }
}