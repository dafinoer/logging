package com.example.dafidzeko.logging.controller;

import com.example.dafidzeko.logging.ViewType;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dafidzeko on 20/11/16.
 */

public class RestManager {

    private Service service;

    public Service getService() {
        if (service == null) {


            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

            // set log level km
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

            //add logging
            okHttpClient.addInterceptor(httpLoggingInterceptor).build();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ViewType.BASE_URL)
                    .client(okHttpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(Service.class);
        }
        return service;
    }
}
