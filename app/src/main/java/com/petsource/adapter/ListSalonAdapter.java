package com.petsource.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.petsource.HomeActivity;
import com.petsource.MapsActivity;
import com.petsource.R;
import com.petsource.SplashActivity;
import com.petsource.UpdateHomeActivity;
import com.petsource.model.Info;
import com.petsource.model.Shop;
import com.petsource.network.API;
import com.petsource.petSalon.ListSalonActivity;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static com.petsource.petSalon.PetSalonActivity.REQ_MAPS;


public class ListSalonAdapter extends RecyclerView.Adapter<ListSalonAdapter.MyViewHolder>{
    private List<Info> data;
    private List<Shop> data2;

    public ListSalonAdapter(List<Info> data){
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_salon, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Info p = data.get(position);
        holder.bind(p);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtName, txtJob, txtAlamat, btnPickMe;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.lblListSalonName);
            txtJob = (TextView) itemView.findViewById(R.id.lblListSalonJob);
            txtAlamat = (TextView) itemView.findViewById(R.id.lblListSalonAlamat);
            itemView.setOnClickListener(this);
        }

        public void bind(final Info user) {
                txtName.setText(user.getName());
                txtJob.setText(user.getJob());
                txtAlamat.setText(user.getAddress());
        }

        @Override
        public void onClick(View v) {
            if(v.getId()==itemView.getId())
            {
                MapsActivity.idStaff = data.get(itemView.getId()+1).getUserid();
                MapsActivity.nameStaff = data.get(itemView.getId()+1).getName();

                Call<List<Shop>> a = API.Factory.getInstance().getSalon(data.get(itemView.getId()+1).getUserid());
                a.enqueue(new Callback<List<Shop>>() {
                    @Override
                    public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
                        MapsActivity.latitudeStaff = response.body().get(0).getLatitude();
                        MapsActivity.longtitudeStaff = response.body().get(0).getLongitude();
                    }

                    @Override
                    public void onFailure(Call<List<Shop>> call, Throwable t) {

                    }
                });


                Intent intent = new Intent(v.getContext(), MapsActivity.class);
                v.getContext().startActivity(intent);
            }
        }
    }
}
