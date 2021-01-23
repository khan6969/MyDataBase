package com.example.mydatabase;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
    private Context context;
    private ArrayList<Model> arrayList;
    DataBaseHelper dataBaseHelper;

    public Adapter(Context context, ArrayList<Model>arrayList){
        this.context=context;
        this.arrayList = arrayList;
     //   TextView tvName,tvAge,tvQualification;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);

        return new Holder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int position) {
      final Model model = arrayList.get(position);
    //  String name = model.getName();
     //   String Age = model.getAge();
     //   String qualification = model.getQualification();
      holder.tvName.setText(model.getName());
      holder.tvAge.setText(model.getAge());
      holder.tvQualification.setText(model.getQualification());
        dataBaseHelper = new DataBaseHelper(context);
     /* holder.profile.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
                dataBaseHelper.deleteStudent(arrayList.get(position).getName());
              Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
          }
      });*/
      holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBaseHelper.deleteStudent(arrayList.get(position).getName());
                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
            }
        });
      holder.Update.setOnClickListener(new View.OnClickListener() {

          @Override
          public void onClick(View v) {
              Intent intent = new Intent(context,AddRecodActivity.class);
              intent.putExtra("name",arrayList.get(position).getName());
              intent.putExtra("age",arrayList.get(position).getAge());
              intent.putExtra("Qualification",arrayList.get(position).getQualification());
              context.startActivity(intent);
          }
      });
   /*   holder.profile.setOnLongClickListener(new View.OnLongClickListener() {
          @Override
          public boolean onLongClick(View v) {
              RemoveItem(model);
              return true;
          }
      });*/
    }

  /*  private void RemoveItem(Model model) {
     int post = arrayList.indexOf(model);
     arrayList.remove(post);
     notifyItemRemoved(post);
    }*/


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Holder extends RecyclerView.ViewHolder {
      ImageView profile;
        Button Delete,Update;

      TextView tvName,tvAge,tvQualification;

      public Holder(@NonNull View itemView) {
          super(itemView);
          profile=itemView.findViewById(R.id.profile);
          tvName=itemView.findViewById(R.id.tvName);
          tvAge=itemView.findViewById(R.id.tvAge);
          Delete = itemView.findViewById(R.id.Delete);
          Update = itemView.findViewById(R.id.Update);
          tvQualification=itemView.findViewById(R.id.tvQualification);
      }
  }

}
