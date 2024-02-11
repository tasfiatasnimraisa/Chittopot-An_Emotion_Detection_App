package com.example.chittopot;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<DataClass> dataList;
    private Context context;

    public MyAdapter(ArrayList<DataClass> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.staggered_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(dataList.get(position).getImageURL()).into(holder.staggeredImages);

        // Set an OnClickListener for each image
        final int itemPosition = position;
        holder.staggeredImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open full-screen activity or dialog
                Intent intent = new Intent(context, FullScreenImageActivity.class);
                intent.putExtra("imageURL", dataList.get(itemPosition).getImageURL());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView staggeredImages;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            staggeredImages = itemView.findViewById(R.id.staggeredImages);
        }
    }
}
