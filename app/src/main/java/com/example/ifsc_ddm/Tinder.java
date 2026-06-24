package com.example.ifsc_ddm;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class Tinder extends androidx.appcompat.widget.AppCompatButton {
    private int x0, y0;
    private int ColorR = 120, ColorG = 120, ColorB = 120;

    public Tinder(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            x0 = (int) x;
            y0 = (int) y;
        }

        int dx = (int) (x - x0);

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            ColorR = Math.min(255, Math.max(0, 120 - dx / 5));
            ColorG = Math.min(255, Math.max(0, 120 + dx / 5));
            ColorB = 120;
        }

        if (event.getAction() == MotionEvent.ACTION_UP) {
            ColorR = 120;
            ColorG = 120;
            ColorB = 120;
        }

        // De acordo com o código original da Duda:
        this.setBackgroundColor(Color.rgb(ColorB, ColorG, ColorR));

        return super.onTouchEvent(event);
    }
}