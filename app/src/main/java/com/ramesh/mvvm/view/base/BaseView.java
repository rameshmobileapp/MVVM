package com.ramesh.mvvm.view.base;

import androidx.annotation.StringRes;


public interface BaseView {

    void showLoading();

    void hideLoading();

    void showKeyBoard();

    void onError(@StringRes int resId);

    void onError(String message);

    void showMessage(String message);

    void showMessage(@StringRes int resId);

    void fullScreen();

    void hideKeyboard();
//
//    void showKeyboard();
//
//    void handleApiError(ANError error);
//
//    void handleError(Throwable throwable);
}
