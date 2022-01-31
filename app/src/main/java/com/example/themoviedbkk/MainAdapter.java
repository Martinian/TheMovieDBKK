package com.example.themoviedbkk;

import android.annotation.SuppressLint;
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

    private List<Result> results;

    public MainAdapter(List<Result> results) {

        this.results = results;
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

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, int position) {

        holder.textFilmId.setText(String.valueOf(results.get(position).getId()));
        holder.textTitle.setText(results.get(position).getTitle());
        holder.imageLike.setImageResource(R.mipmap.plus02); // tutaj trzeba wstawić warunek na widoczność i odczyt z bazy danych
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
