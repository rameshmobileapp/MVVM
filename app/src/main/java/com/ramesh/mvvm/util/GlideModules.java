package com.ramesh.mvvm.util;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

public class GlideModules extends AppGlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        RequestOptions options = new RequestOptions().format(DecodeFormat.PREFER_ARGB_8888);

    }

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {

    }

}
