package com.petsource.adapter;

import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.petsource.HomeActivity;
import com.petsource.R;
import com.petsource.interfaces.RecyclerViewClickListener;
import com.petsource.model.Pet;
import com.petsource.petRescue.PetRescueActivity;

import java.util.List;

/**
 * Created by Daniel on 11/6/2016.
 */

public class RescueListAdapter extends RecyclerView.Adapter<RescueListAdapter.MyViewHolder>{

    private List<Pet> data;
    private static RecyclerViewClickListener itemListener;
    public RescueListAdapter(List<Pet> data){
        this.data = data;
    }
    public RescueListAdapter(List<Pet> data, RecyclerViewClickListener itemListener){
        this.data = data;
        this.itemListener = itemListener;
    }

    public void swap(List<Pet> list) {
        if (data != null) {
            data.clear();
            data.addAll(list);
        } else {
            data = list;
        }
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_pet, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Pet p = data.get(position);
        holder.bind(p);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtName, txtRace, txtYear;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.lblListSalonName);
            txtRace = (TextView) itemView.findViewById(R.id.lblListSalonJob);
            txtYear = (TextView) itemView.findViewById(R.id.lblListSalonAlamat);
            itemView.setOnClickListener(this);
        }

        public void bind(Pet user) {
            txtName.setText(user.getName());
            txtRace.setText(user.getRace());
            txtYear.setText(user.getBirthdate());
        }

        @Override
        public void onClick(View v) {
            Log.d("FAK", "ganteng");
            itemListener.recyclerViewListClicked(v, this.getLayoutPosition());
            Log.d("FAK", "" + this.getLayoutPosition());
        }
    }
}
