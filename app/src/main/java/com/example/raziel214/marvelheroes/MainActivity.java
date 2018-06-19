package com.example.raziel214.marvelheroes;

import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

    public static final int SUCCES_CODE = 200;
    public static final int AVENGERS_COMIC_ID=354;
    private static final String HERO_LIST_FRAGMENT="hero_lis_fragment";
    private static final String TAG=MainActivity.class.getSimpleName();
    public static final String HERO_LIST="hero_list";
    private FrameLayout frameLayout;
    private ArrayList<SuperHero>superHeros;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout= (FrameLayout) findViewById(R.id.placeholder);
      //Call<Basic<Data<ArrayList<SuperHero>>>>

        //Call<Basic<Data<ArrayList<SuperHero>>>> superHeroesCall= MarvelService.getMarvelApi().getHeroes(AVENGERS_COMIC_ID,"-name");
        getHeroList();










    }


    public class GenericClass {

        int a = 5;

        int b = 8;



        public GenericClass(int a, int b) {

            this.a = a * 2;

            this.b = b * 2;

        }

    }

    public class CustomClass extends GenericClass {

        final String TAG = "Questions";



        public CustomClass() {

            super(10, 20);



            Log.d(TAG, String.format("A = %1$d, B = %2$d", a, b));

        }

    }


    private void getHeroList(){

        Call<Basic<Data<ArrayList<SuperHero>>>> superHeroesCall= MarvelService.getMarvelApi().getHeroes(AVENGERS_COMIC_ID);

        superHeroesCall.enqueue(new Callback<Basic<Data<ArrayList<SuperHero>>>>() {
            @Override
            public void onResponse(Call<Basic<Data<ArrayList<SuperHero>>>> call, Response<Basic<Data<ArrayList<SuperHero>>>> response) {

                //Toast.makeText(MainActivity.this,"Hero name is :"+ response.body().getData().getResults().get(0).getName(),Toast.LENGTH_LONG).show();


                if (response.code()== SUCCES_CODE){

                    superHeros=response.body().getData().getResults();

                    /**Toast.makeText(MainActivity.this, "Hero name is :" + superHeros.get(0).getName()+" ", Toast.LENGTH_SHORT).show();*/

                    Bundle bundle= new Bundle();

                    bundle.putParcelableArrayList(HERO_LIST,superHeros);


                    FragmentManager fragmentManager= getSupportFragmentManager();

                    boolean is_tablet = getResources().getBoolean(R.bool.is_table);

                    if(is_tablet){
                        Toast.makeText(MainActivity.this, "es tablet", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "es un telefono", Toast.LENGTH_SHORT).show();
                    }

                    HeroListFragment savedFragment= (HeroListFragment) fragmentManager.findFragmentByTag(HERO_LIST_FRAGMENT);

                    if(savedFragment==null){

                        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

                        HeroListFragment heroListFragment=new HeroListFragment();

                        heroListFragment.setArguments(bundle);

                        fragmentTransaction.add(R.id.placeholder,heroListFragment, HERO_LIST_FRAGMENT);

                        fragmentTransaction.commit();

                    }


                }
                else{
                    displayErrorMessage(getString(R.string.service_error_message));
                }

            }

            @Override
            public void onFailure(Call<Basic<Data<ArrayList<SuperHero>>>> call, Throwable t) {

                //Toast.makeText(MainActivity.this,"Error in callBack:",Toast.LENGTH_LONG);

                displayErrorMessage(getString(R.string.network_error_message));

            }
        });


    }

    public void displayErrorMessage(String mensaje){
        Snackbar snackbar= Snackbar.make(frameLayout,mensaje,Snackbar.LENGTH_INDEFINITE).
                setAction(getString(R.string.ok), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getHeroList();
                    }
                });

        snackbar.show();
    }
}
