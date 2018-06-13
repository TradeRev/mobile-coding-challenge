package com.huandrebarrett.codingtests.traderev.service;

import okhttp3.Callback;
import okhttp3.Request;

public interface PhotoService {
    void getPhotos(Request request, Callback callback);
}
