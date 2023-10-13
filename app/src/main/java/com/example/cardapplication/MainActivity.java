package com.example.cardapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.cardapplication.Quotes.Quote;
import com.example.cardapplication.Quotes.QuoteAdapter;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final ArrayList<Quote> quotes = new ArrayList<>();
    private final QuoteAdapter quoteAdapter = new QuoteAdapter(quotes);
    private int currentPage = 0;
    private boolean isLastPage = false;
    private boolean isLoading = false;


    int getPageSize() {
        return quotes.size();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerViewEvents = findViewById(R.id.recyclerViewEvents);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

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