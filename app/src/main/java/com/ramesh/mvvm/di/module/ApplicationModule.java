

package com.ramesh.mvvm.di.module;

import android.app.Application;
import android.content.Context;


import com.ramesh.mvvm.AppConstant;
import com.ramesh.mvvm.BuildConfig;
import com.ramesh.mvvm.R;
import com.ramesh.mvvm.di.ApplicationContext;
import com.ramesh.mvvm.di.DatabaseInfo;
import com.ramesh.mvvm.di.PreferenceInfo;
import com.ramesh.mvvm.model.data.AppDataManager;
import com.ramesh.mvvm.model.data.DataManager;
import com.ramesh.mvvm.model.data.local.preference.AppPreferencesHelper;
import com.ramesh.mvvm.model.data.local.preference.PreferencesHelper;
import com.ramesh.mvvm.model.data.remote.ApiHeader;
import com.ramesh.mvvm.model.data.remote.ApiHelper;
import com.ramesh.mvvm.model.data.remote.AppApiHelper;
import com.ramesh.mvvm.util.AnimationUtils;
import com.ramesh.mvvm.util.NetworkUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstant.DB_NAME;
    }

//    @Provides
//    @ApiInfo
//    String provideApiKey() {
//        return BuildConfig.API_KEY;
//    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstant.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }


    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    AppApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }


//    @Provides
//    @Singleton
//    ApiHeader.PublicApiHeader providePublicApiHeader(@ApiInfo String apiKey) {
//        return new ApiHeader.PublicApiHeader(apiKey);
//    }

//    @Provides
//    @Singleton
//    DataBaseConfig provideRoomDatabase(@ApplicationContext Context context) {
//        return Room.databaseBuilder(context.getApplicationContext(),
//                DataBaseConfig.class, "movie_database")
//                .build();
//    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor();
    }

    @Provides
    @Singleton
    OkHttpClient.Builder provideHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor);
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient.Builder okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .build();
    }

    @Singleton
    @Provides
    ApiHelper provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(ApiHelper.class);
    }


    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }

    @Provides
    @Singleton
    NetworkUtils provideNetworkConnectivity(@ApplicationContext Context context) {
        return new NetworkUtils(context);
    }

    @Provides
    @Singleton
    AnimationUtils provideAnimationUtil(@ApplicationContext Context context) {
        return new AnimationUtils(context);
    }

//
//    @Provides
//    @Singleton
//    DataBaseHelper provideDbHelper(AppDbHelper appDbHelper) {
//        return appDbHelper;
//    }
//
//
//    @Provides
//    @Singleton
//    DataBaseConfig provideRoomDatabase(@ApplicationContext Context context) {
//        return Room.databaseBuilder(context.getApplicationContext(),
//                DataBaseConfig.class, "movie_database")
//                .build();
//    }
//
//    @Provides
//    @Singleton
//    RoomDataBaseHelper provideRoomDataBaseHelper(DataBaseConfig roomDatabase) {
//        return new RoomDataBaseHelper(roomDatabase);
//    }
//
//    @Provides
//    RoomHelper providesRoomHelper(RoomDataBaseHelper roomDataBaseHelper) {
//        return roomDataBaseHelper;
//    }
//
//    @Provides
//    <T> MutableLiveData provideMutableLiveData(T t) {
//        return new MutableLiveData<T>();
//    }
}
