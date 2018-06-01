package com.example.raziel214.marvelheroes.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MarvelService {

    public static Marvel getMarvelApi(){


        return new Retrofit.Builder().
                baseUrl(Marvel.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build().
                create(Marvel.class);
    }
}
