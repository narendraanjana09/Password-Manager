package com.example.mypasswordmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
 Database db=null;
 EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent=new Intent(loginActivity.this,MainActivity.class);
        setContentView(R.layout.activity_login);
        pass=findViewById(R.id.passtxt);



    }
    public void login(View view){
       String a= loginpass(3);
        if(a.equals(pass.getText().toString())){


            Intent intent=new Intent(loginActivity.this,dataActivity.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);


            startActivity(intent);
            this.finish();



        }else{
            String ans="Enter Correct pass";
            Toast.makeText(this, ans, Toast.LENGTH_SHORT).show();
        }
    }


    public String loginpass(int i){
         db=new Database(this);
         Cursor res=db.getData();
        //Toast.makeText(this, (CharSequence) res, Toast.LENGTH_LONG).show();
         while ((res.moveToNext())){
             String aa=res.getString(i);

         return aa;
         }

        return null;
    }

    public void resetPass(View view) {
        Intent intent=new Intent(loginActivity.this,resetPassActivity.class);
        startActivity(intent);


    }

    public void devloperbtn() {
        Intent intent=new Intent(loginActivity.this,infoActivity.class);
        startActivity(intent);
    }

    public void showpopup(View view) {
        PopupMenu popup=new PopupMenu(this,view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.top_menu);
        popup.show();

    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.infobtn : devloperbtn();
                return true;

            default: return false;
        }
    }

}