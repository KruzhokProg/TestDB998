package com.example.testdb998;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {


   static final String dbName = "userDB";

   static final String userTable = "User";
   static final String colUserId = "Id";
   static final String colUserEmail = "Email";
   static final String colUserPass = "Password";

   static final String hobbyTable = "Hobby";
   static final String colHobbyId = "Id";
   static final String colHobbyName = "Name";
   static final String colHobbyUserId = "UserId";

    public DataBaseHelper(@Nullable Context context) {
        super(context, dbName, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + userTable + "(" +
                colUserId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                colUserEmail + " TEXT UNIQUE NOT NULL," +
                colUserPass + " TEXT NOT NULL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + hobbyTable + "(" +
                colHobbyId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                colHobbyName + " TEXT UNIQUE NOT NULL, " +
                colHobbyUserId + " INTEGER NOT NULL, FOREIGN KEY (" + colHobbyUserId + ") REFERENCES " + userTable + " (" + colUserId + "));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + userTable);
        db.execSQL("DROP TABLE IF EXISTS " + hobbyTable);
        onCreate(db);
    }

    public long createUser(User newUser){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(colUserEmail, newUser.getEmail());
        cv.put(colUserPass, newUser.getPassword());
        long newUserId = db.insert(userTable, null, cv);
        return newUserId;
    }

    public int checkUser(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();


        String selectQuery = "SELECT " + colUserId + " FROM " + userTable + " WHERE " + colUserEmail + "=?" +
                " AND " + colUserPass + "=?";
        Cursor c = db.rawQuery(selectQuery, new String[]{email,password});

        if (c.moveToFirst()) {
            Integer userId = c.getInt(c.getColumnIndex(colUserId));
            return userId;
        }
        else
            return -1;
    }

}
