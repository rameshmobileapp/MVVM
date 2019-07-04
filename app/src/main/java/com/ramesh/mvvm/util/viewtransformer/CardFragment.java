package com.ramesh.mvvm.util.viewtransformer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.ramesh.mvvm.R;


public class CardFragment extends Fragment {
    private static final String TAG = "CardFragment";

    private CardView cardView;

    public static Fragment getInstance(int position, String imageUrl) {
        CardFragment f = new CardFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putString("image_url", imageUrl);
        f.setArguments(args);
        return f;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_viewpager, container, false);

        cardView = view.findViewById(R.id.cardView);
        cardView.setMaxCardElevation(cardView.getCardElevation() * CardAdapter.MAX_ELEVATION_FACTOR);

        ImageView mMoviePoster = view.findViewById(R.id.img_movie_poster);

        assert getArguments() != null;
        Glide.with(getActivity())
                .load("http://image.tmdb.org/t/p/original" + getArguments().getString("image_url")) // Remote URL of image.
                .into(mMoviePoster); //ImageView to set.

        return view;
    }

    public CardView getCardView() {
        return cardView;
    }
}
