package com.lohead010.be;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

//SQLite Open Helper
public class MySQLiteHelper extends SQLiteOpenHelper
{
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "VerbDB";


    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String CREATE_VERB_TABLE = "CREATE TABLE Verb ( " +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "Word TEXT, " +
                    "Definition TEXT )";


            db.execSQL(CREATE_VERB_TABLE);

        }
        catch (Exception e) {

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older Person table if exists
        db.execSQL("DROP TABLE IF EXISTS Verb");

        // Create fresh Person table
        this.onCreate(db);
    }

    private static final String TABLE_VERB = "VERB";
    private static final String KEY_ID = "ID";
    private static final String KEY_Word = "Word";
    private static final String KEY_Definition = "Definition";

    private static final String[] COLUMNS = {KEY_ID, KEY_Word, KEY_Definition};

    public void addVerb(Words[] word)
    {

        // ArrayList<Verb> words = new ArrayList<Verb>();
        // get reference to writable DB
        final int count = word.length;
        //String[] dkd = new String[count];
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues[] cvArray = new ContentValues[count];
        for (int i = 0; i < count; i++) {
            Words temp = word[i];
            temp = new Words();
            ContentValues values = new ContentValues();
            values.put(KEY_Word, word[i].getWord());
            values.put(KEY_Definition, word[i].getDefinition());
            //cvArray[i] = values;

            db.insert(TABLE_VERB, null, values);
        }
        db.close();




    }

    public void deleteAllRecords()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_VERB);
    }
    public String selectWord(int index){
        String query ="";
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String select = "";
            db.execSQL("Select Word from VERB where ID=" + index + "");

            Cursor cursor = db.rawQuery("select Word from VERB where ID=" + index + "", null);
            if (cursor.moveToFirst()) {
                String name = cursor.getString(cursor.getColumnIndex(KEY_Word));
                query = name;
            }

        }
        catch(Exception e){

        }
        return query;
    }

    public List<Words> getVerb()
    {
        List<Words> words = new ArrayList<Words>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Verb", null);

        if (cursor.moveToFirst())
        {
            while (cursor.isAfterLast() == false)
            {
                int ID = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                String name = cursor.getString(cursor.getColumnIndex(KEY_Word));
                String def = cursor.getString(cursor.getColumnIndex(KEY_Definition));
                //int age = cursor.getInt(cursor.getColumnIndex(KEY_AGE));
                words.add(new Words(ID, name, def));
                cursor.moveToNext();
            }
        }
        return words;
    }
}

