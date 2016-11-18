package com.petsource.homeFragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.petsource.HomeActivity;
import com.petsource.R;
import com.petsource.UpdateAccActivity;

public class AccountFragment extends Fragment{
    private TextView lblName;
    private TextView lblEmail;
    private TextView lblPhone;
    private TextView lblUpdate;

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

        lblUpdate = (TextView) getActivity().findViewById(R.id.lblUpdateAccount);
        lblUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UpdateAccActivity.class);
                startActivity(intent);
            }
        });
    }
}
