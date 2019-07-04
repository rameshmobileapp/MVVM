package com.ramesh.mvvm.model.data.remote;


import android.accounts.NetworkErrorException;


import com.ramesh.mvvm.model.data.remote.remotemodel.cast.CastResponse;
import com.ramesh.mvvm.model.data.remote.remotemodel.moviedetails.MovieDetailsResponse;
import com.ramesh.mvvm.model.data.remote.remotemodel.movielist.MovieListResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class AppApiHelper implements ApiHelper{

    private ApiHelper mApiHelper;

    @Inject
    public AppApiHelper(ApiHelper apiHelper) {
        mApiHelper = apiHelper;
    }


    @Override
    public Single<MovieListResponse> getMovieListDetails(String apiKey, String greaterDate, String lessDate) throws NetworkErrorException {
        return mApiHelper.getMovieListDetails(apiKey,greaterDate,lessDate);
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
