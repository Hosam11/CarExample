package com.example.carexample.data.network;


import com.example.carexample.data.model.CarModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CarApiInterface {

    @GET("cars?")
    Call<CarModel> getCarData (@Query("page") int page);

}
