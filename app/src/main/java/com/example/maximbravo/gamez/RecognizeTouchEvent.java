package com.example.maximbravo.gamez;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Maxim Bravo on 12/15/2016.
 */

public class RecognizeTouchEvent {
    private int currentX;
    private int currentY;
    private ViewGroup main;
    private Activity mainActivity;
    public RecognizeTouchEvent(Activity activity){
        mainActivity = activity;
        main = (ViewGroup) mainActivity.findViewById(R.id.content_main);
        makeBoard();

    }

    public void makeBoard(){
        TextView right = new TextView(mainActivity.getApplicationContext());
        final float scale = mainActivity.getApplicationContext().getResources().getDisplayMetrics().density;
        int pixels = (int) (50 * scale + 0.5f);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(pixels, pixels);
        params.addRule(RelativeLayout.RIGHT_OF, R.id.box1_1);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        right.setLayoutParams(params);


        right.setId(View.generateViewId());
        right.setBackgroundColor(Color.RED);
        main.addView(right);

    }
    public String isOnView(int x, int y, float extraTop){
        //TextView box = (TextView) mainActivity.findViewById(R.id.box);

        for(int index=0; index<((ViewGroup)main).getChildCount(); ++index) {
            View nextChild = ((ViewGroup)main).getChildAt(index);
            if(x <= nextChild.getX() + nextChild.getWidth() &&
                x >= nextChild.getX() &&
                y <= nextChild.getY()+nextChild.getHeight()+extraTop &&
                y >= nextChild.getY()+extraTop){

                //return mainActivity.getResources().getResourceEntryName(nextChild.getId());
                return "" + nextChild.getId();
            }
        }

        return "";
    }
}
