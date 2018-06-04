package com.example.raziel214.marvelheroes;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.raziel214.marvelheroes.Models.SuperHero;

import java.util.ArrayList;


public class HeroListFragment extends Fragment {

    private static final String TAG="No Data in the Superhero";

    ArrayList<SuperHero> superHeroes;


    public HeroListFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle= getArguments();
        superHeroes=bundle.getParcelableArrayList(MainActivity.HERO_LIST);

        if(superHeroes==null){
            Log.w(TAG,"No data in bundle");

        }
        else{
            Toast.makeText(getContext(),"The first super Hero is :"+superHeroes.get(0).getName(),Toast.LENGTH_LONG).show();

        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hero_list, container, false);
    }

}
