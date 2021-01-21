package com.example.mypasswordmanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class updateDataActivity extends AppCompatActivity {

    EditText platform_name,email_id,pwd;
    Button update;

    String id,pfname,email,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        platform_name=findViewById(R.id.pfnametxt);
        email_id=findViewById(R.id.emailtxt);
        pwd=findViewById(R.id.passtxt);
        getSetIntentdata();
        update=findViewById(R.id.updatebtn);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database2 db2=new Database2(updateDataActivity.this);
                pfname=platform_name.getText().toString();
                email=email_id.getText().toString();
                password=pwd.getText().toString();
                Boolean ans=db2.updateData(id,pfname,email,password);

                if(ans){
                    Toast.makeText(updateDataActivity.this,"Success",Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(updateDataActivity.this,dataActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(updateDataActivity.this,"Failed",Toast.LENGTH_SHORT).show();

                }

            }
        });




    }
void getSetIntentdata(){
        if(getIntent().hasExtra("id")&&getIntent().hasExtra("pfname")&&getIntent().hasExtra("email")&&getIntent().hasExtra("password")){

            id=getIntent().getStringExtra("id");
            pfname=getIntent().getStringExtra("pfname");
            email=getIntent().getStringExtra("email");
            password=getIntent().getStringExtra("password");

            platform_name.setText(pfname);
            email_id.setText(email);
            pwd.setText(password);


        }else{
            Toast.makeText(this, "NoData", Toast.LENGTH_SHORT).show();
        }
}


    public void deleteRow(View view) {
        confirmDelete();
    }
    void confirmDelete(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Delete "+pfname+" ?");
        builder.setMessage("Are You Sure Want to Delete "+pfname+" ?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Database2 db=new Database2(updateDataActivity.this);
                db.deleteData(id);

                Intent intent =new Intent(updateDataActivity.this,dataActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();


    }
}