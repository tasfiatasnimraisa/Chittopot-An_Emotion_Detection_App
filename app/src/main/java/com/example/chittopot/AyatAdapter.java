package com.example.chittopot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AyatAdapter extends RecyclerView.Adapter<AyatAdapter.MyViewHolder> {
    Context context;
    ArrayList<Ayat> list;

    public AyatAdapter(Context context, ArrayList<Ayat> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.itemayat, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Ayat ayat = list.get(position);
        holder.ayatcontent.setText(ayat.getAyatText());
        holder.ayattype.setText(ayat.getAyatCategoryText());

        // Set a click listener for the "Delete" button
        final int itemPosition = position; // Declare a final variable
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ayatId = list.get(itemPosition).getId();
                list.remove(itemPosition);
                notifyDataSetChanged();
                DatabaseReference ayatRef = FirebaseDatabase.getInstance().getReference("ayat").child(ayatId);
                ayatRef.removeValue();
                Toast.makeText(context, "Ayat deleted successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView ayatcontent, ayattype;
        Button deleteButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ayatcontent = itemView.findViewById(R.id.contentayat);
            ayattype = itemView.findViewById(R.id.contentayattype);
            deleteButton = itemView.findViewById(R.id.deleteayatcontent);
        }
    }
}