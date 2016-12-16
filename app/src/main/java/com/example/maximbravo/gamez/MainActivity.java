package com.example.maximbravo.gamez;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private RecognizeTouchEvent screen;
    private final int boardSize = 2;
    private int cellSize;
    private View main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        main = findViewById(R.id.content_main);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = (int) (displaymetrics.heightPixels - toolbar.getMeasuredHeight()*2);
        int width = displaymetrics.widthPixels;
        int smallest = pxToDp(Math.min(height, width));
        screen = new RecognizeTouchEvent(this, boardSize, boardSize, smallest/(boardSize+2)-4);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }
    public int pxToDp(int px) {
        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //TextView box = (TextView) findViewById(R.id.box);
        TextView t = (TextView) findViewById(R.id.test_textview);
        if(event.getAction() == android.view.MotionEvent.ACTION_UP){
            t.setText("Recording stoped.");
        } else {
            int x = (int) event.getX();
            int y = (int) event.getY();
            //t.setText("Recording: x:" + x + ", y:" + y);
            int mainX = (int) main.getX();
            String viewTouching = screen.isOnView(x, y, main.getY());
            if (viewTouching.equals("")) {
                t.setText("You are touching: No View");
                //box.setBackgroundColor(Color.RED);
                //main.setBackgroundColor(Color.RED);
            } else {
                t.setText("You are touching: " + viewTouching);
            }
        }
        return super.onTouchEvent(event);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
