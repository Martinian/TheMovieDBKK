package com.example.themoviedbkk;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.themoviedbkk.models.Result;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private final MainActivity mainActivity;
    private List<Result> results;

    public MainAdapter(List<Result> results, MainActivity mainActivity) {

        this.results = results;
        this.mainActivity = mainActivity;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.text_film_id)
        public TextView textFilmId;

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.text_title)
        public TextView textTitle;

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.image_like)
        public ImageView imageLike;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.textFilmId.setText(String.valueOf(results.get(position).getId()));
        holder.textTitle.setText(results.get(position).getTitle());
        //holder.imageLike.setImageResource(R.mipmap.plus02); // tutaj trzeba wstawić warunek na widoczność i odczyt z bazy danych
        mainActivity.setStateImageView(holder.imageLike);

        holder.textFilmId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("LOGKK", "1111");
                mainActivity.afterClickOnItemIageView(holder.imageLike,position);
            }
        });

        holder.textTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("LOGKK", "22222");
                mainActivity.afterClickOnItem(position);
            }
        });

        holder.imageLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("LOGKK", "33333");

                holder.imageLike.setVisibility(View.INVISIBLE);

            }
        });

    }

    @Override
    public int getItemCount() {

        return results.size();
    }

    public List<Result> getResults() {

        return results;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateAllFilms(List<Result> results) {

        this.results =  results;
        notifyDataSetChanged();
    }

}
