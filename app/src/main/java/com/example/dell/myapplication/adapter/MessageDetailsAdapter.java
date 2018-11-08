package com.example.dell.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.dell.myapplication.R;
import com.example.dell.myapplication.model.MessageDetails;

import java.util.List;

public class MessageDetailsAdapter extends RecyclerView.Adapter<MessageDetailsAdapter.ViewHolder> {

    public static List<MessageDetails> listItems;

    private Context context;
    private int idSelected;


    public MessageDetailsAdapter(List<MessageDetails> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public MessageDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_messages, parent, false);

        return new MessageDetailsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MessageDetailsAdapter.ViewHolder holder, final int position) {
        final MessageDetails listItem = listItems.get(position);

        holder.name.setText(listItem.getName());
        holder.message.setText(listItem.getMessage());
        holder.checkBox.setChecked(listItem.getSelected());
        holder.checkBox.setTag(position);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer pos = (Integer) holder.checkBox.getTag();

                if(listItems.get(pos).getSelected()){
                    listItems.get(pos).setSelected(false);
                    idSelected = listItems.get(pos).getId();
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
    public class ViewHolder extends RecyclerView.ViewHolder {

        public CheckBox checkBox;
        public TextView name;
        public TextView message;

        public ViewHolder(View itemView) {
            super(itemView);

            checkBox = itemView.findViewById(R.id.checkBox);
            name = itemView.findViewById(R.id.txtName);
            message = itemView.findViewById(R.id.txtMsg);
        }


    }
}
