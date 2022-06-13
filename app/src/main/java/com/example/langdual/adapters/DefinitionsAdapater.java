package com.example.langdual.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.langdual.R;
import com.example.langdual.models.definicion;
import com.example.langdual.viewHolders.DefinitionsViewHolder;

import java.util.List;

public class DefinitionsAdapater extends RecyclerView.Adapter<DefinitionsViewHolder> {

    private Context context;
    private List<definicion> definicionesList;

    public DefinitionsAdapater(Context context, List<definicion> definicionesList) {
        this.context = context;
        this.definicionesList = definicionesList;
    }

    @NonNull
    @Override
    public DefinitionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DefinitionsViewHolder(LayoutInflater.from(context).inflate(R.layout.definicions_list_items,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull DefinitionsViewHolder holder, int position) {
        holder.definiciones.setText("Definition: "+definicionesList.get(position).getDefinition());
        holder.ejemplos.setText("Example: "+definicionesList.get(position).getExample());
        StringBuilder sinonimos = new StringBuilder();
        StringBuilder antonimos = new StringBuilder();

        sinonimos.append(definicionesList.get(position).getSynonyms());
        antonimos.append(definicionesList.get(position).getAntonyms());

        holder.sinonimos.setText(sinonimos);
        holder.antonimos.setText(antonimos);

        holder.sinonimos.setSelected(true);
        holder.antonimos.setSelected(true);
    }

    @Override
    public int getItemCount() {
        return definicionesList.size();
    }
}
