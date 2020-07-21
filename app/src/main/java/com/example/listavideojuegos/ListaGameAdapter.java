package com.example.listavideojuegos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;

import Modelo.Game;

public class ListaGameAdapter extends RecyclerView.Adapter<ListaGameAdapter.ViewHolder>{

    private ArrayList<Game> dataset;

    public ListaGameAdapter(){
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.itm_games,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
     Game g= dataset.get(position);
     holder.nombreTextView.setText(g.getName());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adiconarGame(ArrayList<Game> listaGame) {
        dataset.addAll(listaGame);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView fotoImageView;
        private TextView nombreTextView;

        public ViewHolder(View itemView){
            super(itemView);
            fotoImageView=(ImageView) itemView.findViewById(R.id.fotoImageView);
            nombreTextView=(TextView) itemView.findViewById(R.id.nombreTextView);

        }



    }
}
