package com.example.mypasswordmanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class dataActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
TextView name,head;

EditText email,pass,platForm;
Button add1,add2,back;
    Database2 db;
    ArrayList<String> platform_Name,email_id,pwd;
    ArrayList<Integer> id;
    RecyclerView rv;

    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        Intent intent=new Intent(dataActivity.this,MainActivity.class);


        name=findViewById(R.id.nametxt);
        platForm=findViewById(R.id.pfnametxt);
        email=findViewById(R.id.emailtxt);
        pass=findViewById(R.id.passtxt);
        head=findViewById(R.id.addtxt);
        rv=findViewById(R.id.recyclerView);

        add1=findViewById(R.id.addbtn1);
        add2=findViewById(R.id.addbtn2);
        back=findViewById(R.id.backbtn);

        db=new Database2(dataActivity.this);
        id=new ArrayList<>();
        platform_Name=new ArrayList<>();
        email_id=new ArrayList<>();
        pwd=new ArrayList<>();

        displayData();

        customAdapter= new CustomAdapter(dataActivity.this,id,platform_Name,email_id,pwd);
        rv.setAdapter(customAdapter);
        rv.setLayoutManager(new LinearLayoutManager(dataActivity.this));


    }
    void displayData(){
        Cursor crs=db.readAllData();
        if(crs.getCount()==0){
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }
        else{
            while (crs.moveToNext()){
                 id.add(crs.getInt(0));
                 platform_Name.add(crs.getString(1));
                email_id.add(crs.getString(2));
                pwd.add(crs.getString(3));
            }
        }
    }

  public Boolean allRight(){
      if(platForm.getText().toString().length()==0||email.getText().toString().length()==0||pass.getText().toString().length()==0){
          return false;
      }else{
          return  true;
      }
  }

  public void addRecord(View view) {

         if(allRight()){
             db = new Database2(this);
            Boolean res = db.addRecord(platForm.getText().toString(), email.getText().toString(),pass.getText().toString());


            if(res) {
                visible2();

                startActivity(getIntent());

            }
            else{

                String ans="Something`s Wrong";
                Toast.makeText(this, ans, Toast.LENGTH_LONG).show();
            }}
            else{
                String ans="Entries Can`t Empty";
             Toast.makeText(this, ans, Toast.LENGTH_SHORT).show();
             }


        }






    public void visible(View view) {
        add1.setVisibility(View.INVISIBLE);
        rv.setVisibility(View.INVISIBLE);


        add2.setVisibility(View.VISIBLE);
        back.setVisibility(View.VISIBLE);
        platForm.setVisibility(View.VISIBLE);
        email.setVisibility(View.VISIBLE);
        pass.setVisibility(View.VISIBLE);
        head.setVisibility(View.VISIBLE);

    }
    public void visible2() {
        add1.setVisibility(View.VISIBLE);
        rv.setVisibility(View.VISIBLE);

        add2.setVisibility(View.INVISIBLE);
        back.setVisibility(View.INVISIBLE);
        platForm.setVisibility(View.INVISIBLE);
        email.setVisibility(View.INVISIBLE);
        pass.setVisibility(View.INVISIBLE);
        head.setVisibility(View.INVISIBLE);

    }

    public void visible2(View view) {
        add1.setVisibility(View.VISIBLE);
        rv.setVisibility(View.VISIBLE);

        add2.setVisibility(View.INVISIBLE);
        back.setVisibility(View.INVISIBLE);
        platForm.setVisibility(View.INVISIBLE);
        platForm.setText("");
        email.setVisibility(View.INVISIBLE);
        email.setText("");
        pass.setVisibility(View.INVISIBLE);
        pass.setText("");
        head.setVisibility(View.INVISIBLE);

    }

    public void deleteall() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Delete All ?");
        builder.setMessage("Are You Sure Want to Delete All Data ?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Database2 db=new Database2(dataActivity.this);
                db.deleteAllData();


                Intent intent =new Intent(dataActivity.this,dataActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();



    }
    public void devloperbtn() {
            Intent intent=new Intent(dataActivity.this,infoActivity.class);
            startActivity(intent);
    }

    public void showpopup(View view) {
        PopupMenu popup=new PopupMenu(this,view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.top_menu2);
        popup.show();

    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.deleteallbtn : deleteall();
                return true;
            case R.id.infobtn : devloperbtn();
                return true;

            default: return false;
        }
    }
}