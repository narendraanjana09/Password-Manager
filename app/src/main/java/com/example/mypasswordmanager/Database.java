package com.example.mypasswordmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    private static final String dbname="narru";
    private static final String tbname="details";




    public Database( Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String qry="create table "+tbname+"(name text ,email text NOT Null UNIQUE ,number text NOT Null UNIQUE ,code text NOT Null UNIQUE)";
        db.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
       db.execSQL("DROP TABLE IF EXISTS "+tbname);
       onCreate(db);

    }
    public Boolean addRecord(String s1, String s2,String n1, String i) {
      SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("name",s1);
        cv.put("email",s2);
        cv.put("number",n1);
        cv.put("code",i);
       long res= db.insert(tbname,null,cv);

       if(res==-1){
           return  false;
       }else{
           return true;

       }
    }
    public Cursor getData(){
        SQLiteDatabase db =this.getWritableDatabase();
        String qry="select * from "+tbname;
        Cursor crs=db.rawQuery(qry,null);
        return crs;

    }
    public boolean updateCode(String s) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + tbname + " SET code = "+ s );
        return true;
    }



}
