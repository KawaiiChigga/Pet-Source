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
import com.google.firebase.auth.FirebaseUser;
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

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

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

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        
        lblName = (TextView) getActivity().findViewById(R.id.lblFUpAccountName);
        lblEmail = (TextView) getActivity().findViewById(R.id.lblFUpAccountEmail);

        lblName.setText(mFirebaseAuth.getCurrentUser().getDisplayName());
        lblEmail.setText(mFirebaseAuth.getCurrentUser().getEmail());

        btnSignout = (Button) getActivity().findViewById(R.id.btnFUpAccountSignOut);
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
