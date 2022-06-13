package com.example.langdual.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.langdual.R;
import com.example.langdual.models.significado;
import com.example.langdual.viewHolders.MeaningViewHolder;

import java.util.List;

public class MeaningAdapter extends RecyclerView.Adapter<MeaningViewHolder> {
    private Context context;
    protected List<significado> significadoList;

    public MeaningAdapter(Context context, List<significado> significadoList) {
        this.context = context;
        this.significadoList = significadoList;
    }

    @NonNull
    @Override
    public MeaningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MeaningViewHolder(LayoutInflater.from(context).inflate(R.layout.significado_list_items,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MeaningViewHolder holder, int position) {
        holder.partsOfSpeech.setText("Part of Speech: "+significadoList.get(position).getPartOfSpeech());
        holder.recyclerDefinicion.setHasFixedSize(true);
        holder.recyclerDefinicion.setLayoutManager(new GridLayoutManager(context,1));

        DefinitionsAdapater definicionAdapter = new DefinitionsAdapater(context,significadoList.get(position).getDefinitions());
        holder.recyclerDefinicion.setAdapter(definicionAdapter);
    }

    @Override
    public int getItemCount() {
        return significadoList.size();
    }
}
