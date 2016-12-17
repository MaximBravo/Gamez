package com.example.maximbravo.gamez;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Maxim Bravo on 12/15/2016.
 */

public class BoardMaker {
    private int currentX;
    private int currentY;
    private ViewGroup main;
    private int boardWidth;
    private int boardHeight;
    private Activity mainActivity;
    private int widthOfCell;

    private Board board;
    public BoardMaker(Activity activity, int width, int height, int cellWidth){

        board = new Board(width, height);
        mainActivity = activity;
        main = (ViewGroup) mainActivity.findViewById(R.id.content_main);
        boardWidth = width;
        boardHeight = height;
        widthOfCell = cellWidth;
        makeBoard();
    }
    private static final int fraction = 20;

    private static int[] colors = {
            Color.parseColor("#f59b18"),
            Color.parseColor("#804f63"),
            Color.parseColor("#1650e0"),
            Color.parseColor("#78d2e9"),
            Color.parseColor("#ffaa6a"),
            Color.parseColor("#567f14"),
            Color.parseColor("#4c47c6"),
            Color.parseColor("#364293"),
            Color.parseColor("#938fd7"),
            Color.parseColor("#fa15bd"),
            Color.parseColor("#3a0bcd"),
            Color.parseColor("#10d3ec"),
            Color.parseColor("#bc44f9"),
            Color.parseColor("#662a74"),
            Color.parseColor("#67b1ac"),
            Color.parseColor("#b6ef6e"),
            Color.parseColor("#662a74")
    };

    public class Board{
        private int currentX;
        private int currentY;

        private int numberOfRows;
        private int numberOfColumns;

        private HashMap<Integer, Integer> cellStateOptions;

        private int cellWidthInPixels;
        private int cellHeightInPixels;

        private final int WIDTH_CONSTANT = 0;
        private final int HEIGHT_CONSTANT = 1;

        private int screenWidthInPixels;
        private int screenHeightInPixels;

        private Cell[][] arrayOfSquares;
        public Board(int rows, int columns){
            currentX = 0;
            currentY = 0;
            numberOfRows = rows;
            numberOfColumns = columns;
            arrayOfSquares = new Cell[rows][columns];

        }


        public void addCell(Cell c){
            arrayOfSquares[currentX][currentY] = c;
            incrementPosition();
        }

        public Cell getCell(int x, int y){
            return arrayOfSquares[x][y];
        }

        public void updateCellAt(int x, int y, Cell newCell){
            arrayOfSquares[x][y] = newCell;
        }
//        public Board(int rows, int columns, int cellWidth, int cellHeight){
//            currentX = 0;
//            currentY = 0;
//            numberOfRows = rows;
//            numberOfColumns = columns;
//            arrayOfSquares = new Cell[rows][columns];
//            cellWidthInPixels = cellWidth;
//            cellHeightInPixels = cellHeight;
//
//        }
//
//        public Board(int rows, int columns, int cellWidth, int cellHeight, HashMap<Integer, Integer> stateOptions){
//            currentX = 0;
//            currentY = 0;
//            numberOfRows = rows;
//            numberOfColumns = columns;
//            arrayOfSquares = new Cell[rows][columns];
//            cellStateOptions = stateOptions;
//
//        }
//
//        public void addScreenSizeInPixels(int width, int height){
//            screenWidthInPixels = width;
//            screenHeightInPixels = height;
//        }
//
//        public void addCellWidthAndHeightInPixels(int cellwidth, int cellheight){
//            cellWidthInPixels = cellwidth;
//            cellHeightInPixels = cellheight;
//        }
//        public void addCell(int resId, int x, int y) {
//            Cell currentCell = new Cell(cellWidthInPixels, cellHeightInPixels, x, y);
//            currentCell.addResId(resId);
//
//            arrayOfSquares[currentX][currentY] = currentCell;
//            incrementPosition();
//        }
//        public void addCellWithBackgroundColor(int resId, int backgroundColor, int x, int y){
//            Cell currentCell = new Cell(cellWidthInPixels, cellHeightInPixels, x, y);
//            currentCell.addResId(resId);
//            currentCell.addBackgroundColor(backgroundColor);
//
//            arrayOfSquares[currentX][currentY] = currentCell;
//            incrementPosition();
//        }
//
//        public void addCellWithState(int resId, int state, int x, int y){
//            Cell currentCell = new Cell(cellWidthInPixels, cellHeightInPixels, x, y);
//            currentCell.addResId(resId);
//            currentCell.addStateOptions(cellStateOptions);
//            currentCell.addState(state);
//            arrayOfSquares[currentX][currentY] = currentCell;
//            incrementPosition();
//        }


        public void incrementPosition(){
            if(currentX == numberOfColumns-1){
                currentX = 0;
                currentY++;
            } else {
                currentX++;
            }
        }


    }
    public class Cell{
        private int cellWidthInPixels;
        private int cellHeightInPixels;
        private Drawable cellBackgroundColor;
        private int cellState;
        private int cellXInPixels;
        private int cellYInPixels;
        private TextView cellTextView;
        private HashMap<Integer, Integer> cellStateOptions;
        private int cellResId;
        private int marginInPixels;

        public String toString(){
            String summary = "";
            summary += "WidthInPixels = " + getCellWidthInPixels();
            summary += "\nHeightInPixels = " + getCellHeightInPixels();
            summary += "\nXPositionInPixels = " + getCellXInPixels();
            summary += "\nYPositionInPixels = " + getCellYInPixels();
            summary += "\nMarginInPixels = " + getMarginInPixels();
            summary += "\nBackground = " + getCellBackgroundColor();
            return summary;
        }
        public int getCellResId() {
            return cellResId;
        }

        public int getCellYInPixels() {
            return cellYInPixels;
        }

        public int getCellXInPixels() {
            return cellXInPixels;
        }

        public Drawable getCellBackgroundColor() {
            return cellBackgroundColor;
        }

        public int getCellHeightInPixels() {
            return cellHeightInPixels;
        }

        public int getCellWidthInPixels() {
            return cellWidthInPixels;
        }

        public int getMarginInPixels() {
            return marginInPixels;
        }

        public Cell(int width, int height, int margin){
            cellWidthInPixels = width;
            cellHeightInPixels = height;

            marginInPixels = margin;
        }
        public Cell(int width, int height, int x, int y){
            cellWidthInPixels = width;
            cellHeightInPixels = height;

            cellXInPixels = x;
            cellYInPixels = y;
        }
        public void addBackgroundColor(Drawable color){
            cellBackgroundColor = color;
        }

        public void addStateOptions(HashMap<Integer, Integer> stateOptions){
            cellStateOptions = stateOptions;
        }
        public void addState(int state){
            cellState = state;
        }

        public void addResId(int resId){
            cellResId = resId;
        }

        public void addXYPosition(int x, int y){
            cellXInPixels = x;
            cellYInPixels = y;
        }

        public void addTextView(TextView textView){
            cellTextView = textView;
            cellBackgroundColor = cellTextView.getBackground();
            int x = (int) (cellTextView.getX() - marginInPixels);
            int y = (int) (cellTextView.getY() - marginInPixels);
            cellXInPixels = x;
            cellYInPixels = y;
            cellResId = cellTextView.getId();

        }

    }
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
                int entireWidth = pixels + (2*margin);
                Cell currentCell = new Cell(entireWidth, entireWidth, margin);
                TextView right = new TextView(mainActivity.getApplicationContext());
                right.setHeight(pixels);
                right.setWidth(pixels);
                LinearLayout.LayoutParams templ = new LinearLayout.LayoutParams(pixels, pixels);
                templ.setMargins(margin, margin, margin, margin);
                right.setLayoutParams(templ);
                right.setId(count);
                Random rand = new Random();

                // nextInt is normally exclusive of the top value,
                // so add 1 to make it inclusive
                int randomNum = rand.nextInt((15 - 1) + 1) + 1;

                right.setBackgroundColor(colors[randomNum]);
                l.addView(right);
                currentCell.addTextView(right);
                count++;
                board.addCell(currentCell);
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

    private String tempSummary = "";
    public String isOnView(int x, int y, float extraTop){
        //TextView box = (TextView) mainActivity.findViewById(R.id.box);

        LinearLayout b = (LinearLayout) main.getChildAt(1);
        HashMap<Integer, Integer[]> views = new HashMap<>();
        int boardX = (int) b.getX();
        int boardY = (int) b.getY();
        int count = 0;
        for(int index = 0; index < b.getChildCount(); ++index) {
            LinearLayout row = (LinearLayout) b.getChildAt(index);
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
                String xypos = convertIdToPostion(id);
                String[] pos = xypos.split("-");
                int xpos = Integer.parseInt(pos[0]);
                int ypos = Integer.parseInt(pos[1]);

                Cell touchingCell = board.getCell(xpos, ypos);
                touchingCell.addXYPosition(viewX, viewY);
                String summary = touchingCell.toString();
                tempSummary = summary;
                //return mainActivity.getResources().getResourceEntryName(nextChild.getId());
                return "" + id;
            }
        }


        return "";
    }

    public String getTempSummary(){
        return tempSummary;
    }
    public String convertIdToPostion(int id){
        int x = (id-1)%boardWidth;
        int y = (id-1)/boardWidth;
        String result = x + "-" + y;
        return result;
    }
}
