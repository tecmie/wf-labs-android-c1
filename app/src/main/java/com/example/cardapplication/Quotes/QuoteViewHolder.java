package com.example.cardapplication.Quotes;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardapplication.R;

public class QuoteViewHolder extends RecyclerView.ViewHolder {
    private TextView textViewTitle;
    private TextView textViewDate;
    private TextView textViewContent;
    RecyclerView recyclerViewTags;

    public TextView getTextViewTitle() {
        return textViewTitle;
    }

    public TextView getTextViewDate() {
        return textViewDate;
    }

    public TextView getTextViewContent() {
        return textViewContent;
    }

    public QuoteViewHolder(@NonNull View itemView) {
        super(itemView);
        this.textViewTitle = itemView.findViewById(R.id.textViewTitle);
        this.textViewDate = itemView.findViewById(R.id.textViewDate);
        this.textViewContent = itemView.findViewById(R.id.textViewContent);
//        this.recyclerViewTags =  itemView.findViewById(R.id.recyclerViewTags);
    }
}
