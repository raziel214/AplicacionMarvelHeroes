package com.example.raziel214.marvelheroes;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
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

    private static final String HERO_LIST_FRAGMENT="hero_lis_fragment";

    private static final String TAG=MainActivity.class.getSimpleName();
    public static final int SUCCES_CODE = 200;

    private FrameLayout frameLayout;

    public static final int AVENGERS_COMIC_ID=354;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout= (FrameLayout) findViewById(R.id.placeholder);
      //Call<Basic<Data<ArrayList<SuperHero>>>>

        //Call<Basic<Data<ArrayList<SuperHero>>>> superHeroesCall= MarvelService.getMarvelApi().getHeroes(AVENGERS_COMIC_ID,"-name");
        Call<Basic<Data<ArrayList<SuperHero>>>> superHeroesCall= MarvelService.getMarvelApi().getHeroes(AVENGERS_COMIC_ID);

        superHeroesCall.enqueue(new Callback<Basic<Data<ArrayList<SuperHero>>>>() {
            @Override
            public void onResponse(Call<Basic<Data<ArrayList<SuperHero>>>> call, Response<Basic<Data<ArrayList<SuperHero>>>> response) {

                //Toast.makeText(MainActivity.this,"Hero name is :"+ response.body().getData().getResults().get(0).getName(),Toast.LENGTH_LONG).show();
                Toast.makeText(MainActivity.this, "Hero name is :" + response.body().getData().getResults().get(0).getName()+" ", Toast.LENGTH_SHORT).show();

                if (response.code()== SUCCES_CODE){


                    FragmentManager fragmentManager= getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

                    HeroListFragment heroListFragment=new HeroListFragment();

                    fragmentTransaction.add(R.id.placeholder,heroListFragment, HERO_LIST_FRAGMENT);
                    fragmentTransaction.commit();
                }
                else{
                    Log.d(TAG,"Error in request");
                }

            }

            @Override
            public void onFailure(Call<Basic<Data<ArrayList<SuperHero>>>> call, Throwable t) {

                Toast.makeText(MainActivity.this,"Error in callBack:",Toast.LENGTH_LONG);

            }
        });










    }
}
