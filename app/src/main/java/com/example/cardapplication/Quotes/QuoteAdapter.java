package com.example.cardapplication.Quotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardapplication.R;

import java.util.ArrayList;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteViewHolder> {

    private ArrayList<Quote> data;

    public QuoteAdapter(ArrayList<Quote> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_card_item, parent, false);
        LinearLayoutManager linearLayoutManagerTags = new LinearLayoutManager(parent.getContext(), RecyclerView.HORIZONTAL, false);

        return new QuoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteViewHolder holder, int position) {
        Quote quote = data.get(position);
        holder.getTextViewTitle().setText(quote.getAuthor());
        holder.getTextViewDate().setText(quote.getDateAdded());
        holder.getTextViewContent().setText(quote.getContent());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}



