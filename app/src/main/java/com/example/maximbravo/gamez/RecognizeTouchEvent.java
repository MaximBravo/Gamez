package com.example.maximbravo.gamez;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

/**
 * Created by Maxim Bravo on 12/15/2016.
 */

public class RecognizeTouchEvent {
    private int currentX;
    private int currentY;
    private int layout;
    private Activity mainActivity;
    public RecognizeTouchEvent(Activity activity){
        mainActivity = activity;
    }

    public boolean isOnView(int x, int y, float extraTop){
        TextView box = (TextView) mainActivity.findViewById(R.id.box);
        if(x <= box.getX() + box.getWidth() && x >= box.getX() && y <= box.getY()+box.getHeight()+extraTop && y >= box.getY()+extraTop){
            return true;
        }
        return false;
    }
}
