package com.example.raziel214.marvelheroes.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.raziel214.marvelheroes.HeroListFragment;
import com.example.raziel214.marvelheroes.Models.SuperHero;
import com.example.raziel214.marvelheroes.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.MyViewHolder> {


    ArrayList<SuperHero> superHeroArraylist;
    Context context;
    HeroListFragment.HeroClickListener heroClickListener;

   // public ImageView heroPictureImageView;
    //public TextView heroDetailNameTextView1;


    public HeroAdapter(ArrayList<SuperHero> superHeroArraylist, Context context, HeroListFragment.HeroClickListener heroClickListener) {
        this.superHeroArraylist = superHeroArraylist;
        this.context = context;
        this.heroClickListener=heroClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



        View view= LayoutInflater.from(context).inflate(R.layout.hero_list_item,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        SuperHero superHero= superHeroArraylist.get(position);

        holder.bind(context,superHero,heroClickListener);




        //holder.heroPictureImageview1.setImageResource(superHero.getFullPathUrl());

    }

    @Override
    public int getItemCount() {
        return superHeroArraylist.size();
    }

   static public class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView heroPictureImageView;
        public TextView heroDetailNameTextView1;


        public MyViewHolder(View itemView) {

            super(itemView);
            heroPictureImageView= (ImageView) itemView.findViewById(R.id.heroPictureImageView);
            heroDetailNameTextView1 =  itemView.findViewById(R.id.heroDetailNameTextView);
        }

        public void bind(Context context, final SuperHero superHero , final HeroListFragment.HeroClickListener heroClickListener){

            heroDetailNameTextView1.setText(superHero.getName());

            Picasso.get().load(superHero.getThumbnail().getFullPathUrl()).into(heroPictureImageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    heroClickListener.onHeroCliked(superHero);
                }
            });



        }
    }

}
