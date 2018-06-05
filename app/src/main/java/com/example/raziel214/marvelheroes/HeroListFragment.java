package com.example.raziel214.marvelheroes;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.raziel214.marvelheroes.Adapters.HeroAdapter;
import com.example.raziel214.marvelheroes.Models.SuperHero;

import java.util.ArrayList;

import static java.security.AccessController.getContext;


public class HeroListFragment extends Fragment {

    private static final String TAG="No Data in the Superhero";

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;


    ArrayList<SuperHero> superHeroes;


    public HeroListFragment() {
        // Required empty public constructor
    }


    public interface HeroClickListener{
        void  onHeroCliked(SuperHero superHero);
    }



    @SuppressLint("LongLogTag")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle= getArguments();
        superHeroes=bundle.getParcelableArrayList(MainActivity.HERO_LIST);

        if(superHeroes==null){
            Log.d(TAG,"No data in bundle");

        }
        /*else{
            Toast.makeText(getContext(),"The first super Hero is :"+superHeroes.get(0).getName(),Toast.LENGTH_LONG).show();

        }*/



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_hero_list, container, false);

        recyclerView =(RecyclerView) view.findViewById(R.id.superHeroRecyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager( layoutManager);
        HeroAdapter heroAdapter = new HeroAdapter(superHeroes, getContext(), new HeroClickListener() {
            @Override
            public void onHeroCliked(SuperHero superHero) {
                //Cambiar  de Fragment a el HeroDetailFragment

                goToHeroDetailFragment();
                
            }
        });
        recyclerView.setAdapter(heroAdapter);
        return view;
    }

    private void goToHeroDetailFragment() {

        Toast.makeText(getContext(),"aqui estoy ",Toast.LENGTH_LONG).show();

    }

}
