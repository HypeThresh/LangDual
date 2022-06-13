package com.example.langdual.viewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.langdual.R;

public class MeaningViewHolder extends RecyclerView.ViewHolder {
    public TextView partsOfSpeech;
    public RecyclerView recyclerDefinicion;

    public MeaningViewHolder(@NonNull View itemView) {
        super(itemView);

        partsOfSpeech = itemView.findViewById(R.id.partsofSpeech);
        recyclerDefinicion = itemView.findViewById(R.id.recyclerDefinicion);
    }
}

