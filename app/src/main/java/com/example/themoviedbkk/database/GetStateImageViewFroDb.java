package com.example.themoviedbkk.database;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.themoviedbkk.R;

public class GetStateImageViewFroDb {

    @SuppressLint("Range")
    public GetStateImageViewFroDb(DatabaseFilmsMemory provideDatabase, ImageView imageLike, int filmId) {

        final int LIKE = 1;

        Cursor cursor = provideDatabase.getOneFilmByIdFilm(filmId);
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                if(cursor.getInt(cursor.getColumnIndex(DatabaseParameters.LIKE_COLUMN)) == LIKE)
                {
                    imageLike.setVisibility(View.VISIBLE);
                    imageLike.setImageResource(R.mipmap.plus02);
                } else {
                    imageLike.setVisibility(View.INVISIBLE);
                }
            }
        }
    }
}
