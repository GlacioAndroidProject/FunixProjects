package com.example.prm391x_project_1_truongbxxm01956;

import static com.google.android.material.internal.ContextUtils.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prm391x_project_1_truongbxxm01956.models.Aniamls;
import com.example.prm391x_project_1_truongbxxm01956.utils.Functions;

public class AnimalDetail extends AppCompatActivity {
    Aniamls aniamlsInfo;
    ImageView avata, favorite;
    TextView name, description;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_animal_detail);
        getIntentData();
        setView(aniamlsInfo);
    }
    void getIntentData(){
        Intent i = getIntent();
        aniamlsInfo = (Aniamls) i.getParcelableExtra("animalObjectDetail");
    }
    void setView(Aniamls aniamlsInfo){
        avata=(ImageView) findViewById(R.id.avata_animal);
        favorite =(ImageView) findViewById(R.id.favorite);
        name =(TextView) findViewById(R.id.txt_name);
        description =(TextView) findViewById(R.id.txt_description);
        avata.setImageResource(aniamlsInfo.getPhoto());
        setImagePhoto(favorite, aniamlsInfo.isFavorite());
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
                boolean isfavorite = aniamlsInfo.isFavorite();
                isfavorite =!isfavorite;
                aniamlsInfo.setFavorite(isfavorite);
                setImagePhoto(favorite, isfavorite);
                Functions.setSharedPreferences(((Activity)context),aniamlsInfo.getName(), aniamlsInfo.isFavorite());
            }
        });
        name.setText(aniamlsInfo.getName());
        description.setText(aniamlsInfo.getDescription());
    }
    void setImagePhoto(ImageView favorite, boolean isFavorite){
        if(isFavorite)
        {
            favorite.setImageResource(R.drawable.enable_favorite);
        }
        else
        {
            favorite.setImageResource(R.drawable.disable_favorite);
        }
    }
}