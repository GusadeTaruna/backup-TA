package com.example.bitmaptes;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.Image;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ZoomView zoomView;
    Button btnSwitch;
    DrawableView denah;
    ImageView marker;
    private float xCoOrdinate, yCoOrdinate;
    Canvas canvas;
    Paint mPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        btnSwitch = (Button) findViewById(R.id.switchBtn);
        zoomView = (ZoomView) findViewById(R.id.zoomView);
        denah = (DrawableView) findViewById(R.id.floorPlan);
        marker = (ImageView) findViewById(R.id.marker);

        marker.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        xCoOrdinate = view.getX() - event.getRawX();
                        yCoOrdinate = view.getY() - event.getRawY();
                        System.out.println("marker : "+xCoOrdinate+" "+yCoOrdinate);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        view.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();
//                        denah.drawing(event.getRawX() + xCoOrdinate,event.getRawY() + yCoOrdinate);
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });


        this.getSupportActionBar().hide();
        denah.setImageResource(R.drawable.sample_floor);
        denah.setDrawingEnabled(true);


    }
}