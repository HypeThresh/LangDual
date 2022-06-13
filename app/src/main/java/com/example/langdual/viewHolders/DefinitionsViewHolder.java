package com.example.langdual.viewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.langdual.R;

public class DefinitionsViewHolder extends RecyclerView.ViewHolder {

    public TextView definiciones,ejemplos,sinonimos,antonimos;

    public DefinitionsViewHolder(@NonNull View itemView) {
        super(itemView);
        definiciones = itemView.findViewById(R.id.definiciones);
        ejemplos = itemView.findViewById(R.id.ejemplos);
        sinonimos = itemView.findViewById(R.id.sinonimos);
        antonimos = itemView.findViewById(R.id.antonimos);
    }
}
