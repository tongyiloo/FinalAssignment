package com.example.finalassignment;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

    private Context context;
    private ArrayList<Model> arrayList;

    public Adapter(Context context, ArrayList<Model> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @NotNull
    @Override
    public Holder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Adapter.Holder holder, int position) {

        Model model = arrayList.get(position);
        //get for view
        String lid = model.getLid();
        String image = model.getImage();
        String title = model.getTitle();
        String description = model.getDescription();

        //set views
        holder.LibIV.setImageURI(Uri.parse(image));
        holder.title.setText(title);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        ImageView LibIV;
        TextView title;

        public Holder(@NonNull @NotNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.libTitle);
            LibIV = itemView.findViewById(R.id.imageLibIv);
        }
    }


}
