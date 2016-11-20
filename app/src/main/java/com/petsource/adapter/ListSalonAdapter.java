package com.petsource.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.petsource.R;
import com.petsource.model.Info;
import com.petsource.model.Shop;

import java.util.List;

/**
 * Created by Daniel on 11/6/2016.
 */

public class ListSalonAdapter extends RecyclerView.Adapter<ListSalonAdapter.MyViewHolder>{
    private List<Shop> data;

    public ListSalonAdapter(List<Shop> data){
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_salon, parent, false);

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

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtJob, txtAlamat;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.lblListSalonName);
            txtJob = (TextView) itemView.findViewById(R.id.lblListSalonJob);
            txtAlamat = (TextView) itemView.findViewById(R.id.lblListSalonAlamat);
        }

        public void bind(Shop user) {
            txtName.setText(user.getIduser());
        }
    }
}
