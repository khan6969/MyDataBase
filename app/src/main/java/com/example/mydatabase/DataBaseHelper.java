package com.example.mydatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.Display;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "USERS";
    private static final String DB_NAME = "USERS_INFO";

    private static final int DB_VERSION = 1;
    private static final String C_ID = "ID";
    private static final String C_NAME = "NAME";
    private static final String C_AGE = "AGE";
    private static final String C_QUALIFICATION = "QUALIFICATION";
    private String CREATE_TABLE = "create table USERS(C_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL" +
            ",C_NAME VARCHAR(255),C_AGE VARCHAR(255),C_QUALIFICATION VARCHAR(255))";


    public DataBaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertInfo(String name, String age, String qualification) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(C_NAME, name);
        values.put(C_AGE, age);
        values.put(C_QUALIFICATION, qualification);
        long id = db.insert(TABLE_NAME, null, values);
        // db.close();
        if (id == -1) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<Model> getAllData(String orderBy) {
        ArrayList<Model> arrayList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " ORDER BY" + orderBy;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToNext()) {
            do {
                Model model = new Model(
                        "" + cursor.getInt(cursor.getColumnIndex(C_NAME)),
                        "" + cursor.getInt(cursor.getColumnIndex(C_AGE)),
                        "" + cursor.getInt(cursor.getColumnIndex(C_QUALIFICATION)));
                arrayList.add(model);
            } while (cursor.moveToNext());
        }
        db.close();
        return arrayList;
    }

    // public void DELETE(){
    //     SQLiteDatabase db = this.getWritableDatabase();
    //  db.delete(C_NAME,C_AGE,null);
    //  }
    //delete data from database based on name
    public int deleteStudent(String sid) {

        SQLiteDatabase db = getWritableDatabase();

        return db.delete(TABLE_NAME, "NAME=?", new String[]{String.valueOf(sid)});

    }

    public int Update(Model model) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
      //  cv.put(C_ID,model.getId());
        cv.put(C_NAME, model.getName());
        cv.put(C_AGE, model.getAge());
        cv.put(C_QUALIFICATION, model.getQualification());
        return db.update(TABLE_NAME, cv, "Name= ?", new String[]{String.valueOf(model.getName())});
    }

}

