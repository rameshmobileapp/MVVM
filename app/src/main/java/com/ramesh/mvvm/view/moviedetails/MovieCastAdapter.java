package com.ramesh.mvvm.view.moviedetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ramesh.mvvm.R;
import com.ramesh.mvvm.model.data.remote.remotemodel.cast.Cast;

import java.util.List;

public class MovieCastAdapter extends RecyclerView.Adapter<MovieCastHolder> {

    private Context mContext;
    private List<Cast> mCastList;

    public MovieCastAdapter(Context context, List<Cast> castList) {
        mContext = context;
        mCastList = castList;
    }

    @NonNull
    @Override
    public MovieCastHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_cast, parent, false);
        return new MovieCastHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieCastHolder holder, int position) {
        try {
            Cast cast = mCastList.get(position);
            holder.txtCastName.setText(String.valueOf(cast.getName()));
            Glide.with(mContext)
                    .load("http://image.tmdb.org/t/p/original" + cast.getProfilePath()) // Remote URL of image.
                    .apply(RequestOptions.circleCropTransform())
                    .into(holder.imgCast);
        } catch (NullPointerException ex) {
            ex.getMessage();
        }
    }

    // show only five
    @Override
    public int getItemCount() {
        return 5;
    }
}
