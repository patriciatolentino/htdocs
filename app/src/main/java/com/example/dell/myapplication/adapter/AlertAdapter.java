package com.example.dell.myapplication.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.myapplication.R;
import com.example.dell.myapplication.model.Crud;
import com.example.dell.myapplication.model.Result;

import java.util.List;

import retrofit2.Response;

public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.ViewHolder> {

    private static final String TAG = "AlertAdapter";
    public static List<Result> alertitems;
    private Context context;
    private int drawable;

    public AlertAdapter(List<Result> alertitems, Context context) {
        this.alertitems = alertitems;
        this.context = context;
    }

    @Override
    public AlertAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new AlertAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final AlertAdapter.ViewHolder holder, final int position) {
        final Result listItem = alertitems.get(position);

        holder.txtCalamityName.setText(listItem.getCalamityName());
        holder.txtDescription.setText(listItem.getDescription());
        Log.d(TAG, "see img:" + listItem.getImage());
        Glide.with(context).load(listItem.getImage()).into(holder.imgCalamity1);

/*
        if (listItem.getId().equals("1")) {
            holder.imageView.setBackgroundResource(R.drawable.earthquake);
            drawable = R.drawable.earthquake;
            System.out.println("CALAMITY  " + listItem.getCalamityName());
        }
        else if (listItem.getId().equals("2")){
            holder.imageView.setBackgroundResource(R.drawable.fire_img);
            drawable = R.drawable.fire_img;
            System.out.println("CALAMITY  " + listItem.getCalamityName());
        }
        else if (listItem.getId().equals("3")){
            holder.imageView.setBackgroundResource(R.drawable.flood_img);
            drawable = R.drawable.flood_img;
            System.out.println("CALAMITY  " + listItem.getCalamityName());
        }
        else if (listItem.getId().equals("4")){
            holder.imageView.setBackgroundResource(R.drawable.bomb_threat);
            drawable = R.drawable.bomb_threat;
            System.out.println("CALAMITY  " + listItem.getCalamityName());
        }
        else if (listItem.getId().equals("5")){
            holder.imageView.setBackgroundResource(R.drawable.active_shooter);
            drawable = R.drawable.active_shooter;
            System.out.println("CALAMITY  " + listItem.getCalamityName());
        }
        holder.imageView.setBackgroundResource(drawable);
    }
    */

    }
    @Override
    public int getItemCount() {
        return alertitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtCalamityName;
        public TextView txtDescription;
        public ImageView imgCalamity1;



        public ViewHolder(View itemView) {
            super(itemView);

            txtCalamityName = itemView.findViewById(R.id.txtCalamityName);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            imgCalamity1 = itemView.findViewById(R.id.imgCalamity1);


        }
}
}
