package com.example.mypasswordmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class resetPassActivity extends AppCompatActivity {

    EditText num,codetxt1,codetxt2;
    TextView nmtxt;
    Button findbtn,resetbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);
        num=findViewById(R.id.numtxt);
        nmtxt=findViewById(R.id.nametxt);

        findbtn=findViewById(R.id.findbtn);
       resetbtn=findViewById(R.id.resetbtn);
        codetxt1=findViewById(R.id.code1);
        codetxt2=findViewById(R.id.code2);


    }


    public void findnumber(View view) {
       Database db=new Database(this);
        Cursor res=db.getData();
        String b=num.getText().toString();
        while ((res.moveToNext())){
            String aa=res.getString(2);
            if(aa.equals(b)){

                nmtxt.setText(res.getString(0));
                visible("YES");


            }else{
                String reply="Enter Correct Mobile Number";
                Toast.makeText(this,reply,Toast.LENGTH_SHORT).show();
            }
        }




    }

   public  void visible(String a){
        if(a.equals("YES")){
            num.setVisibility(View.INVISIBLE);
            findbtn.setVisibility(View.INVISIBLE);



            nmtxt.setVisibility(View.VISIBLE);
            resetbtn.setVisibility(View.VISIBLE);
            codetxt1.setVisibility(View.VISIBLE);
            codetxt2.setVisibility(View.VISIBLE);

        }

    }
    public Boolean codeCheck(){
        String a=codetxt1.getText().toString(),b=codetxt2.getText().toString();
        if(a.equals(b)){
            return  true;
        }else{
            return  false;
        }
    }

    public void reserpwd(View view) {

        Database db=new Database(this);
        if(codeCheck()){
            Boolean ans=db.updateCode(codetxt1.getText().toString());
            startActivity(new Intent(resetPassActivity.this,loginActivity.class));


        }else{
            String aa="Check Code";
            Toast.makeText(this,aa,Toast.LENGTH_SHORT).show();
        }




    }
}