package com.example.lab5;

import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;


import com.example.lab5.fragments.M000SplashFrg;
import com.example.lab5.fragments.M001TopicFrg;
import com.example.lab5.fragments.M002StoryFrg;
import com.example.lab5.fragments.M003DetailStoryFrg;

import java.util.ArrayList;

import java.util.List;



public class MainActivity extends AppCompatActivity {



    private String topicName;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        showFrg(new M000SplashFrg());

    }



    private void showFrg(Fragment frg) {

        getSupportFragmentManager().beginTransaction().replace(R.id.ln_main, frg, null).commit();

    }



    public void gotoM001Screen() {

        getSupportFragmentManager().beginTransaction().replace(R.id.ln_main, new M001TopicFrg(), null).commit();

    }



    public void gotoM002Screen(String topicName) {

        this.topicName = topicName;

        M002StoryFrg frg = new M002StoryFrg();

        frg.setTopicName(topicName);

        showFrg(frg);

    }



    public void backToM001Screen() {

        gotoM001Screen();

    }



    public void gotoM003Screen(ArrayList<StoryEntity> listStory, StoryEntity story) {

        M003DetailStoryFrg frg = new M003DetailStoryFrg();

        frg.setData(topicName, listStory, story);

        showFrg(frg);

    }

}