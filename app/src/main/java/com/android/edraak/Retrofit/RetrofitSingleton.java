package com.android.edraak.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {

    private static Retrofit retrofit;

    private static Retrofit getRetrofitInstance() {

        if (retrofit == null) {

            synchronized (RetrofitSingleton.class) {

                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl("Make a Constant Class Carry Final Base Url and call him her")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return retrofit;
    }

    public static RetrofitService getServices() {
        return getRetrofitInstance().create(RetrofitService.class);
    }
}