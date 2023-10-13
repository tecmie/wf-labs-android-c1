package com.example.cardapplication;

import android.app.Application;

import androidx.room.Room;

import com.example.cardapplication.DatabaseModels.AppDatabase;

public class MainApp extends Application {
    private static AppDatabase appDatabase;


    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "card-app-1").build();
    }
}
