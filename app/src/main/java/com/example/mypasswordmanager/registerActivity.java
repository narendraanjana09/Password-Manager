package com.example.mypasswordmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.sql.*;


    public class registerActivity extends AppCompatActivity {

        Button  registerbtn;
        EditText name, email, pass, pass2,num;
        String name1="";


        public void back(View view) {
            Intent intent=new Intent(registerActivity.this,MainActivity.class);
            startActivity(intent);
            this.finish();
        }
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);

            registerbtn = findViewById(R.id.registerbtn);
            name = findViewById(R.id.nametxt);
            email = findViewById(R.id.emailtxt);
            num = findViewById(R.id.number);
            pass = findViewById(R.id.passtxt);
            pass2 = findViewById(R.id.passtxt2);





        }
        public Boolean passsahihai(){
            String p1=pass.getText().toString(),p2=pass2.getText().toString();
            if(p1.equals(p2)){
                return true;
            }else{
                return false;
            }
        }

        public Boolean allFine(){
            String nm=name.getText().toString(),em=email.getText().toString(),number=num.getText().toString();
            String p1=pass.getText().toString(),p2=pass2.getText().toString();
            Boolean sahihai=true;
            if(nm.isEmpty()&&em.isEmpty()&&p1.isEmpty()&&p2.isEmpty()){
                sahihai=false;
                String ans="Enter Details";
                Toast.makeText(this, ans, Toast.LENGTH_SHORT).show();
            }else {

                if (nm.length() == 0) {
                    sahihai = false;
                    String ans = "Check Name";
                    Toast.makeText(this, ans, Toast.LENGTH_SHORT).show();

                }

                if (em.length() == 0) {
                    sahihai = false;
                    String ema = "Check Email";
                    Toast.makeText(this, ema, Toast.LENGTH_SHORT).show();
                }
                if (number.length() == 0) {
                    sahihai = false;
                    String ema = "Check Number";
                    Toast.makeText(this, ema, Toast.LENGTH_SHORT).show();
                }


                if (p1.length() == 0 || p2.length() == 0||!passsahihai() ) {
                    sahihai = false;
                    String code = "Check Code";
                    Toast.makeText(this, code, Toast.LENGTH_SHORT).show();
                }
            }
            return  sahihai;
        }


        public void addRecord(View view) {

            if(allFine()) {
                Database db = new Database(this);
                Boolean res = db.addRecord(name.getText().toString(), email.getText().toString(),num.getText().toString(), pass2.getText().toString());


                if(res){
                Intent intent = new Intent(registerActivity.this, loginActivity.class);

                startActivity(intent);

                finish();}
                else{

                    String ans="Something`s Wrong";
                    Toast.makeText(this, ans, Toast.LENGTH_LONG).show();

                }
            }
        }


    }