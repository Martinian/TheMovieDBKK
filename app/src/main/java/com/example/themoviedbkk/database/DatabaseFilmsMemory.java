package com.example.themoviedbkk.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseFilmsMemory {

    private final static String DATABASE_NAME = DatabaseParameters.DATABASE_NAME;
    private final static int DATABASE_VERSION = DatabaseParameters.DATABASE_VERSION;

    private final static String TABLE_FILMS = DatabaseParameters.TABLE_FILMY;
    private final static String INDEX_UNIQUE_TABLE_FILMY_ON_ID_FILM_COLUMN = DatabaseParameters.KEY_UNIQUE_INDEX_TABLE_FILMY_ON_ID_FILM_COLUMN;

    public  final static String KEY_ROWID = DatabaseParameters.ID_ROWID;
    public  final static String KEY_ID_FILM = DatabaseParameters.ID_FILM_COLUMN;
    public  final static String KEY_LIKE = DatabaseParameters.LIKE_COLUMN;


    private final Context mCtx;
    private DevicesHelper mDbHelper;
    private SQLiteDatabase mDb;


    private final String CREATE_TABLE_FILMS =
            "CREATE TABLE if not exists " + TABLE_FILMS + " (" +
                    KEY_ROWID + " integer PRIMARY KEY autoincrement ," +
                    KEY_ID_FILM +  " integer default 0 ," +
                    KEY_LIKE  + " integer default 0 " + ");";

    private final String CREATE_INDEX_OF_TABLE_FILMS =
            "CREATE UNIQUE INDEX " + INDEX_UNIQUE_TABLE_FILMY_ON_ID_FILM_COLUMN + " ON " + TABLE_FILMS + " (" + KEY_ID_FILM + ");";

    public DatabaseFilmsMemory(Context ctx ) {
        this.mCtx = ctx;

    }

    public DatabaseFilmsMemory open() throws SQLException {
        mDbHelper = new DevicesHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }

    }

    public boolean isOpen() {

        return mDb.isOpen();
    }

    private class DevicesHelper extends SQLiteOpenHelper {

        DevicesHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(CREATE_TABLE_FILMS);
            db.execSQL(CREATE_INDEX_OF_TABLE_FILMS);

            Log.i("bazasql", "koniec create bazy");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


            Log.i("onupgrade", String.valueOf(oldVersion) + " --- " + String.valueOf(newVersion));

            db.execSQL("DROP TABLE IF EXISTS " + TABLE_FILMS);

            Log.i("bazasql", "koniec update");

            onCreate(db);
        }

    }

    public boolean AddNewIdFilm(int idFilm ,int likeFilm) {

        final int WRITE_ERROR  = -1;

        boolean okTransaction;

        ContentValues devValues = new  ContentValues();
        devValues.put(KEY_ID_FILM, idFilm);
        devValues.put(KEY_LIKE, likeFilm);


        mDb.beginTransaction();

        long rowIdInsertNotf =  mDb.insert(TABLE_FILMS,null,devValues);

        if (rowIdInsertNotf != WRITE_ERROR)
            okTransaction = true;
        else
            okTransaction = false;

        mDb.setTransactionSuccessful();
        mDb.endTransaction();

        return okTransaction;

    }

    public boolean UpdateTableLikeFilm(int idFilm ,int likeFilm) {

        final int UPDATE_SCORE  = 0;
        String whereClause = KEY_ID_FILM  + " = ?";
        String[] whereArgs = new String[]{ String.valueOf(idFilm)};

        ContentValues deviceValues = new  ContentValues();
        deviceValues.put(KEY_LIKE, likeFilm);

        mDb.beginTransaction();

        long rowIdProject = mDb.update(TABLE_FILMS, deviceValues, whereClause, whereArgs);

        mDb.setTransactionSuccessful();
        mDb.endTransaction();

        return rowIdProject != UPDATE_SCORE;

    }

    public Cursor getFilmIdFilm(Long idFilm) {

        String[] whereArgs = new String[]{String.valueOf(idFilm)};
        String selection = KEY_ID_FILM + " = ?";

        return mDb.query(TABLE_FILMS, null, selection, whereArgs, null, null, null);


    }


}
