package com.example.ifsc_ddm;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class SimplePaint extends View {

    float x0;
    float y0;
    Path currentPath;
    Paint currentPaint;
    List<Paint> mPaintList;
    List<Path> mPathList;
    ColorDrawable colorDrawable;
    private String shapeType = "free";

    public SimplePaint(Context context) {
        super(context);
        init();
    }

    public SimplePaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaintList = new ArrayList<Paint>();
        mPathList = new ArrayList<Path>();
        colorDrawable = new ColorDrawable();
        colorDrawable.setColor(Color.BLACK);
        initLayer();
    }

    public SimplePaint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public SimplePaint(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init(){
        currentPaint = new Paint();
        currentPath = new Path();
        currentPaint.setColor(Color.BLACK);
        initLayer();
    }

    public void initLayer(){
        currentPath = new Path();
        currentPaint = new Paint();
        currentPaint.setStrokeWidth(10);
        currentPaint.setStyle(Paint.Style.STROKE);
        currentPaint.setColor(colorDrawable.getColor());
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        for(int i = 0; i<mPaintList.size(); i++){
            canvas.drawPath(mPathList.get(i), mPaintList.get(i));
        }
        canvas.drawPath(currentPath, currentPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x0 = x;
                y0 = y;

                if (shapeType.equals("free")) {
                    currentPath.moveTo(x0, y0);
                }
                invalidate();
                return true;

            case MotionEvent.ACTION_MOVE:
                if (shapeType.equals("free")) {
                    currentPath.lineTo(x, y);
                }
                invalidate();
                return true;

            case MotionEvent.ACTION_UP:
                Paint newPaint = new Paint(currentPaint);
                Path path = new Path();

                if (shapeType.equals("free")) {
                    path.set(currentPath);
                    mPathList.add(path);
                    mPaintList.add(newPaint);
                } else if (shapeType.equals("rect")) {
                    path.addRect(x0, y0, x, y, Path.Direction.CW);
                    mPathList.add(path);
                    mPaintList.add(newPaint);
                } else if (shapeType.equals("circle")) {
                    float radius = (float) Math.hypot(x - x0, y - y0);
                    path.addCircle(x0, y0, radius, Path.Direction.CW);
                    mPathList.add(path);
                    mPaintList.add(newPaint);
                }

                initLayer();
                invalidate();
                return true;
        }
        return true;
    }

    public void undo() {
        mPathList.remove(mPathList.size() - 1);
        mPaintList.remove(mPaintList.size() - 1);
        invalidate();
    }

    public void setShapeType(String shapeType) {
        this.shapeType = shapeType;
    }

    public void clearDraw(){
        mPathList.clear();
        mPaintList.clear();
        currentPath.reset();
        invalidate();
    }

    public void changeColor(int color){
        colorDrawable.setColor(color);
        currentPaint.setColor(color);
    }
}