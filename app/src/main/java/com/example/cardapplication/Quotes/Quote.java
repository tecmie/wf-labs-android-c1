package com.example.cardapplication.Quotes;

import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Quote {
    private String id;
    private String author;
    private String content;
    private ArrayList<String> tags;
    private String authorSlug;
    private int length;
    private String dateAdded;
    private String dateModified;


    public Quote(String id, String author, String content, ArrayList<String> tags, String authorSlug, int length, String dateAdded, String dateModified) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.tags = tags;
        this.authorSlug = authorSlug;
        this.length = length;
        this.dateAdded = dateAdded;
        this.dateModified = dateModified;
    }


    public Quote(JsonObject jsonObject) {


        tags = new ArrayList<String>();

        this.id = jsonObject.get("_id").getAsString();
        this.author = jsonObject.get("author").getAsString();
        this.content = jsonObject.get("content").getAsString();


        this.authorSlug = jsonObject.get("authorSlug").getAsString();
        this.length = jsonObject.get("length").getAsInt();

        this.dateAdded = formatDate(jsonObject.get("dateModified").getAsString());
        this.dateModified = formatDate(jsonObject.get("dateModified").getAsString());

        jsonObject.get("tags").getAsJsonArray().forEach(item -> {
            tags.add(item.getAsString().substring(0, 1).toUpperCase());
        });

        System.out.println(tags);

    }

    private String formatDate(String responseDate) {

        String outputDateString = "";

        SimpleDateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormatter = new SimpleDateFormat("MMM d, yyyy");


        try {
            Date date = inputFormatter.parse(responseDate);
            outputDateString = outputFormatter.format(date);

        } catch (ParseException e) {
            System.out.println("Unable to parse the date: " + e.getMessage());
        }

        return outputDateString;
    }

    public String get_id() {
        return id;
    }

    public void set_id(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getAuthorSlug() {
        return authorSlug;
    }

    public void setAuthorSlug(String authorSlug) {
        this.authorSlug = authorSlug;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }
}
