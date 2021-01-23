package com.example.mydatabase;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddRecodActivity extends AppCompatActivity {
      EditText Ename,Eage,Equalification;
      Button AddButton;
     ActionBar actionBar;
   String name,age,qualification;
    private ArrayList<Model> arrayList;
    RecyclerView recyclerView;
   DataBaseHelper dbHelper;
   Context context;
   Button ShowData,UpDat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recod);
      Ename=findViewById(R.id.Ename);
        Eage=findViewById(R.id.Eage);
        Equalification=findViewById(R.id.Equlification);
       AddButton=findViewById(R.id.AddButton);
       actionBar = getSupportActionBar();
       recyclerView=findViewById(R.id.recyclerview);
       arrayList = new ArrayList<>();
       ShowData= findViewById(R.id.ShowData);
        UpDat = findViewById(R.id.UpDat);
       dbHelper = new DataBaseHelper(this);
       Intent intent = getIntent();
       String n = intent.getStringExtra("name");
       String a = intent.getStringExtra("age");
       String Q = intent.getStringExtra("Qualification");
        Ename.setText(n);
        Eage.setText(a);
        Equalification.setText(Q);
        UpDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = Ename.getText().toString();
                age = Eage.getText().toString();
                qualification = Equalification.getText().toString();
                Model model = new Model(name,age,qualification);

                DataBaseHelper db = new DataBaseHelper(AddRecodActivity.this);
             int result = db.Update(model);
            if (result>0){
                Toast.makeText(AddRecodActivity.this, "Updated", Toast.LENGTH_SHORT).show();
            }

            }
        });

        //sava data to database
       AddButton.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
                   getData();
           }
       });
       ShowData.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent =  new Intent(AddRecodActivity.this,MainActivity.class);
               startActivity(intent);
           }
       });


    }

    private void getData(){


        name = Ename.getText().toString().trim();
        age = Eage.getText().toString().trim();
        qualification = Equalification.getText().toString().trim();
        boolean id = dbHelper.insertInfo(name,age,qualification);

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(age) || TextUtils.isEmpty(qualification)){
            Toast.makeText(AddRecodActivity.this, "Empty", Toast.LENGTH_SHORT).show();
        }else {
            if (id){
                Toast.makeText(this,"Inserted", Toast.LENGTH_SHORT).show();
            }
            Intent i = new Intent(AddRecodActivity.this,MainActivity.class);
            startActivity(i);
        }
        //

//        /*DataBaseHelper objdatabase =  new DataBaseHelper(this);
//        SQLiteDatabase objsqlidatabase= objdatabase.getReadableDatabase();
//        if (objsqlidatabase !=null){
//            Cursor cursor = objsqlidatabase.rawQuery( "SELECT * FROM USERS", null);
//            if (cursor.getCount() == 0){
//                Toast.makeText(this,"data not inserted",Toast.LENGTH_SHORT).show();
//            }else {
//                while (cursor.moveToNext()){
////                    arrayList.add(new Model(cursor.getInt(0),
////                            cursor.getString(1),
////                            cursor.getString(2),
////                            cursor.getString(3);
//                    arrayList.add(new Model(
//
//                            cursor.getString(1),
//                            cursor.getString(2),
//                            cursor.getString(3)
//                    ));
//
//               }
//                Adapter adapter = new Adapter(this,arrayList);
//
//
//               recyclerView.setLayoutManager(new LinearLayoutManager(AddRecodActivity.this));
//               recyclerView.setAdapter(adapter);
//            }
//
//        }*/

        }

    public boolean onSupportNavigateUp(){
        onBackPressed();
        return super.onSupportNavigateUp();
    }


}