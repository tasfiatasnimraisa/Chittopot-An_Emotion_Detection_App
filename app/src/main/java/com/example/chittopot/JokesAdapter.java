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

import java.util.List;

public class JokesAdapter extends RecyclerView.Adapter<JokesAdapter.MyViewHolder> {
    private Context context;
    private List<Jokes> jokesList;

    public JokesAdapter(Context context, List<Jokes> jokesList) {
        this.context = context;
        this.jokesList = jokesList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.itemjokes, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Jokes joke = jokesList.get(position);
        holder.jokeContent.setText(joke.getJokesText());
        holder.jokeType.setText(joke.getJokescatagoryText());

        final int itemPosition = position; // Declare a final variable
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ayatId = jokesList.get(itemPosition).getId();
                jokesList.remove(itemPosition);
                notifyDataSetChanged();
                DatabaseReference ayatRef = FirebaseDatabase.getInstance().getReference("jokes").child(ayatId);
                ayatRef.removeValue();
                Toast.makeText(context, "Quotes deleted successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return jokesList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView jokeContent, jokeType;
        Button deleteButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            jokeContent = itemView.findViewById(R.id.contentjokes);
            jokeType = itemView.findViewById(R.id.contenttypejokes);
            deleteButton = itemView.findViewById(R.id.deletejokescontent);
        }
    }
}
