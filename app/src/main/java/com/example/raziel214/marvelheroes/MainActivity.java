package com.example.raziel214.marvelheroes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.raziel214.marvelheroes.Api.MarvelService;
import com.example.raziel214.marvelheroes.Models.Basic;
import com.example.raziel214.marvelheroes.Models.Data;
import com.example.raziel214.marvelheroes.Models.SuperHero;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final int AVENGERS_COMIC_ID=354;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //Call<Basic<Data<ArrayList<SuperHero>>>>

        //Call<Basic<Data<ArrayList<SuperHero>>>> superHeroesCall= MarvelService.getMarvelApi().getHeroes(AVENGERS_COMIC_ID,"-name");
        Call<Basic<Data<ArrayList<SuperHero>>>> superHeroesCall= MarvelService.getMarvelApi().getHeroes(AVENGERS_COMIC_ID);

        superHeroesCall.enqueue(new Callback<Basic<Data<ArrayList<SuperHero>>>>() {
            @Override
            public void onResponse(Call<Basic<Data<ArrayList<SuperHero>>>> call, Response<Basic<Data<ArrayList<SuperHero>>>> response) {

                //Toast.makeText(MainActivity.this,"Hero name is :"+ response.body().getData().getResults().get(0).getName(),Toast.LENGTH_LONG).show();
                Toast.makeText(MainActivity.this, "Hero name is :" + response.body().getData().getResults().get(0).getName()+" ", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Basic<Data<ArrayList<SuperHero>>>> call, Throwable t) {

                Toast.makeText(MainActivity.this,"Error in callBack:",Toast.LENGTH_LONG);

            }
        });
    }
}
