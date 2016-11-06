package com.example.user.petsource.homeFragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.petsource.HomeActivity;
import com.example.user.petsource.R;
import com.example.user.petsource.model.Login;
import com.example.user.petsource.network.API;
import com.example.user.petsource.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountFragment extends Fragment{
    private TextView lblName;
    private TextView lblEmail;
    private TextView lblPhone;

    private Call<Login> login;

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

        lblName = (TextView) getActivity().findViewById(R.id.lblFAccountName);
        lblEmail = (TextView) getActivity().findViewById(R.id.lblFAccountEmail);
        lblPhone = (TextView) getActivity().findViewById(R.id.lblFAccountPhoneNum);

        lblName.setText(HomeActivity.shared.getString("nameKEY", null));
        lblEmail.setText(HomeActivity.shared.getString("emailKEY", null));
        lblPhone.setText(HomeActivity.shared.getString("phoneKEY", null));

    }
}
