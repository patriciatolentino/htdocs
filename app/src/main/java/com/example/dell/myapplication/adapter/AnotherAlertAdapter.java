package com.example.dell.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.myapplication.R;
import com.example.dell.myapplication.model.SafeExits;

import java.util.List;

public class AnotherAlertAdapter extends RecyclerView.Adapter<AnotherAlertAdapter.ViewHolder>{

    public static List<SafeExits> alertitems;
    private Context context;

    @Override
    public AnotherAlertAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item2, parent, false);

        return new AnotherAlertAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AnotherAlertAdapter.ViewHolder holder, int position) {
    SafeExits listItem = alertitems.get(position);
        holder.txtExitName.setText(listItem.getExitName());
    }

    @Override
    public int getItemCount() {
        return alertitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtExitName;
        public ViewHolder(View itemView) {
            super(itemView);

            txtExitName = itemView.findViewById(R.id.txtExitName);
        }
    }
}
