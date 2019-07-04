package com.ramesh.mvvm.view.recentmovie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;


import com.ramesh.mvvm.R;
import com.ramesh.mvvm.model.data.remote.ApiStatus;
import com.ramesh.mvvm.model.data.remote.remotemodel.movielist.MovieList;
import com.ramesh.mvvm.util.AnimationUtils;
import com.ramesh.mvvm.util.viewtransformer.CardFragmentPagerAdapter;
import com.ramesh.mvvm.util.viewtransformer.ShadowTransformer;
import com.ramesh.mvvm.view.base.BaseActivity;
import com.ramesh.mvvm.view.moviedetails.MovieDetailsActivity;
import com.ramesh.mvvm.viewmodel.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnPageChange;

import static butterknife.OnPageChange.Callback.PAGE_SELECTED;
import static com.ramesh.mvvm.AppConstant.MOVIE_ID;


public class MovieActivity extends BaseActivity implements MovieView {


    // View initialization
    @BindView(R.id.img_home)
    ImageView mHomeView;

    @BindView(R.id.txt_title)
    TextView txtMovieTitle;

    @BindView(R.id.txt_date)
    TextView txtReleaseDate;

    @BindView(R.id.txt_avg)
    TextView txtAverage;

    @BindView(R.id.txt_avg_bot)
    TextView txtAverageBot;

    @BindView(R.id.txt_popular)
    TextView txtPopularity;

    @BindView(R.id.txt_popular_bot)
    TextView txtPopularityBot;

    @BindView(R.id.txt_adult)
    TextView txtAdult;

    @BindView(R.id.txt_adult_bot)
    TextView txtAdultBot;

    @BindView(R.id.txt_book)
    TextView txtMovieBook;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    // Injection
    @Inject
    ViewModelProviderFactory factory;
    @Inject
    AnimationUtils animationUtils;

    // Norma declaration
    MovieViewModel viewModel;
    private List<MovieList> mMovieLists = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set full screen view
        fullScreen();

        setContentView(R.layout.activity_movie);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));

        // view model initialization
        viewModel();

    }


    @OnClick(R.id.txt_book)
    public void openMovieDetails(View view) {
        mViewPager.setAnimation(animationUtils.getSlideUp());
        Intent intent = new Intent(MovieActivity.this, MovieDetailsActivity.class);
        intent.putExtra(MOVIE_ID, txtMovieBook.getTag().toString());
        startActivity(intent);
    }


    private void viewModel() {

        viewModel = ViewModelProviders.of(this, factory).get(MovieViewModel.class);
        viewModel.getMovieListFromServer();

        viewModel.getMutableLiveData().observe(MovieActivity.this, movieLists -> {
            try {
                mMovieLists = movieLists;

                CardFragmentPagerAdapter pagerAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager(), dpToPixels(2, MovieActivity.this), movieLists);
                ShadowTransformer fragmentCardShadowTransformer = new ShadowTransformer(mViewPager, pagerAdapter);
                fragmentCardShadowTransformer.enableScaling(true);

                mViewPager.setAdapter(pagerAdapter);
                mViewPager.setPageTransformer(false, fragmentCardShadowTransformer);
                mViewPager.setOffscreenPageLimit(3);
                setMovieDetails(0); // for initially set zero

            } catch (NullPointerException ex) {
                ex.getMessage();
            }
        });
        viewModel.getIsProcess().observe(this, apiResponse -> {
            if (apiResponse.status == ApiStatus.MESSAGE)
                showMessage(apiResponse.message);
            else if (apiResponse.status == ApiStatus.LOADING)
                showLoading();
            else if (apiResponse.status == ApiStatus.SUCCESS)
                hideLoading();
            else {
                showMessage(apiResponse.error.getMessage());
                hideLoading();
            }
        });

    }


    @OnPageChange(value = R.id.view_pager, callback = PAGE_SELECTED)
    void onPageStateChanged(int position) {
        setMovieDetails(position);

    }


    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }


    @Override
    public void setMovieDetails(int position) {

        MovieList list = mMovieLists.get(position);
        if (list == null)
            return;

        txtMovieTitle.setText(list.getTitle());
        txtReleaseDate.setText(String.format("%s%s", getResources().getString(R.string.release_date), list.getRelease_date()));
        txtAverage.setText(String.valueOf(list.getVoteAverage()));
        txtPopularity.setText(String.valueOf(list.getPopularity()));
        txtMovieBook.setTag(String.valueOf(list.getId()));

        if (list.getAdult())
            txtAdult.setText(getResources().getString(R.string.yes));
        else
            txtAdult.setText(getResources().getString(R.string.no));

        txtMovieTitle.startAnimation(animationUtils.getFadeIn());
        txtReleaseDate.startAnimation(animationUtils.getFadeIn());
        txtAverage.startAnimation(animationUtils.getFadeIn());
        txtPopularity.startAnimation(animationUtils.getFadeIn());
        txtAdult.startAnimation(animationUtils.getFadeIn());

        txtAverageBot.startAnimation(animationUtils.getFadeIn());
        txtPopularityBot.startAnimation(animationUtils.getFadeIn());
        txtAdultBot.startAnimation(animationUtils.getFadeIn());


    }

    // avoid memory leak
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (viewModel != null)
            viewModel = null;
    }
}
