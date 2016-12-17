package com.example.maximbravo.gamez.data;

import android.provider.BaseColumns;

/**
 * Created by Maxim Bravo on 12/16/2016.
 */

public final class BoardContract {
    public BoardContract(){

    }
    public static class BoardEntry implements BaseColumns {
        public static final String TABLE_NAME = "board";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_RES_ID = "resid";
        public static final String COLUMN_X_VALUE = "xvalue";
        public static final String COLUMN_Y_VALUE = "yvalue";
        public static final String COLUMN_WIDTH = "width";
        public static final String COLUMN_HEIGHT = "height";
        public static final String COLUMN_BACKGROUND_COLOR = "color";
        public static final String COLUMN_STATE = "state";

        public static final int STATE_EMPTY = 0;
        public static final int STATE_WHITE = 1;
        public static final int STATE_BLACK = 2;
    }
}
