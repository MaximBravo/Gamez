package com.example.maximbravo.gamez.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.maximbravo.gamez.data.BoardContract.BoardEntry;
/**
 * Created by Maxim Bravo on 12/16/2016.
 */

public class BoardDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "board.db";

    private static final int DATABASE_VERSION = 1;

    public BoardDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_BOARD_TABLE = "CREATE TABLE "
                + BoardEntry.TABLE_NAME + "("
                + BoardEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BoardEntry.COLUMN_RES_ID + " INTEGER, "
                + BoardEntry.COLUMN_X_VALUE + " INTEGER NOT NULL, "
                + BoardEntry.COLUMN_Y_VALUE + " INTEGER NOT NULL, "
                + BoardEntry.COLUMN_WIDTH + " INTEGER NOT NULL, "
                + BoardEntry.COLUMN_HEIGHT + " INTEGER NOT NULL, "
                + BoardEntry.COLUMN_BACKGROUND_COLOR + " INTEGER, "
                + BoardEntry.COLUMN_STATE + " INTEGER NOT NULL DEFAULT 0"
                + ");";
        db.execSQL(SQL_CREATE_BOARD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
