package com.jepsolucoes.fansmarvel.view.Activities.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jepsolucoes.fansmarvel.R;
import com.jepsolucoes.fansmarvel.model.AdapterInterface;
import com.jepsolucoes.fansmarvel.model.Results;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterChars extends RecyclerView.Adapter<AdapterChars.MyViewHolder> {

    private List<Results> listChars = new ArrayList<>();

    private AdapterInterface listener;

    public AdapterChars(AdapterInterface listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_chars, viewGroup, false);
        return new AdapterChars.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final Results results = listChars.get(i);
        myViewHolder.name.setText(results.getName());
        String url = results.thumbnail.getPath();
        String extension = results.thumbnail.getExtension();
        Picasso.get().load(url + "/portrait_xlarge." + extension).into(myViewHolder.thumbnail);


        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClickListener(results);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listChars != null ? listChars.size() : 0;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView description;
        ImageView thumbnail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textChars);
            thumbnail = itemView.findViewById(R.id.imageChar);
        }
    }

    public void addItens(List<Results> resultsList){
        this.listChars.addAll(resultsList);
        notifyDataSetChanged();
    }

    public void deleteItems(){
        listChars.clear();
// 2. Notify the adapter of the update
        notifyDataSetChanged();
    }
}
