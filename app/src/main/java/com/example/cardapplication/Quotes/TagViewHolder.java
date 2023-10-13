package com.example.cardapplication.Quotes;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardapplication.R;

public class TagViewHolder extends RecyclerView.ViewHolder {
    private TextView textViewTag;

    public TextView getTextViewTag() {
        return textViewTag;
    }

    public TagViewHolder(@NonNull View itemView) {
        super(itemView);
        this.textViewTag = itemView.findViewById(R.id.textViewTag);

    }
}