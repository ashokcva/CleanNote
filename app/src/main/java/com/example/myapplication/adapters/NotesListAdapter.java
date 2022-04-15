package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.NotesClickListener;
import com.example.myapplication.R;
import com.example.myapplication.model.Notes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotesListAdapter extends RecyclerView.Adapter<NotesViewHolder>{

    Context context;
    List<Notes> list;
    NotesClickListener listener;

    public NotesListAdapter(Context context, List<Notes> list, NotesClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.textview_title.setText(list.get(position).getTitle());
        holder.textview_title.setSelected(true);

        holder.notes.setText(list.get(position).getNote());

        holder.textview_date.setText(list.get(position).getDate());
        holder.textview_date.setSelected(true);

        if(list.get(position).isPinned()){
            holder.imageView_pin.setImageResource(R.drawable.ic_add_24);
        }else{
            holder.imageView_pin.setImageResource(0);
        }

        int color_code = getRandomColor();
        holder.notes_container.setCardBackgroundColor(holder.itemView.getResources().getColor(color_code,null));

        holder.notes_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(list.get(holder.getAdapterPosition()));
            }
        });

        holder.notes_container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onLongClick(list.get(holder.getAdapterPosition()),holder.notes_container);
                return true;
            }
        });
    }

    private int getRandomColor(){
        List<Integer> colorCodes = new ArrayList<>();

        colorCodes.add(R.color.color1);
        colorCodes.add(R.color.color2);
        colorCodes.add(R.color.color3);
        colorCodes.add(R.color.color4);
        colorCodes.add(R.color.color5);

        Random random = new Random();
        int random_colors = random.nextInt(colorCodes.size());

        return colorCodes.get(random_colors);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filterList(List<Notes> filteredNotes){
        list = filteredNotes;
        notifyDataSetChanged();
    }
}

class NotesViewHolder extends RecyclerView.ViewHolder{

    CardView notes_container;
    TextView textview_title, notes, textview_date;
    ImageView imageView_pin;

    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);

        notes_container = itemView.findViewById(R.id.notes_container);
        textview_title = itemView.findViewById(R.id.textview_title);
        notes = itemView.findViewById(R.id.notes);
        textview_date = itemView.findViewById(R.id.textview_date);
        imageView_pin = itemView.findViewById(R.id.imageView_pin);

    }
}