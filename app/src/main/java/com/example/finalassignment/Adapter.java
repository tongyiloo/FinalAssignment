package com.example.finalassignment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

    private Context context;
    private ArrayList<Model> arrayList;
    //dataBase object
    DatabaseHelperLibrary databaseHelperLibrary;

    public Adapter(Context context, ArrayList<Model> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        databaseHelperLibrary = new DatabaseHelperLibrary(context);
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
        final String lid = model.getLid();
        final String image = model.getImage();
        final String title = model.getTitle();
        final String description = model.getDescription();
        final String addTimeStamp = model.getAddTimeStamp();
        final String updateTimeStamp = model.getUpdateTimeStamp();

        //set views
        holder.LibIV.setImageURI(Uri.parse(image));
        holder.title.setText(title);
        holder.description.setText(description);

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editDialog(
                        ""+position,
                        ""+lid,
                        ""+image,
                        ""+title,
                        ""+description,
                        ""+addTimeStamp,
                        ""+updateTimeStamp
                );
            }
        });

        //when long press on item, show an alert dialog delete an item
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                deleteDialog(
                        ""+lid
                );
                return false;
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog(
                        ""+lid
                );
            }
        });

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent  = new Intent(context, LibRecordActivity.class);
//                intent.putExtra("ID", lid);
//                intent.putExtra("TITLE", title);
//                intent.putExtra("DESCRIPTION", description);
//                intent.putExtra("IMAGE", image);
//                intent.putExtra("ADD_TIMESTAMP", addTimeStamp);
//                intent.putExtra("UPDATE_TIMESTAMP", updateTimeStamp);
//                context.startActivity(intent);
//            }
//        });

    }

    private void deleteDialog(final String lid) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete");
        builder.setMessage("Are you sure you want to delete?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_baseline_delete_24);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseHelperLibrary.deleteInfo(lid);
                ((MyLibraryActivity)context).onResume();
                Toast.makeText(context,"Delete Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();

    }


    private void editDialog(String position, String lid, String image, String title, String description, String addTimeStamp, String updateTimeStamp) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Update");
        builder.setMessage("Are you sure you want to update?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_baseline_edit_24);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(context, EditLibraryActivity.class);
                intent.putExtra("ID", lid);
                intent.putExtra("TITLE", title);
                intent.putExtra("DESCRIPTION", description);
                intent.putExtra("IMAGE", image);
                intent.putExtra("ADD_TIMESTAMP", addTimeStamp);
                intent.putExtra("UPDATE_TIMESTAMP", updateTimeStamp);
                intent.putExtra("editMode", true);
                context.startActivity(intent);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        ImageView LibIV;
        TextView title, description;
        ImageButton editButton, deleteButton;
        CardView cardView;

        public Holder(@NonNull @NotNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.libTitle);
            LibIV = itemView.findViewById(R.id.imageLibIv);
            editButton =itemView.findViewById(R.id.editBtn);
            deleteButton = itemView.findViewById(R.id.deleteBtn);
            description = itemView.findViewById(R.id.libDes);
            cardView = itemView.findViewById(R.id.CView);
        }
    }


}
