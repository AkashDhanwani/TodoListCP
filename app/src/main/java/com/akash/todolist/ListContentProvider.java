package com.akash.todolist;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by AKASH on 1/4/2018.
 */

public class ListContentProvider extends ContentProvider {

    DataBaseOperator dbo;
    @Override
    public boolean onCreate() {
        dbo = new DataBaseOperator(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();
        sqLiteQueryBuilder.setTables("list");
        SQLiteDatabase db = dbo.getWritableDatabase();
        Cursor cursor = sqLiteQueryBuilder.query(db, strings, s,strings1,null,null, s1);
        return cursor;
    }


    @Override
    public String getType(@NonNull Uri uri) {
        ArrayList<String> arrayList = dbo.getRecord();
        int size = arrayList.size();
        String display = "";
        for(int i=0 ; i<size ; i++)
            display = display+"\n"+arrayList.get(i);
        return display;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
