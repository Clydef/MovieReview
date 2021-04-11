package com.unasat.sr.moviereview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MovieReviewDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "movieReview"; // the name of our database
    private static final int DB_VERSION = 1; // the version of the database

    MovieReviewDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE NEW_MEMBERS (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "FIRST_NAME TEXT NOT NULL, "
                + "LAST_NAME TEXT NOT NULL, "
                /*+ "DATE_OF_BIRTH INTEGER, "*/
                + "GENDER TEXT NOT NULL, "
                + "EMAIL TEXT UNIQUE NOT NULL, "
                + "COUNTRY TEXT NOT NULL, "
                + "REGISTER_DATE DATETIME DEFAULT CURRENT_TIMESTAMP);");

        /*insertNewMember(db, "Clyde", "Ford",...
                6-11-1998, "M",
                "c.ford@unasat.sr", "Suriname", "4-3-2021");*/ // Manually insert data
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Boolean insertNewMember(String firstName,
                                   String lastName, /*int dateOfBirth,*/ String gender, String email, String country) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues newMembersInfo = new ContentValues();
        newMembersInfo.put("FIRST_NAME", firstName);
        newMembersInfo.put("LAST_NAME", lastName);
        //newMembersInfo.put("DATE_OF_BIRTH", dateOfBirth);
        newMembersInfo.put("GENDER", gender);
        newMembersInfo.put("EMAIL", email);
        newMembersInfo.put("COUNTRY", country);
        //newMembersInfo.put("REGISTER_DATE", registerDate); // Moet automatisch gebeuren
        long insert = db.insert("NEW_MEMBERS", null, newMembersInfo);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean deleteMember(String FIRST_NAME) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM NEW_MEMBERS WHERE FIRST_NAME = ?", new String[]{FIRST_NAME});
        if (cursor.getCount() > 0) {
            long insert = db.delete("NEW_MEMBERS", "FIRST_NAME=?", new String[]{FIRST_NAME});
            if (insert == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean updateMember(String FIRST_NAME, String EMAIL) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues newMembersInfo = new ContentValues();
        newMembersInfo.put("FIRST_NAME", FIRST_NAME);
        newMembersInfo.put("EMAIL", EMAIL);
        Cursor cursor = db.rawQuery("SELECT * FROM NEW_MEMBERS WHERE FIRST_NAME = ?", new String[]{FIRST_NAME});
        if (cursor.getCount() > 0) {
        long insert = db.update("NEW_MEMBERS", newMembersInfo, "FIRST_NAME=?", new String[] {FIRST_NAME});
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
        } else {
            return false;
        }
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM NEW_MEMBERS", null);
        return cursor;
    }
}