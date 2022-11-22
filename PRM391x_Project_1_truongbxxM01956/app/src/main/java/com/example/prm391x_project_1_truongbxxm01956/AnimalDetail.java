package com.example.prm391x_project_1_truongbxxm01956;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prm391x_project_1_truongbxxm01956.models.Aniamls;

public class AnimalDetail extends AppCompatActivity {
    Aniamls aniamlsInfo;
    ImageView avata, favorite;
    TextView name, description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isfavorite = aniamlsInfo.isFavorite();
                isfavorite =!isfavorite;
                aniamlsInfo.setFavorite(isfavorite);
                if(isfavorite)
                {
                    favorite.setImageResource(R.drawable.enable_favorite);
                }
                else
                {
                    favorite.setImageResource(R.drawable.disable_favorite);
                }
            }
        });
        name.setText(aniamlsInfo.getName());
        description.setText(aniamlsInfo.getDescription());
    }
}