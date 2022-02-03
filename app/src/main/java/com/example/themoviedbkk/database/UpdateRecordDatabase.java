package com.example.themoviedbkk.database;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.themoviedbkk.R;

public class UpdateRecordDatabase {

    @SuppressLint("Range")
    public UpdateRecordDatabase(DatabaseFilmsMemory provideDatabase, ImageView imageLike, int filmId) {

        final int LIKE = 1;
        final int NO_LIKE = 0;

        Cursor cursor = provideDatabase.getOneFilmByIdFilm(filmId);
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                if(cursor.getInt(cursor.getColumnIndex(DatabaseParameters.LIKE_COLUMN)) == LIKE)
                {
                    boolean bool = provideDatabase.UpdateTableLikeFilm(filmId,NO_LIKE);
                    imageLike.setVisibility(View.INVISIBLE);
                } else {
                    boolean bool = provideDatabase.UpdateTableLikeFilm(filmId,LIKE);
                    imageLike.setVisibility(View.VISIBLE);
                    imageLike.setImageResource(R.mipmap.plus02);
                }
            }
        } else {
            boolean bool = provideDatabase.AddNewIdFilm(filmId, LIKE);
            imageLike.setVisibility(View.VISIBLE);
            imageLike.setImageResource(R.mipmap.plus02);
        }
    }
}
