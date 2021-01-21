package com.example.mypasswordmanager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

   private Context context;
   private   ArrayList id,platform_Name,email_id,pwd;

   Animation translate_anim;


    CustomAdapter(Context context,ArrayList id,ArrayList platform_Name,ArrayList email_id,ArrayList pwd){
        this.context=context;
        this.id=id;
        this.platform_Name=platform_Name;
        this.email_id=email_id;
        this.pwd=pwd;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.recycler_row,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.idtxt.setText(String.valueOf(id.get(position)));
        holder.pfnametxt.setText(String.valueOf(platform_Name.get(position)));
        holder.emailtxt.setText(String.valueOf(email_id.get(position)));
        holder.pwdtxt.setText(String.valueOf(pwd.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,updateDataActivity.class);
                intent.putExtra("id",String.valueOf(id.get(position)));
                intent.putExtra("pfname",String.valueOf(platform_Name.get(position)));
                intent.putExtra("email",String.valueOf(email_id.get(position)));
                intent.putExtra("password",String.valueOf(pwd.get(position)));

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView idtxt,pfnametxt,emailtxt,pwdtxt;
        LinearLayout mainLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            idtxt=itemView.findViewById(R.id.idtxt);
            pfnametxt=itemView.findViewById(R.id.pfnametxt);
            emailtxt=itemView.findViewById(R.id.emailtxt);
            pwdtxt=itemView.findViewById(R.id.pwdtxt);

            mainLayout=itemView.findViewById(R.id.mainLayout);

            translate_anim= AnimationUtils.loadAnimation(context,R.anim.bottomtotop);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
