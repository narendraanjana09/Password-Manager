package com.example.mypasswordmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {


    TextView t1,t2;
    Button login;

    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        SharedPreferences preferences=getSharedPreferences("PREFERENCE",MODE_PRIVATE);
        String FirstTime=preferences.getString("FirstTimeInstall","");

        if(FirstTime.equals("Yes")&&dataExist()){

            Intent i=new Intent(this,loginActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);



        }else{
            SharedPreferences.Editor editor=preferences.edit();
            editor.putString("FirstTimeInstall","Yes");
            editor.apply();
        }

       




    }
    public Boolean dataExist(){
        Database db=new Database(this);
        Cursor crs=db.getData();
        if(crs.getCount()==0){
           return  false;
        }else{
            return  true;
        }

    }




    public void startdbapp(View view){
        new Database(this);
        startActivity(new Intent(this,registerActivity.class));
        this.finish();

    }

    public void devloperbtn() {
        Intent intent=new Intent(MainActivity.this,infoActivity.class);
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