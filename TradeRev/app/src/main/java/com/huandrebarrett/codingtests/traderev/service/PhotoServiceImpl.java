package com.huandrebarrett.codingtests.traderev.service;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class PhotoServiceImpl implements PhotoService {
    @Override
    public void getPhotos(Request request, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(callback);
    }
}
