package com.example.ifsc_ddm;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

public class MainActivity extends AppCompatActivity {
    Button buttonClear, tradeColor, undoButton, retangleBut, circleBut, freeBut;
    SimplePaint simplePaint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonClear = findViewById(R.id.clearButton);
        tradeColor = findViewById(R.id.changeColor);
        simplePaint = findViewById(R.id.simplePaint);
        undoButton = findViewById(R.id.undo);
        retangleBut = findViewById(R.id.retangle);
        circleBut = findViewById(R.id.circle);
        freeBut = findViewById(R.id.freeDraw);

        buttonClear.setOnClickListener(v->{
            simplePaint.clearDraw();
        });

        tradeColor.setOnClickListener(v -> {
            new ColorPickerDialog.Builder(this)
                    //configurando a caixa de dialogo
                    .setTitle("ColorPicker Dialog")
                    .setPreferenceName("MyColorPickerDialog")
                    .setPositiveButton("Confirma",
                            new ColorEnvelopeListener() {
                                @Override
                                public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {
                                    v.setBackgroundColor(envelope.getColor());
                                    simplePaint.changeColor(envelope.getColor());

                                }
                            })
                    .setNegativeButton("Cancelar",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                    .attachAlphaSlideBar(true) // the default value is true.
                    .attachBrightnessSlideBar(true)  // the default value is true.
                    .setBottomSpace(12) // set a bottom space between the last slidebar and buttons.
                    .show();
        });

        freeBut.setOnClickListener(v -> {
            simplePaint.setShapeType("free");
        });

        retangleBut.setOnClickListener(v -> {
            simplePaint.setShapeType("rect");
        });

        circleBut.setOnClickListener(v -> {
            simplePaint.setShapeType("circle");
        });

        undoButton.setOnClickListener(v -> {
            simplePaint.undo();
        });
    }

}