package com.example.bitmaptes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.Image;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ZoomView zoomView;
    private Button btnSwitch;
    private DrawableView denah;
    private ImageView marker;
    private ViewGroup mainLayout;
    private float xCoOrdinate, yCoOrdinate;
    private int xDelta,yDelta;
    int hScreen, wScreen;
    Canvas canvas;
    Paint mPaint;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        btnSwitch = (Button) findViewById(R.id.switchBtn);
        zoomView = (ZoomView) findViewById(R.id.zoomView);
        denah = (DrawableView) findViewById(R.id.floorPlan);
        marker = (ImageView) findViewById(R.id.marker);
        mainLayout = (ConstraintLayout)findViewById(R.id.background);

        this.getSupportActionBar().hide();
        denah.setImageResource(R.drawable.ss);
        denah.setDrawingEnabled(true);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        hScreen = displayMetrics.heightPixels;
        wScreen = displayMetrics.widthPixels;

        System.out.println("aw "+hScreen+" "+wScreen);

        marker.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int x = (int) event.getRawX();
                final int y = (int) event.getRawY();

                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        ConstraintLayout.LayoutParams lparams = (ConstraintLayout.LayoutParams) view.getLayoutParams();
                        xDelta = x - lparams.leftMargin;
                        yDelta = y - lparams.topMargin;
                        break;

                    case MotionEvent.ACTION_UP:
                        Rect viewRect = new Rect();
                        denah.getGlobalVisibleRect(viewRect);
                        if (!viewRect.contains(x,y)) {
                            Log.d("Click : "," Luar canvas");
                        }else{
                            Log.d("Click : ","dalam canvas");

                            //rumus untuk da
                            int finalX = x-((wScreen-denah.getWidth())/2) ;
                            int finalY = y-((hScreen-denah.getHeight())/2) ;

                            denah.drawing(finalX,finalY);
                        }
                        break;

                    case MotionEvent.ACTION_MOVE:
                        if (x - xDelta + view.getWidth() <= mainLayout.getWidth() && y - yDelta + view.getHeight() <= mainLayout.getHeight() && x - xDelta >= 0 && y - yDelta >= 0) {
                            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) view.getLayoutParams();

                            params.leftMargin = x - xDelta;
                            params.topMargin = y - yDelta;
                            params.rightMargin = 0;
                            params.bottomMargin = 0;
                            view.setLayoutParams(params);


                            System.out.println("tes"+x+" "+y+" "+denah.getHeight());

//                        denah.drawing(event.getRawX() + xCoOrdinate,event.getRawY() + yCoOrdinate);
                        }
                        break;
                    default:
                        return false;
                }
                return true;
            }

        });

    }
}