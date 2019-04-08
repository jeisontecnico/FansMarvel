package com.jepsolucoes.fansmarvel.viewmodel.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jepsolucoes.fansmarvel.R;
import com.jepsolucoes.fansmarvel.model.Characters;

import java.util.List;

public class AdapterChars extends RecyclerView.Adapter<AdapterChars.MyViewHolder> {

    private List<Characters> chars;
    private Context context;

    public AdapterChars(List<Characters> chars, Context context) {
        this.chars = chars;
        this.context = context;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_chars,viewGroup, false);
        return new AdapterChars.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
            Characters characters = chars.get(i);
            myViewHolder.name.setText(characters.getName());
    }

    @Override
    public int getItemCount() {
        return chars.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView description;
        ImageView thumbnail;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textChars);
            thumbnail = itemView.findViewById(R.id.imageThumbChars);
        }
    }


}
