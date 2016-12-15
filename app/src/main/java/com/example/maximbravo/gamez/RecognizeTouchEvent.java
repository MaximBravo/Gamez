package com.example.maximbravo.gamez;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    }

    public String isOnView(int x, int y, float extraTop){
        //TextView box = (TextView) mainActivity.findViewById(R.id.box);

        for(int index=0; index<((ViewGroup)main).getChildCount(); ++index) {
            View nextChild = ((ViewGroup)main).getChildAt(index);
            if(x <= nextChild.getX() + nextChild.getWidth() &&
                x >= nextChild.getX() &&
                y <= nextChild.getY()+nextChild.getHeight()+extraTop &&
                y >= nextChild.getY()+extraTop){
                return mainActivity.getResources().getResourceEntryName(nextChild.getId());
            }
        }

        return "";
    }
}
