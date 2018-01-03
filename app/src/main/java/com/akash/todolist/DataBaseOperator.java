package com.akash.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by AKASH on 12/22/2017.
 */

public class DataBaseOperator extends SQLiteOpenHelper {

    Context context;
    SQLiteDatabase db;

    DataBaseOperator(Context context)
    {
        super(context,"listdb",null,1);
        this.context = context;
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table list(number integer primary key,work text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void addRecord(int number,String work)
    {
        ContentValues values = new ContentValues();
        values.put("number",number);
        values.put("work",work);
        long rid = db.insert("list",null,values);
        if(rid<0)
        {
            Toast.makeText(context, "Insert issue", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(context, "Insert success", Toast.LENGTH_LONG).show();
        }
    }

    public ArrayList getRecord()
    {
        Cursor cursor = db.query("list",null,null,null,null,null,null);
        ArrayList<String> todo = new ArrayList<>();

        cursor.moveToFirst();

        if(cursor.getCount() > 0)
        {
            do {
                String number = cursor.getString(0);
                String work = cursor.getString(1);
                todo.add(number +"  " +work+ "\n");
            }while(cursor.moveToNext());
        }
        else
        {
            todo.add("No records to show");
        }
        return todo;
    }

    public void removeRecord(int number)
    {
        long rid = db.delete("list", "number="+number,null);
        if(rid == 0)
        {
            Toast.makeText(context, "Zero records deleted", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, rid+" Records deleted", Toast.LENGTH_SHORT).show();
        }
    }

    public void modifyRecord(int number, String work)
    {
        ContentValues values = new ContentValues();
        values.put("number", number);
        values.put("work", work);
        long rid = db.update("list",values, "number="+number,null);
        if(rid == 0)
        {
            Toast.makeText(context, "0 records updated", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, rid+" records updated", Toast.LENGTH_SHORT).show();
        }
    }
}
