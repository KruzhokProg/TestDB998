package com.example.testdb998;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.testdb998.Models.Hobby;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {


   static final String dbName = "userDB";

   static final String userTable = "User";
   static final String colUserId = "Id";
   static final String colUserEmail = "Email";
   static final String colUserPass = "Password";

   static final String hobbyTable = "Hobby";
   static final String colHobbyId = "Id";
   static final String colHobbyName = "Name";
   static final String colHobbyImage = "Image";

   static final String userHobbyTable = "UserHobby";
   static final String colUHUserId = "UserId";
   static final String colUHHobbyId = "HobbyId";
//   static final String colHobbyUserId = "UserId";



    public DataBaseHelper(@Nullable Context context) {
        super(context, dbName, null, 7);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + userTable + "(" +
                colUserId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                colUserEmail + " TEXT UNIQUE NOT NULL," +
                colUserPass + " TEXT NOT NULL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + hobbyTable + "(" +
                colHobbyId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                colHobbyName + " TEXT UNIQUE NOT NULL," +
                colHobbyImage + " INTEGER NOT NULL);");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + userHobbyTable + "(" +
                colUHUserId + " INTEGER NOT NULL," +
                colUHHobbyId + " INTEGER NOT NULL," +
                " PRIMARY KEY (" + colUHUserId + "," + colUHHobbyId + ")," +
                " FOREIGN KEY (" + colUHUserId + ") REFERENCES " + userTable + "(" + colUserId + ")," +
                " FOREIGN KEY (" + colUHHobbyId + ") REFERENCES " + hobbyTable + "(" + colHobbyId + "));");

        //fillHobby();
//                colHobbyUserId + " INTEGER NOT NULL, FOREIGN KEY (" + colHobbyUserId + ") REFERENCES " + userTable + " (" + colUserId + "));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + userHobbyTable);
        db.execSQL("DROP TABLE IF EXISTS " + userTable);
        db.execSQL("DROP TABLE IF EXISTS " + hobbyTable);
        onCreate(db);
    }


    public List<Hobby> getHobbies(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + hobbyTable;
        Cursor c = db.rawQuery(query, null);
        List<Hobby> hobbies = new ArrayList<>();
        if(c.moveToFirst()){
            do{
                Hobby newHobby = new Hobby();
                newHobby.setName(c.getString(c.getColumnIndex(colHobbyName)));
                newHobby.setImage(c.getInt(c.getColumnIndex(colHobbyImage)));
                hobbies.add(newHobby);
            }while(c.moveToNext());
        }

        return hobbies;
    }

    public void fillHobby(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + hobbyTable;
        Cursor c = db.rawQuery(query, null);
        if(!c.moveToFirst()){
            SQLiteDatabase dbW = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(colHobbyName, "Выберите вариант");
            cv.put(colHobbyImage, 0);
            dbW.insert(hobbyTable, null, cv);

            cv.put(colHobbyName, "кататься на велосипеде");
            cv.put(colHobbyImage, R.drawable.hobby_bycycle);
            dbW.insert(hobbyTable, null, cv);

            cv.put(colHobbyName, "рисование");
            cv.put(colHobbyImage, R.drawable.hobby_drawing);
            dbW.insert(hobbyTable, null, cv);

            cv.put(colHobbyName, "играть на гитаре");
            cv.put(colHobbyImage, R.drawable.hobby_playing_guitar);
            dbW.insert(hobbyTable, null, cv);

            cv.put(colHobbyName, "игра на пианино");
            cv.put(colHobbyImage, R.drawable.hobby_playing_piano);
            dbW.insert(hobbyTable, null, cv);
        }
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

    public Integer getHobbyId(Integer position) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + colHobbyId + " FROM " + hobbyTable;
        Cursor c = db.rawQuery(query, null);

        if(c.moveToFirst()){
            Integer hobbyId = c.getInt(c.getColumnIndex(colHobbyId));
            return hobbyId;
        }
        else
            return -1;
    }

    public void addNewHobby(Integer userId, Integer hobbyId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("userId", userId);
        cv.put("hobbyId", hobbyId);
        db.insert(userHobbyTable, null, cv);
    }

}
