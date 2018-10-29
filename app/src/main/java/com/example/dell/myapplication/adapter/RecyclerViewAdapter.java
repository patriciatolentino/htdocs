package com.example.dell.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.myapplication.R;
import com.example.dell.myapplication.UpdateActivity;
import com.example.dell.myapplication.model.Crud;

import java.util.List;

import butterknife.ButterKnife;

public class RecyclerViewAdapter extends  RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    public static List<Crud> listItems;
    private Context context;

    public RecyclerViewAdapter(List<Crud> listItems, Context context) {
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
        final Crud listItem = listItems.get(position);

       // String imageUri = ("http://10.90.75.207/CRUD/" +listItem.getPath());
      //  Uri imageUri =Uri.parse("192.168.1.2/CRUD/" +listItem.getPath());
       // InputStream is = null;
        //BufferedInputStream bis = null;

        holder.txtID.setText(listItems.get(position).getId());
        holder.txtCalamityName.setText(listItem.getCalamityName());
        holder.txtDescription.setText(listItem.getDescription());
        holder.txtSomething.setText(listItem.getSomething());


    }


    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtID;
        public TextView txtCalamityName;
        public TextView txtDescription;
        public TextView txtSomething;
        public ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);

            txtID = itemView.findViewById(R.id.txtID);
            txtCalamityName = itemView.findViewById(R.id.txtCalamityName);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            txtSomething = itemView.findViewById(R.id.txtSomething);
          //  imageView = itemView.findViewById(R.id.imageView);


             ButterKnife.bind(this, itemView);
             itemView.setOnClickListener(this);
        }

          public void onClick(View view) {

            String id = txtID.getText().toString();
            String calamityName = txtCalamityName.getText().toString();
            String description = txtDescription.getText().toString();
            String something = txtSomething.getText().toString();

            Intent i = new Intent(context, UpdateActivity.class);
            i.putExtra("id", id);
            i.putExtra("calamityName", calamityName);
            i.putExtra("description", description);
            i.putExtra("something", something);
            context.startActivity(i);


        }

    }

}