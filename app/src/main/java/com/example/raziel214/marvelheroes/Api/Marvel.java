package com.example.raziel214.marvelheroes.Api;

import com.example.raziel214.marvelheroes.Models.Basic;
import com.example.raziel214.marvelheroes.Models.Data;
import com.example.raziel214.marvelheroes.Models.SuperHero;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Marvel {

    String BASE_URL="https://gateway.marvel.com/";

        //v1/public/series/354/characters?apikey=81a740022689a4eff215f0bf8c95448c&ts=1&hash=696dfe8f0aea9b17e522ceaf66ffc26e

    @GET("v1/public/series/{seriesID}/characters?apikey=81a740022689a4eff215f0bf8c95448c&ts=1&hash=696dfe8f0aea9b17e522ceaf66ffc26e")
    Call<Basic<Data<ArrayList<SuperHero>>>> getHeroes(@Path("seriesID") int seriesId);
}
