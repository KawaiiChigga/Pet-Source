package com.petsource.upHomeFragments;

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

import com.google.firebase.auth.FirebaseAuth;
import com.petsource.HomeActivity;
import com.petsource.LoginActivity;
import com.petsource.R;
import com.petsource.SplashActivity;
import com.petsource.model.Login;
import com.squareup.picasso.Picasso;

import retrofit2.Call;

public class UpAccountFragment extends Fragment {
    private TextView lblName;
    private TextView lblEmail;
    private ImageView imgProfile;
    private Button btnSignout;

    public UpAccountFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_upaccount, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lblName = (TextView) getActivity().findViewById(R.id.lblFUpAccountName);
        lblEmail = (TextView) getActivity().findViewById(R.id.lblFUpAccountEmail);

        lblName.setText(HomeActivity.shared.getString("nameKEY", null));
        lblEmail.setText(HomeActivity.shared.getString("emailKEY", null));

        btnSignout = (Button) getActivity().findViewById(R.id.btnFAccountSignOut);
        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SplashActivity.mFirebaseAuth.signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                HomeActivity.homeActivity.finish();
            }
        });

        imgProfile = (ImageView) getActivity().findViewById(R.id.profilepicture);
        Picasso.with(imgProfile.getContext()).load(SplashActivity.mFirebaseAuth.getCurrentUser().getPhotoUrl()).into(imgProfile);
    }
}
