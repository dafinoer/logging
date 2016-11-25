package com.example.dafidzeko.logging.controller;

import com.example.dafidzeko.logging.model.Bunga;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by dafidzeko on 20/11/16.
 */

public interface Service {

    @GET("/feeds/flowers.json")
    Call<List<Bunga>> getDataBunga();
}
