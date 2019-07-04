package com.ramesh.mvvm.util.viewtransformer;

import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import com.ramesh.mvvm.model.data.remote.remotemodel.movielist.MovieList;

import java.util.ArrayList;
import java.util.List;

public class CardFragmentPagerAdapter extends FragmentStatePagerAdapter implements CardAdapter {

    private List<CardFragment> fragments;
    private float baseElevation;
    private List<MovieList> mMovieLists;

    public CardFragmentPagerAdapter(FragmentManager fm, float baseElevation, List<MovieList> movieLists) {
        super(fm);
        fragments = new ArrayList<>();
        this.baseElevation = baseElevation;
        mMovieLists = movieLists;
       // Recent five movie only
        for (int i = 0; i < 5; i++) {
            addCardFragment(new CardFragment());
        }
    }

    @Override
    public float getBaseElevation() {
        return baseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return fragments.get(position).getCardView();
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return CardFragment.getInstance(position, mMovieLists.get(position).getBackdrop_Path());
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object fragment = super.instantiateItem(container, position);
//        fragments.set(position, (CardFragment) fragment);
        return fragment;
    }

    public void addCardFragment(CardFragment fragment) {
        fragments.add(fragment);
    }

}
