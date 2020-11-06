package com.example.bitmaptes;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ZoomView zoomView;
    Button btnSwitch;
    DrawableView denah;
    Canvas canvas;
    Paint mPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSwitch = (Button) findViewById(R.id.switchBtn);
        zoomView = (ZoomView) findViewById(R.id.zoomView);
        denah = (DrawableView) findViewById(R.id.floorPlan);

        denah.setDrawingEnabled(true);
    }
}