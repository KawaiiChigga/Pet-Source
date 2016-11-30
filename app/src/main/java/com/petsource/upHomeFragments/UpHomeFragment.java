package com.petsource.upHomeFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.petsource.R;
import com.petsource.petCare.ChosePetCareActivity;
import com.petsource.petCare.PetCareActivity;
import com.petsource.petRescue.PetRescueActivity;
import com.petsource.petSalon.ChosePetSalonActivity;
import com.petsource.petSalon.PetSalonActivity;

public class UpHomeFragment extends Fragment{

    Button btnSalon, btnCare, btnRescue;

    public UpHomeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_uphome, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnSalon = (Button) getActivity().findViewById(R.id.btnFUpHomePetSalon);
        btnSalon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChosePetSalonActivity.class);
                startActivity(intent);
            }
        });


        btnCare = (Button) getActivity().findViewById(R.id.btnFUpHomePetCare);
        btnCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChosePetCareActivity.class);
                startActivity(intent);
            }
        });

        btnRescue = (Button) getActivity().findViewById(R.id.btnFUpHomePetRescue);
        btnRescue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PetRescueActivity.class);
                startActivity(intent);
            }
        });

    }


}
