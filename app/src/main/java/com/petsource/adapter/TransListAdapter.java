package com.petsource.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.petsource.R;
import com.petsource.ViewTransActivity;
import com.petsource.model.Info;
import com.petsource.model.Pet;
import com.petsource.model.Transaction;
import com.petsource.network.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Daniel on 11/22/2016.
 */

public class TransListAdapter extends RecyclerView.Adapter<TransListAdapter.MyViewHolder>{
    List<Transaction> data;

    public TransListAdapter(List<Transaction> data) {
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_customerhistory, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Transaction t = data.get(position);
        holder.bind(t);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView shopname, petname, transdate, status;
        private FirebaseAuth mFirebaseAuth;

        public MyViewHolder(View itemView) {
            super(itemView);
            petname = (TextView) itemView.findViewById(R.id.lblFHistoryPetName);
            shopname = (TextView) itemView.findViewById(R.id.lblFHistoryShopName);
            transdate = (TextView) itemView.findViewById(R.id.lblFHistoryDate);
            status = (TextView) itemView.findViewById(R.id.lblFHistoryStatus);
            itemView.setOnClickListener(this);
        }

        public void bind (Transaction t) {
            Call<Pet> getPetName = API.Factory.getInstance().getPet(t.getIdpet());
            getPetName.enqueue(new Callback<Pet>() {
                @Override
                public void onResponse(Call<Pet> call, Response<Pet> response) {
                    petname.setText(response.body().getName());
                }

                @Override
                public void onFailure(Call<Pet> call, Throwable t) {

                }
            });
            Call<List<Info>> getShopName = API.Factory.getInstance().checkAccount(t.getIdshop());
            getShopName.enqueue(new Callback<List<Info>>() {
                @Override
                public void onResponse(Call<List<Info>> call, Response<List<Info>> response) {
                    shopname.setText(response.body().get(0).getName());
                }

                @Override
                public void onFailure(Call<List<Info>> call, Throwable t) {

                }
            });

            transdate.setText(t.getDate());
            status.setText(t.getStatus());
        }

        @Override
        public void onClick(final View v) {
            if(v.getId()==itemView.getId()) {

                mFirebaseAuth = FirebaseAuth.getInstance();

                Call<List<Transaction>> t = API.Factory.getInstance().getTransUser(mFirebaseAuth.getCurrentUser().getUid());

                t.enqueue(new Callback<List<Transaction>>() {
                    @Override
                    public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                        ViewTransActivity.tr = response.body().get(Integer.valueOf(getAdapterPosition()));
                        Intent intent = new Intent(v.getContext(), ViewTransActivity.class);
                        v.getContext().startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<List<Transaction>> call, Throwable t) {

                    }
                });
            }
        }
    }
}
