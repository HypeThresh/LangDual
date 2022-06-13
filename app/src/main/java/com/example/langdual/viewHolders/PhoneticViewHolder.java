package com.example.langdual.viewHolders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.langdual.R;

public class PhoneticViewHolder extends RecyclerView.ViewHolder {
    public TextView fonetica;
    public ImageButton imageButton_audio;

    public PhoneticViewHolder(@NonNull View itemView) {
        super(itemView);
        fonetica = itemView.findViewById(R.id.fonetica);
        imageButton_audio = itemView.findViewById(R.id.imageBotton_audio);

    }
}

