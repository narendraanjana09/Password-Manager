package com.example.mypasswordmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class Database2 extends SQLiteOpenHelper {

    private static final String dbname="dataentry";
    dataActivity nm=new dataActivity();
    private static final String tbname="entry";

    public Database2( Context context) {
        super(context, dbname, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry="create table "+tbname+"(id integer primary key autoincrement,PlatForm text,email text NOT Null  ,password text NOT Null )";
        db.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+tbname);
        onCreate(db);
    }
    public Boolean addRecord(String s1, String s2,String n1) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("PlatForm",s1);
        cv.put("email",s2);
        cv.put("password",n1);

        long res= db.insert(tbname,null,cv);

        if(res==-1){
            return  false;
        }else{
            return true;

        }
    }
    Cursor readAllData(){
        String qry="SELECT * FROM "+tbname;
        SQLiteDatabase db= this.getReadableDatabase();

        Cursor crs=null;
        if(db!=null){
          crs=  db.rawQuery(qry,null);
        }
        return crs;
    }

    public Boolean updateData (String id,String s1, String s2,String s3) {
      /*  SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + tbname + " SET  PlatForm = '"+ s1 +"',email ='"+s2+"',password='"+s3+"' WHERE id ="+id);
        return true;
*/


        SQLiteDatabase db= this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("PlatForm",s1);
        cv.put("email",s2);
        cv.put("password",s3);

        long result = db.update(tbname, cv, "id=?",new String[]{id});




        if (result == -1) {

            return false;



        } else{
                return true;
    }

}
    public void deleteData(String s1){
        SQLiteDatabase db= this.getReadableDatabase();
        db.delete(tbname,"id=?",new String[]{s1});


    }
    public void deleteAllData(){
        SQLiteDatabase db= this.getReadableDatabase();
        db.delete(tbname,null,null);


    }
}