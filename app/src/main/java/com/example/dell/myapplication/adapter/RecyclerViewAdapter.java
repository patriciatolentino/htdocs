package com.example.dell.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.myapplication.R;
import com.example.dell.myapplication.UpdateActivity;
import com.example.dell.myapplication.model.Crud;
import com.example.dell.myapplication.model.Result;

import java.util.List;

import butterknife.ButterKnife;

public class RecyclerViewAdapter extends  RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "recycler";
    public static List<Result> listItems;
    private Context context;

    private String idSelected;

    public RecyclerViewAdapter(List<Result> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false);
        return new RecyclerViewAdapter.ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapter.ViewHolder holder, final int position) {


        final Result listItem = listItems.get(position);

        holder.txtID.setText(listItems.get(position).getCalamityID());
        holder.txtCalamityName.setText(listItem.getCalamityName());
        holder.txtDescription.setText(listItem.getDescription());

        Glide.with(context).load(listItem.getImage()).into(holder.imgCalamity);
        Log.d(TAG, "select :" + listItem.getImage());

        holder.checkBox.setChecked(listItem.getSelected());
        holder.checkBox.setTag(position);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer pos = (Integer) holder.checkBox.getTag();
                if(listItems.get(pos).getSelected()){
                    listItems.get(pos).setSelected(false);
                    idSelected = listItems.get(pos).getCalamityID();
                }else{
                    listItems.get(pos).setSelected(true);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public CheckBox checkBox;
        public TextView txtID;
        public TextView txtCalamityName;
        public TextView txtDescription;

        public ImageView imgCalamity;

        public ViewHolder(View itemView) {
            super(itemView);

            txtID = itemView.findViewById(R.id.txtID);
            txtCalamityName = itemView.findViewById(R.id.txtCalamityName);
            txtDescription = itemView.findViewById(R.id.txtDescription);

            imgCalamity = itemView.findViewById(R.id.imgCalamity);

            checkBox = itemView.findViewById(R.id.cbSelect);

            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void onClick(View view) {


        }

    }

}