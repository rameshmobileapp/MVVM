package com.ramesh.mvvm.view.moviedetails;

import androidx.lifecycle.MutableLiveData;


import com.ramesh.mvvm.BuildConfig;
import com.ramesh.mvvm.model.data.DataManager;
import com.ramesh.mvvm.model.data.remote.ApiResponse;
import com.ramesh.mvvm.model.data.remote.remotemodel.cast.CastResponse;
import com.ramesh.mvvm.model.data.remote.remotemodel.moviedetails.MovieDetailsResponse;
import com.ramesh.mvvm.util.NetworkUtils;
import com.ramesh.mvvm.util.rx.SchedulerProvider;
import com.ramesh.mvvm.view.base.BaseViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailsModel extends BaseViewModel {

    private MutableLiveData<ApiResponse> apiResponse;
    private MutableLiveData<MovieDetailsResponse> mMovieDetails;
    private MutableLiveData<CastResponse> mMovieDCast;

    public MovieDetailsModel(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, NetworkUtils networkUtils) {
        super(dataManager, schedulerProvider, compositeDisposable, networkUtils);

        if (mMovieDetails == null)
            mMovieDetails = new MutableLiveData<>();

        if (apiResponse == null)
            apiResponse = new MutableLiveData<>();

        if (mMovieDCast == null)
            mMovieDCast = new MutableLiveData<>();
    }

    void getMovieDetails(String movieID) {
        try {
            apiResponse.setValue(ApiResponse.message(null));
            if (!getNetworkUtils().isNetworkConnected())
                apiResponse.setValue(ApiResponse.message("No internet connection"));
            else {
                apiResponse.setValue(ApiResponse.loading(null));
                getCompositeDisposable().add(
                        getDataManager().getMovie(
                                movieID,
                                BuildConfig.API_KEY)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(movieDetailsResponse -> {
                                    mMovieDetails.setValue(movieDetailsResponse);
                                    apiResponse.setValue(ApiResponse.success(null));
                                }, throwable -> {
                                    apiResponse.setValue(ApiResponse.error(throwable, null));
                                }));

                apiResponse.setValue(ApiResponse.loading(null));
                getCompositeDisposable().add(
                        getDataManager().getCastOfMovie(
                                movieID,
                                BuildConfig.API_KEY)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(movieCast -> {
                                    mMovieDCast.setValue(movieCast);
                                    apiResponse.setValue(ApiResponse.success(null));
                                }, throwable -> {
                                    apiResponse.setValue(ApiResponse.error(throwable, null));
                                }));

            }
        } catch (Exception exception) {
            exception.getMessage();
            apiResponse.setValue(ApiResponse.error(exception, null));
        }
    }

     MutableLiveData<ApiResponse> getIsProcess() {
        return apiResponse;
    }

     MutableLiveData<MovieDetailsResponse> getDetails() {
        return mMovieDetails;
    }

     MutableLiveData<CastResponse> getMovieCast() {
        return mMovieDCast;
    }
}
