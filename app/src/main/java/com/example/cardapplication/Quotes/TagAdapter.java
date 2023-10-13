package com.example.cardapplication.Quotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cardapplication.R;

import java.util.ArrayList;

public class TagAdapter extends RecyclerView.Adapter<TagViewHolder> {
    private ArrayList<String> data;

    public TagAdapter(ArrayList<String> data) {
        this.data = data;
    }

    @Override
    public TagViewHolder  onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tag_item, parent, false);
        return new TagViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TagViewHolder holder, int position) {
        String tag = data.get(position);

        holder.getTextViewTag() .setText(tag);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
