package com.example.user.petsource.homeFragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.user.petsource.R;
import com.example.user.petsource.petCare.PetCareActivity;
import com.example.user.petsource.petRescue.PetRescueActivity;
import com.example.user.petsource.petSalon.PetSalonActivity;

public class HomeFragment extends Fragment{


    ImageView btnSalon, btnCare, btnRescue;

    public HomeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnSalon = (ImageView) getActivity().findViewById(R.id.btnFHomeSalon);
        btnSalon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PetSalonActivity.class);
                startActivity(intent);
            }
        });


        btnCare = (ImageView) getActivity().findViewById(R.id.btnFHomeCare);
        btnCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PetCareActivity.class);
                startActivity(intent);
            }
        });

        btnRescue = (ImageView) getActivity().findViewById(R.id.btnFHomeRescue);
        btnRescue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PetRescueActivity.class);
                startActivity(intent);
            }
        });
    }


}
