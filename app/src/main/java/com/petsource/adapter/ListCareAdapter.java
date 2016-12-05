package com.petsource.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.petsource.petCare.ListCareActivity;
import com.petsource.petCare.Maps2Activity;
import com.petsource.R;
import com.petsource.model.Info;
import com.petsource.model.Shop;
import com.petsource.network.API;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListCareAdapter extends RecyclerView.Adapter<ListCareAdapter.MyViewHolder>{
    private List<Shop> data;

    public ListCareAdapter(List<Shop> data){
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_care, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Shop p = data.get(position);
        holder.bind(p);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtName, txtDate, txtTime;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.lblListCareName);
            txtDate = (TextView) itemView.findViewById(R.id.lblListCareDate);
            txtTime = (TextView) itemView.findViewById(R.id.lblListCareTime);
            itemView.setOnClickListener(this);
        }

        public void bind(final Shop user) {
            Call<List<Info>> getlist = API.Factory.getInstance().checkAccount(user.getIduser());
            getlist.enqueue(new Callback<List<Info>>() {
                @Override
                public void onResponse(Call<List<Info>> call, Response<List<Info>> response) {
                    txtName.setText(response.body().get(0).getName());
                }

                @Override
                public void onFailure(Call<List<Info>> call, Throwable t) {

                }
            });
//            txtName.setText(user.getIduser());
            txtDate.setText(user.getStartdate() + " - " + user.getEnddate());
            txtTime.setText(user.getStarttime() + " - " + user.getEndtime());
        }

        @Override
        public void onClick(final View v) {
            if(v.getId()==itemView.getId())
            {
                Call<List<Shop>> a = API.Factory.getInstance().getSalonStaff(data.get(Integer.valueOf(getAdapterPosition())).getIduser());
                a.enqueue(new Callback<List<Shop>>() {
                    @Override
                    public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
                        Maps2Activity.staff = data.get(Integer.valueOf(getAdapterPosition()));
                        Call<List<Info>> i = API.Factory.getInstance().checkAccount(response.body().get(0).getIduser());
                        i.enqueue(new Callback<List<Info>>() {
                            @Override
                            public void onResponse(Call<List<Info>> call, Response<List<Info>> response) {
                                Maps2Activity.ChosePet = ListCareActivity.ChosePet;
                                Maps2Activity.infoStaff = response.body().get(0);
                                Maps2Activity.cusDateStart = ListCareActivity.cusDateStart;
                                Maps2Activity.cusDateEnd = ListCareActivity.cusDateEnd;
                                Intent intent = new Intent(v.getContext(), Maps2Activity.class);
                                v.getContext().startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<List<Info>> call, Throwable t) {

                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<List<Shop>> call, Throwable t) {

                    }
                });


            }
        }
    }
}
