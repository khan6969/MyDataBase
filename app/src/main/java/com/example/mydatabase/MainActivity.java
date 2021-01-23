 package com.example.mydatabase;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
     FloatingActionButton FBtn;
     ActionBar actionBar;
     RecyclerView recyclerView;
     DataBaseHelper dataBaseHelper;
     Button AddButton;
     EditText name,age,qualification;
    private ArrayList<Model> arrayList;
    Button Delete,Update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.info);
        arrayList = new ArrayList<>();
        FBtn = findViewById(R.id.FButton);
        recyclerView = findViewById(R.id.recyclerview);
        dataBaseHelper = new DataBaseHelper(this);


        DataBaseHelper objdatabase =  new DataBaseHelper(this);
        SQLiteDatabase objsqlidatabase= objdatabase.getReadableDatabase();
        if (objsqlidatabase !=null){
            Cursor cursor = objsqlidatabase.rawQuery( "SELECT * FROM USERS", null);
            if (cursor.getCount() == 0){
                Toast.makeText(this,"data not inserted",Toast.LENGTH_SHORT).show();
            }else {
                while (cursor.moveToNext()){
//                    arrayList.add(new Model(cursor.getInt(0),
//                            cursor.getString(1),
//                            cursor.getString(2),
//                            cursor.getString(3);
                    arrayList.add(new Model(

                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3)
                    ));

               }
                Adapter adapter = new Adapter(this,arrayList);


               recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
               recyclerView.setAdapter(adapter);
            }

        }

        //   showRecord();

        FBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isInserted = dataBaseHelper.insertInfo(name.getText().toString(), age.getText().toString(),
                        qualification.getText().toString());
                if (isInserted = true) {
                    Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, AddRecodActivity.class));
                    name.setText(null);
                    age.setText(null);
                    qualification.setText(null);
                } else {
                    Toast.makeText(MainActivity.this, "Data not inserted inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
