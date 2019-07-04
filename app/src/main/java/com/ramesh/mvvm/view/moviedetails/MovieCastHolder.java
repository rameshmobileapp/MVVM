package com.ramesh.mvvm.view.moviedetails;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.ramesh.mvvm.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieCastHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.img_cast)
    ImageView imgCast;

    @BindView(R.id.txt_cast_name)
    TextView txtCastName;

    public MovieCastHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
