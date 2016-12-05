package com.petsource.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.petsource.R;
import com.petsource.ViewTransActivity;
import com.petsource.ViewTransStaffActivity;
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

public class TransStaffListAdapter extends RecyclerView.Adapter<TransStaffListAdapter.MyViewHolder>{
    List<Transaction> data;

    public TransStaffListAdapter(List<Transaction> data) {
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_shophistory, parent, false);
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

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView customername, petname, transdate, status;
        private FirebaseAuth mFirebaseAuth;

        public MyViewHolder(View itemView) {
            super(itemView);
            petname = (TextView) itemView.findViewById(R.id.lblFHistoryPetName);
            customername = (TextView) itemView.findViewById(R.id.lblFHistoryCustomerName);
            transdate = (TextView) itemView.findViewById(R.id.lblFHistoryDate);
            status = (TextView) itemView.findViewById(R.id.lblFHistoryStatus);
            itemView.setOnClickListener(this);
        }

        public void bind (final Transaction t) {
            Call<Pet> getPetName = API.Factory.getInstance().getPet(t.getIdpet());
            getPetName.enqueue(new Callback<Pet>() {
                @Override
                public void onResponse(Call<Pet> call, Response<Pet> response) {
                    String type;
                    if (t.getType() == 0) {
                        type = "Salon";
                    } else {
                        type = "Care";
                    }
                    petname.setText(response.body().getName() + " (" + type + ")");
                }

                @Override
                public void onFailure(Call<Pet> call, Throwable t) {

                }
            });
            Call<List<Info>> getCustomerName = API.Factory.getInstance().checkAccount(t.getIduser());
            getCustomerName.enqueue(new Callback<List<Info>>() {
                @Override
                public void onResponse(Call<List<Info>> call, Response<List<Info>> response) {
                    customername.setText(response.body().get(0).getName());
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

                Call<List<Transaction>> t = API.Factory.getInstance().getTransShop(mFirebaseAuth.getCurrentUser().getUid());

                t.enqueue(new Callback<List<Transaction>>() {
                    @Override
                    public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                        ViewTransStaffActivity.tr = response.body().get(Integer.valueOf(getAdapterPosition()));
                        Intent intent = new Intent(v.getContext(), ViewTransStaffActivity.class);
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
