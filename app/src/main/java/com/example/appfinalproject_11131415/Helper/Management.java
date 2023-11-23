package com.example.appfinalproject_11131415.Helper;

import android.content.Context;

public class Management {
    private Context context;
    private TinyDB tinyDB;

    public Management(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }
}
