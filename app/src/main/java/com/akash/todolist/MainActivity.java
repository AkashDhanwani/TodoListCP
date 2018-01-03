package com.akash.todolist;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etNumber, etWork;
    Button btnAdd, btnUpdate, btnDelete;
    ListView lvRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        setRequestedOrientation(orientation);

        etNumber = findViewById(R.id.etNumber);
        etWork = findViewById(R.id.etWork);
        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        lvRead = findViewById(R.id.lvRead);

        final DataBaseOperator db = new DataBaseOperator(this);
        //ListContentProvider cp = new ListContentProvider();

        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,db.getRecord());
        lvRead.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = etNumber.getText().toString();
                String work = etWork.getText().toString();

                if(number.length() == 0 || work.length() == 0)
                {
                    Toast.makeText(MainActivity.this, "Please enter Number and Work", Toast.LENGTH_SHORT).show();
                    return;
                }

                db.addRecord(Integer.parseInt(number), work);
                ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,db.getRecord());
                lvRead.setAdapter(adapter);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = etNumber.getText().toString();
                String work = etWork.getText().toString();

                if(number.length() == 0 || work.length() == 0)
                {
                    Toast.makeText(MainActivity.this, "Please enter Number and Work", Toast.LENGTH_SHORT).show();
                    return;
                }
                db.modifyRecord(Integer.parseInt(number), work);
                ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,db.getRecord());
                lvRead.setAdapter(adapter);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = etNumber.getText().toString();

                if(number.length() == 0)
                {
                    etNumber.setError("Enter number");
                    return;
                }
                db.removeRecord(Integer.parseInt(number));
                ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,db.getRecord());
                lvRead.setAdapter(adapter);
            }
        });
    }

    public void onBackPressed()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Do you want to close this application?");
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog=builder.create();
        alertDialog.setTitle("Exit");
        alertDialog.show();
    }//end of onBackPressed
}