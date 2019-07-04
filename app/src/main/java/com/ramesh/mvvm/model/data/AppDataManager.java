package com.ramesh.mvvm.model.data;

import android.accounts.NetworkErrorException;
import android.content.Context;

import com.ramesh.mvvm.di.ApiInfo;
import com.ramesh.mvvm.di.ApplicationContext;
import com.ramesh.mvvm.model.data.remote.ApiHelper;
import com.ramesh.mvvm.model.data.remote.remotemodel.cast.CastResponse;
import com.ramesh.mvvm.model.data.remote.remotemodel.moviedetails.MovieDetailsResponse;
import com.ramesh.mvvm.model.data.remote.remotemodel.movielist.MovieListResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";
    private final ApiHelper mApiHelper;
    private final Context mContext;

    @Inject
    public AppDataManager(@ApplicationContext Context mContext, ApiHelper mApiHelper) {
        this.mContext = mContext;
        this.mApiHelper = mApiHelper;
    }



    @Override
    public Single<MovieListResponse> getMovieListDetails(String apiKey, String greaterDate, String lessDate) throws NetworkErrorException {
        return mApiHelper.getMovieListDetails(apiKey, greaterDate, lessDate);
    }

    @Override
    public Single<MovieDetailsResponse> getMovie(String movieId, String apiKey) {
        return mApiHelper.getMovie(movieId,apiKey);
    }

    @Override
    public Single<CastResponse> getCastOfMovie(String movieId, String apiKey) {
        return mApiHelper.getCastOfMovie(movieId,apiKey);
    }

}
