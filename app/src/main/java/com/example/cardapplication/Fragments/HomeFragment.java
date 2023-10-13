package com.example.cardapplication.Fragments;

import android.app.Fragment;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardapplication.Quotes.Quote;
import com.example.cardapplication.Quotes.QuoteAdapter;
import com.example.cardapplication.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import java.util.ArrayList;
import java.util.Objects;


public class HomeFragment extends Fragment {

    private final ArrayList<Quote> quotes = new ArrayList<>();
    private final QuoteAdapter quoteAdapter = new QuoteAdapter(quotes);
    private int currentPage = 0;
    private boolean isLastPage = false;
    private boolean isLoading = false;


    int getPageSize() {
        return quotes.size();
    }

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home, container, false);





        RecyclerView recyclerViewEvents = view.findViewById(R.id.recyclerViewEvents);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);

        recyclerViewEvents.setLayoutManager(linearLayoutManager);
        recyclerViewEvents.setAdapter(quoteAdapter);


        getQuotes();

        recyclerViewEvents.addOnScrollListener(new RecyclerView.OnScrollListener() {


            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (!isLastPage && !isLoading) {
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0 && totalItemCount >= getPageSize()) {

                        getQuotes();
                    }
                }
            }

        });


        return view;
    }


    void getQuotes() {
        isLoading = true;
        String url = "https://api.quotable.io/quotes?page=" + (currentPage + 1);
        System.out.println(url);
        Ion.with(this).load(url).asJsonObject().withResponse().setCallback(new FutureCallback<Response<JsonObject>>() {
            @Override
            public void onCompleted(Exception e, Response<JsonObject> response) {
                currentPage = response.getResult().getAsJsonObject().get("page").getAsInt();

                JsonArray result = response.getResult().getAsJsonObject().get("results").getAsJsonArray();


                result.forEach(item -> {
                    quotes.add(new Quote(item.getAsJsonObject()));
                });

                isLoading = false;

                quoteAdapter.notifyDataSetChanged();

            }
        });
    }
}