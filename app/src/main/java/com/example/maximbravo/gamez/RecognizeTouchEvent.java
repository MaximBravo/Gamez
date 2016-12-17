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
    private int widthOfCell;

    public RecognizeTouchEvent(Activity activity, int width, int height, int cellWidth){
        mainActivity = activity;
        main = (ViewGroup) mainActivity.findViewById(R.id.content_main);
        boardWidth = width;
        boardHeight = height;
        widthOfCell = cellWidth;
        rawBoard = new HashMap<>();
        makeBoard();

    }
    private static final int fraction = 20;
    public void makeBoard(){

        final float scale = mainActivity.getApplicationContext().getResources().getDisplayMetrics().density;
        int pixels = (int) (widthOfCell * scale + 0.5f);
        int margin = pixels/fraction;
        //int pixels = widthOfCell;
        LinearLayout boardLinear = new LinearLayout(mainActivity.getApplicationContext());
        boardLinear.setOrientation(LinearLayout.VERTICAL);
        int count = 1;
        for(int rows = 0; rows < boardWidth; rows++) {
            LinearLayout l = new LinearLayout(mainActivity.getApplicationContext());
            l.setOrientation(LinearLayout.HORIZONTAL);
            for(int columns = 0; columns < boardHeight; columns++) {
                TextView right = new TextView(mainActivity.getApplicationContext());
                right.setHeight(pixels);
                right.setWidth(pixels);
                LinearLayout.LayoutParams templ = new LinearLayout.LayoutParams(pixels, pixels);
                templ.setMargins(margin, margin, margin, margin);
                right.setLayoutParams(templ);
                right.setId(count);
                right.setBackgroundColor(mainActivity.getResources().getColor(R.color.boardGreen));
                l.addView(right);
                count++;
            }

            //l.setLayoutParams(params);
            boardLinear.addView(l);

        }

        double paddingspacewidth = margin * (boardWidth * 2);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int)((pixels*boardWidth) + paddingspacewidth),(int) ((pixels*boardWidth) + paddingspacewidth));
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        boardLinear.setLayoutParams(params);
        boardLinear.setId(count);
        boardLinear.setBackgroundColor(mainActivity.getResources().getColor(R.color.boardBlack));
        main.addView(boardLinear);



    }
    private static HashMap<Integer, Integer[]> rawBoard;
    public static HashMap<Integer, Integer[]> getRawBoardData(){
        return rawBoard;
    }
    public void loadRawBoard(){
        LinearLayout board = (LinearLayout) main.getChildAt(1);

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
                rawBoard.put(count, idxywh);
                count++;
            }
        }
    }
    public String isOnView(int x, int y, float extraTop){
        //TextView box = (TextView) mainActivity.findViewById(R.id.box);



        for(int i = 0; i < rawBoard.size(); i++){
            Integer[] metaData = rawBoard.get(i);
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
