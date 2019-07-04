package com.ramesh.mvvm.model.data.remote;

import android.accounts.NetworkErrorException;


import com.ramesh.mvvm.model.data.remote.remotemodel.cast.CastResponse;
import com.ramesh.mvvm.model.data.remote.remotemodel.moviedetails.MovieDetailsResponse;
import com.ramesh.mvvm.model.data.remote.remotemodel.movielist.MovieListResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiHelper {


    @GET(ApiEndPoint.ENDPOINT_MOVIE_LIST)
    Single<MovieListResponse> getMovieListDetails(
            @Query("api_key") String apiKey,
            @Query("primary_release_date.gte") String greaterDate,
            @Query("primary_release_date.lte") String lessDate) throws NetworkErrorException;

    @GET(ApiEndPoint.ENDPOINT_MOVIE)
    Single<MovieDetailsResponse> getMovie(
            @Path("movie_id") String movieId,
            @Query("api_key") String apiKey
    );

    @GET(ApiEndPoint.ENDPOINT_MOVIE_CAST)
    Single<CastResponse> getCastOfMovie(
            @Path("id") String movieId,
            @Query("api_key") String apiKey
    );


}
