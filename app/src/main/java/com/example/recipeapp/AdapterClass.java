package com.example.recipeapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder> {
    public Context context;

    ArrayList<Chicken> list;
    public AdapterClass(ArrayList<Chicken>list ){
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_holder,viewGroup,false);
        context = viewGroup.getContext();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {

        final String ckname = list.get(i).getCname();
        final String ckinget=list.get(i).getCingredient();
        myViewHolder.setCurgoals(ckname);
        myViewHolder.setWgoals(ckinget);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private View mview;
        private CardView mParent;

        TextView curgoals,wgoals;

        public MyViewHolder(@NonNull View itemView){
            super (itemView);
            mview =itemView;
            mParent= itemView.findViewById(R.id.chicken_parent);

        }

        public void setCurgoals (String name){
            curgoals = mview.findViewById(R.id.tv_cname);
            curgoals.setText(name);
        }
        public void setWgoals (String goals){
            wgoals = mview.findViewById(R.id.tv_cingredient);
            wgoals.setText(goals);
        }
    }


}
