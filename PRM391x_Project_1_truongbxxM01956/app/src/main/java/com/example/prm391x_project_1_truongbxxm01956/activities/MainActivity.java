package com.example.prm391x_project_1_truongbxxm01956.activities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.GridView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.example.prm391x_project_1_truongbxxm01956.R;
import com.example.prm391x_project_1_truongbxxm01956.adapters.GrilViewAdapter;
import com.example.prm391x_project_1_truongbxxm01956.models.Aniamls;
import com.example.prm391x_project_1_truongbxxm01956.utils.Functions;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Aniamls> users;
    private GridView gallery;
    private GrilViewAdapter grilViewAdapter;
    private String[] names={
            //"animal",
            "ant","big foot","bird","cat",
            "dog","dolphin","dragon fly","elephant","fish",
            "frog","jelly fish","kbugbuster","lion","nessy",
            "penguin","pig","staroffice","turtle","xfiles monster",
            "ant","big foot","bird","cat",
            "dog","dolphin","dragon fly","elephant","fish",
            "frog","jelly fish","kbugbuster","lion","nessy",
            "penguin","pig","staroffice","turtle","xfiles monster",
    };
    private int[] description ={
            //"animal",
            R.string.ant,R.string.big_foot,R.string.bird,R.string.cat,
            R.string.dog,R.string.dolphin,R.string.dragon_fly,R.string.elephant,R.string.fish,
            R.string.frog,R.string.elly_fish,R.string.kbugbuster,R.string.lion,R.string.nessy,
            R.string.penguin,R.string.pig,R.string.staroffice,R.string.turtle,R.string.xfiles_monster,
            R.string.ant,R.string.big_foot,R.string.bird,R.string.cat,
            R.string.dog,R.string.dolphin,R.string.dragon_fly,R.string.elephant,R.string.fish,
            R.string.frog,R.string.elly_fish,R.string.kbugbuster,R.string.lion,R.string.nessy,
            R.string.penguin,R.string.pig,R.string.staroffice, R.string.turtle,R.string.xfiles_monster
    };
    private int[] photos={//R.drawable.bg_animal,
            R.drawable.ant,R.drawable.big_foot,R.drawable.bird,R.drawable.cat,R.drawable.dog,
            R.drawable.dolphin,R.drawable.dragon_fly,R.drawable.elephant,R.drawable.fish,R.drawable.frog,
            R.drawable.jelly_fish,R.drawable.kbugbuster,R.drawable.lion,R.drawable.nessy,R.drawable.penguin,
            R.drawable.pig,R.drawable.staroffice,R.drawable.turtle,R.drawable.xfiles_monster,

            R.drawable.ant,R.drawable.big_foot,R.drawable.bird,R.drawable.cat,R.drawable.dog,
            R.drawable.dolphin,R.drawable.dragon_fly,R.drawable.elephant,R.drawable.fish,R.drawable.frog,
            R.drawable.jelly_fish,R.drawable.kbugbuster,R.drawable.lion,R.drawable.nessy,R.drawable.penguin,
            R.drawable.pig,R.drawable.staroffice,R.drawable.turtle,R.drawable.xfiles_monster,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        users=new ArrayList<>();
        gallery=(GridView)findViewById(R.id.gallery);
        grilViewAdapter =new GrilViewAdapter(users,this);
        gallery.setAdapter(grilViewAdapter);
        // in the mime type you'd like to allow the user to select
        getDatas();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateDatas();
    }

    // getting all the datas
    private void getDatas(){
        for(int count=0;count<names.length;count++){
            users.add(new Aniamls(names[count], getString(description[count]),photos[count], false));
        }
    }
    private void updateDatas(){
        for(int count=0;count<users.size();count++){
            boolean isFavorite = Functions.getsetSharedPreferences(((Activity)this),users.get(count).getName());
            users.get(count).setFavorite(isFavorite);
        }
        grilViewAdapter =new GrilViewAdapter(users,this);
        gallery.setAdapter(grilViewAdapter);
    }
}
