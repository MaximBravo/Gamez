package com.example.maximbravo.gamez;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Maxim Bravo on 12/15/2016.
 */

public class RecognizeTouchEvent {
    private int currentX;
    private int currentY;
    private ViewGroup main;
    private int boardWidth;
    private int boardHeight;
    private Activity mainActivity;
    public RecognizeTouchEvent(Activity activity, int width, int height){
        mainActivity = activity;
        main = (ViewGroup) mainActivity.findViewById(R.id.content_main);
        boardWidth = width;
        boardHeight = height;
        makeBoard();

    }
    private final int fraction = 20;
    public void makeBoard(){

        final float scale = mainActivity.getApplicationContext().getResources().getDisplayMetrics().density;
        int pixels = (int) (50 * scale + 0.5f);
        LinearLayout boardLinear = new LinearLayout(mainActivity.getApplicationContext());
        boardLinear.setOrientation(LinearLayout.VERTICAL);
        for(int rows = 0; rows < boardWidth; rows++) {
            LinearLayout l = new LinearLayout(mainActivity.getApplicationContext());
            l.setOrientation(LinearLayout.HORIZONTAL);
            for(int columns = 0; columns < boardHeight; columns++) {
                TextView right = new TextView(mainActivity.getApplicationContext());
                right.setHeight(pixels);
                right.setWidth(pixels);
                LinearLayout.LayoutParams templ = new LinearLayout.LayoutParams(pixels, pixels);
                templ.setMargins(pixels/fraction,pixels/fraction,pixels/fraction,pixels/fraction);
                right.setLayoutParams(templ);
                right.setId(View.generateViewId());
                right.setBackgroundColor(Color.RED);
                l.addView(right);
            }

            //l.setLayoutParams(params);
            boardLinear.addView(l);

        }
        double multiplier = 8.0/fraction;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int)((pixels*boardWidth) + (pixels * (multiplier))),(int) ((pixels*boardWidth) + (pixels * (multiplier))));
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        boardLinear.setLayoutParams(params);
        boardLinear.setId(View.generateViewId());
        main.addView(boardLinear);



    }
    public String isOnView(int x, int y, float extraTop){
        //TextView box = (TextView) mainActivity.findViewById(R.id.box);

        LinearLayout board = (LinearLayout) main.getChildAt(1);
        HashMap<Integer, Integer[]> views = new HashMap<>();
        int boardX = (int) board.getX();
        int boardY = (int) board.getY();
        int count = 0;
        for(int index = 0; index < board.getChildCount(); ++index) {
            LinearLayout row = (LinearLayout) board.getChildAt(index);
            //int rowX = boardX;
            //int rowY = boardY + (index * (row.getHeight()));
            for(int i = 0; i < row.getChildCount(); ++i) {
                View nextChild = row.getChildAt(i);
                Integer id = nextChild.getId();
                int childX = boardX+(i * (row.getHeight() ));
                int childY = boardY+(index * (row.getHeight()));
                Integer[] idxywh = {id, childX, childY, row.getHeight(), row.getHeight()};
                views.put(count, idxywh);
                count++;
            }
        }

        for(int i = 0; i < views.size(); i++){
            Integer[] metaData = views.get(i);
            int id = metaData[0];
            int viewX = metaData[1];
            int viewY = metaData[2];
            int viewWidth = metaData[3];
            int viewHeight = metaData[4];

            if (x <= viewX + viewWidth &&
                    x >= viewX &&
                    y <= viewY + viewHeight + extraTop &&
                    y >= viewY + extraTop) {

                //return mainActivity.getResources().getResourceEntryName(nextChild.getId());
                return "" + id;
            }
        }

        return "";
    }
}
