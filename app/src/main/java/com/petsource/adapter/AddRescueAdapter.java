package com.petsource.adapter;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.petsource.MapsActivity;
import com.petsource.R;
import com.petsource.SplashActivity;
import com.petsource.model.Pet;
import com.petsource.model.Rescue;
import com.petsource.model.Shop;
import com.petsource.network.API;
import com.petsource.petRescue.PetRescueActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Daniel on 11/6/2016.
 */

public class AddRescueAdapter extends RecyclerView.Adapter<AddRescueAdapter.MyViewHolder>{
    private List<Pet> data;
    private List<Rescue> data2;

    public AddRescueAdapter(List<Pet> data){
        this.data = data;
    }

//    public void swap(List<Pet> list) {
//        if (data != null) {
//            data.clear();
//            data.addAll(list);
//        } else {
//            data = list;
//        }
//        notifyDataSetChanged();
//    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_rescue, parent, false);

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
        }

        public void bind(Pet user) {
            txtName.setText(user.getName());
            txtRace.setText(user.getRace());
            txtYear.setText(user.getBirthdate());
        }

        @Override
        public void onClick(View v) {
            if(v.getId()==itemView.getId()) {
                PetRescueActivity.rescueName = data.get(Integer.valueOf(getAdapterPosition())).getName();
                PetRescueActivity.rescueRace = data.get(Integer.valueOf(getAdapterPosition())).getRace();
                PetRescueActivity.rescueCertified = data.get(Integer.valueOf(getAdapterPosition())).isCertified();
                PetRescueActivity.rescueYear = data.get(Integer.valueOf(getAdapterPosition())).getBirthdate();
                PetRescueActivity.rescueGender = data.get(Integer.valueOf(getAdapterPosition())).isMale();
                PetRescueActivity.rescueIsDog = data.get(Integer.valueOf(getAdapterPosition())).isDog();
                PetRescueActivity.rescuePetID = data.get(Integer.valueOf(getAdapterPosition())).getId();
                PetRescueActivity.rescueUserID = data.get(Integer.valueOf(getAdapterPosition())).getUserid();

//                PetRescueActivity.rescueDecript = data2.get(Integer.valueOf(getAdapterPosition())).getDescription();

                Call<List<Pet>> a = API.Factory.getInstance().getPets(SplashActivity.mFirebaseAuth.getCurrentUser().getUid());
                a.enqueue(new Callback<List<Pet>>() {
                    @Override
                    public void onResponse(Call<List<Pet>> call, Response<List<Pet>> response) {
//                        PetRescueActivity.rescueDecript = response.body().get(0).getDescription();
//                        PetRescueActivity.rescueLatitude = response.body().get(0).getLatitude();
//                        PetRescueActivity.rescueLongtitude = response.body().get(0).getLongitude();
                    }

                    @Override
                    public void onFailure(Call<List<Pet>> call, Throwable t) {

                    }
                });
            }
        }
    }
}
