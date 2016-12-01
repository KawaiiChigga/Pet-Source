package com.petsource.homeFragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.petsource.HomeActivity;
import com.petsource.LoginActivity;
import com.petsource.R;
import com.petsource.SplashActivity;
import com.petsource.UpdateAccActivity;
import com.petsource.model.Info;
import com.petsource.network.API;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountFragment extends Fragment{
    private TextView lblName;
    private TextView lblEmail;
    private TextView lblUpdate;
    private Button btnSignout;
    private ImageView imgProfile;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    public AccountFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        lblName = (TextView) getActivity().findViewById(R.id.lblFAccountName);
        lblEmail = (TextView) getActivity().findViewById(R.id.lblFAccountEmail);

        lblName.setText(mFirebaseAuth.getCurrentUser().getDisplayName());
        lblEmail.setText(mFirebaseAuth.getCurrentUser().getEmail());

        lblUpdate = (TextView) getActivity().findViewById(R.id.lblUpdateAccount);
        lblUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<List<Info>> checkUp = API.Factory.getInstance().checkAccount(mFirebaseAuth.getCurrentUser().getUid());
                checkUp.enqueue(new Callback<List<Info>>() {
                    @Override
                    public void onResponse(Call<List<Info>> call, Response<List<Info>> response) {
                        if (response.body().get(0).getIsStaff() == 1) {
                            Toast.makeText(getContext(), "You're already in process of being a staff. Thank you!", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(getActivity(), UpdateAccActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Info>> call, Throwable t) {

                    }
                });

            }
        });

        btnSignout = (Button) getActivity().findViewById(R.id.btnFAccountSignOut);
        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseAuth.signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                HomeActivity.homeActivity.finish();
            }
        });

        imgProfile = (ImageView) getActivity().findViewById(R.id.profilepicture);
        Picasso.with(imgProfile.getContext()).load(mFirebaseAuth.getCurrentUser().getPhotoUrl()).into(imgProfile);

    }
}
