package com.petsource.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.petsource.R;
import com.petsource.model.Pet;
import com.petsource.model.Rescue;
import com.petsource.network.API;
import com.petsource.petRescue.RescueInfoActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RescueListAdapter extends RecyclerView.Adapter<RescueListAdapter.MyViewHolder>{
    private List<Pet> data;

    public RescueListAdapter(List<Pet> data){
        this.data = data;
    }

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
        TextView txtName, txtRace, txtType;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.lblListRescueName);
            txtRace = (TextView) itemView.findViewById(R.id.lblListRescueRace);
            txtType = (TextView) itemView.findViewById(R.id.lblListRescueBirth);
            itemView.setOnClickListener(this);
        }

        public void bind(Pet pet) {
            txtName.setText(pet.getName());
            txtRace.setText(pet.getRace());
            if(pet.isDog()==1){
                txtType.setText("Dog");
            }
            else{
                txtType.setText("Cat");
            }
        }

        @Override
        public void onClick(final View v) {
            if(v.getId()==itemView.getId())
            {
                Call<List<Rescue>> a = API.Factory.getInstance().getRescue();
                a.enqueue(new Callback<List<Rescue>>() {
                    @Override
                    public void onResponse(Call<List<Rescue>> call, Response<List<Rescue>> response) {
                        Log.d("TESTING", Integer.valueOf(getAdapterPosition()) + "");
                        RescueInfoActivity.rescue = response.body().get(Integer.valueOf(getAdapterPosition()));

                        Intent intent = new Intent(v.getContext(), RescueInfoActivity.class);
                        v.getContext().startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<List<Rescue>> call, Throwable t) {

                    }
                });
            }
        }
    }
}
