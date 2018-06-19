package com.example.raziel214.marvelheroes;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.raziel214.marvelheroes.Models.SuperHero;
import com.squareup.picasso.Picasso;


public class HeroDetailFragment extends Fragment {

    SuperHero superHero;
    TextView heroNameTextView;
    TextView heroDescriptionTextView;
    ImageView heroPictureImageView;



    public HeroDetailFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            superHero=getArguments().getParcelable(HeroListFragment.SUPER_HERO);

            //Toast.makeText(getContext(),"The hero detail is: "+superHero.getName(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_hero_detail, container, false);

        heroNameTextView=view.findViewById(R.id.heroDetailTitleTextView);
        heroDescriptionTextView=view.findViewById(R.id.heroDetailDescriptionTextView);
        heroPictureImageView=view.findViewById(R.id.heroDetailThumbnailTextView);

        heroNameTextView.setText(superHero.getName());

        if(superHero.getDescription()!=null && !superHero.getDescription().isEmpty()){
            heroDescriptionTextView.setText(superHero.getDescription());
        }

        else{
            heroDescriptionTextView.setText(getString(R.string.No_information));
        }



        Picasso.get().load(superHero.getThumbnail().getFullPathUrl()).into(heroPictureImageView);





        return view;
    }

}
