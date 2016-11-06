package com.example.user.petsource.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.petsource.R;
import com.example.user.petsource.model.Pet;

import java.util.List;

/**
 * Created by Daniel on 11/6/2016.
 */

public class PetListAdapter extends RecyclerView.Adapter<PetListAdapter.MyViewHolder>{
    private List<Pet> data;

    public PetListAdapter(List<Pet> data){
        this.data = data;
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

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtRace, txtYear;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.lblPetListName);
            txtRace = (TextView) itemView.findViewById(R.id.lblPetListRace);
            txtYear = (TextView) itemView.findViewById(R.id.lblPetListYears);
        }

        public void bind(Pet user) {
            txtName.setText(user.getName());
            txtRace.setText(user.getRace());
            txtYear.setText(user.getBirthdate());
        }
    }
}
