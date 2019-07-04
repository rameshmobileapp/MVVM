package com.ramesh.mvvm.model.data.remote;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

import static com.ramesh.mvvm.model.data.remote.ApiStatus.ERROR;
import static com.ramesh.mvvm.model.data.remote.ApiStatus.LOADING;
import static com.ramesh.mvvm.model.data.remote.ApiStatus.MESSAGE;
import static com.ramesh.mvvm.model.data.remote.ApiStatus.SUCCESS;


public class ApiResponse {
    @NonNull
    public final String message;

    @NonNull
    public final ApiStatus status;

    @Nullable
    public final Throwable error;

    private ApiResponse(ApiStatus status, @Nullable Throwable error, @Nullable String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }


    public static ApiResponse message(String message) {
        return new ApiResponse(MESSAGE, null, message);
    }

    public static ApiResponse loading(@NonNull String message) {
        return new ApiResponse(LOADING, null, null);
    }

    public static ApiResponse success(@NonNull String message) {
        return new ApiResponse(SUCCESS, null, null);
    }

    public static ApiResponse error(@NonNull Throwable error, @NonNull String message) {
        return new ApiResponse(ERROR, error, null);
    }
}
