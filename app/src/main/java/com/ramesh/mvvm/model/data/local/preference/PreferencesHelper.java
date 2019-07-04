package com.ramesh.mvvm.model.data.local.preference;

public interface PreferencesHelper {


    void setCurrentUserId(Long userId);

    Long getCurrentUserId();

    void setCurrentUserPassword(String password);

    String getCurrentUserPassword();

    String setUserToken(String tkoen);

    void getUserToken();
}
