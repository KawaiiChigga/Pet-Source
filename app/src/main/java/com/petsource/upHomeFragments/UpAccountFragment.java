package com.petsource.upHomeFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.petsource.HomeActivity;
import com.petsource.R;
import com.petsource.model.Login;

import retrofit2.Call;

public class UpAccountFragment extends Fragment {
    private TextView lblName;
    private TextView lblEmail;
    private TextView lblPhone;

    private Call<Login> login;

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
        lblPhone = (TextView) getActivity().findViewById(R.id.lblFUpAccountPhoneNum);

        lblName.setText(HomeActivity.shared.getString("nameKEY", null));
        lblEmail.setText(HomeActivity.shared.getString("emailKEY", null));
        lblPhone.setText(HomeActivity.shared.getString("phoneKEY", null));


    }
}
