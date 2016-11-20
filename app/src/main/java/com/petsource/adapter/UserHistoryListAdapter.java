package com.petsource.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.petsource.R;

/**
 * Created by Daniel on 11/7/2016.
 */

public class UserHistoryListAdapter {

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView lblStaffName, lblPetName, lblDate, lblRating;

        public MyViewHolder(View itemView) {
            super(itemView);
            lblStaffName = (TextView) itemView.findViewById(R.id.lblFHistoryName);
            lblPetName = (TextView) itemView.findViewById(R.id.lblFHistoryPetName);
            lblDate = (TextView) itemView.findViewById(R.id.lblFHistoryDate);
            lblRating = (TextView) itemView.findViewById(R.id.lblFHistoryRating);
        }

        public void bind() {

        }
    }
}
