package com.petsource.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.petsource.R;
import com.petsource.model.Pet;
import com.petsource.network.API;
import com.petsource.petSalon.PetSalonActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by USER on 30/11/2016.
 */

public class ChosePetSalonAdapter extends RecyclerView.Adapter<ChosePetSalonAdapter.MyViewHolder> {

    private List<Pet> data;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    public ChosePetSalonAdapter (List<Pet> data){
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_chosepetsalon, parent, false);
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

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
        public void onClick(final View v) {
            if(v.getId()==itemView.getId())
            {
                mFirebaseAuth = FirebaseAuth.getInstance();
                mFirebaseUser = mFirebaseAuth.getCurrentUser();

                Call<List<Pet>> a = API.Factory.getInstance().getPets(mFirebaseAuth.getCurrentUser().getUid());
                a.enqueue(new Callback<List<Pet>>() {
                    @Override
                    public void onResponse(Call<List<Pet>> call, Response<List<Pet>> response) {
                        PetSalonActivity.ChosePet = response.body().get(Integer.valueOf(getAdapterPosition())).getName();

                        Intent intent = new Intent(v.getContext(), PetSalonActivity.class);
                        v.getContext().startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<List<Pet>> call, Throwable t) {

                    }
                });
            }
        }
    }
}
