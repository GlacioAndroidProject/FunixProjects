package com.example.funixworking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/*
public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    private ImageView ivAnimal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        initViews();
    }
    private void initViews() {
        ivAnimal = findViewById(R.id.iv_animal);
        findViewById(R.id.bt_alpha).setOnClickListener(this);
        findViewById(R.id.bt_rotate).setOnClickListener(this);
        findViewById(R.id.bt_scale).setOnClickListener(this);
        findViewById(R.id.bt_trans).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_alpha) {
            ivAnimal.startAnimation(AnimationUtils.loadAnimation(this, R.anim.alpha));
        } else if (v.getId() == R.id.bt_rotate) {
            ivAnimal.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate));
        } else if (v.getId() == R.id.bt_scale) {
            ivAnimal.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale));
        } else if (v.getId() == R.id.bt_trans) {
            ivAnimal.startAnimation(AnimationUtils.loadAnimation(this, R.anim.translate));
        }
    }
}*/
import android.os.Bundle;

import android.view.LayoutInflater;

import android.view.View;

import android.widget.ImageView;

import android.widget.LinearLayout;

import android.widget.TextView;



import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

    private static final int[] ID_DRAWABLES = {R.drawable.ic_mess, R.drawable.ic_flight, R.drawable.ic_hospital,

            R.drawable.ic_hotel, R.drawable.ic_restaurant, R.drawable.ic_coctail,

            R.drawable.ic_store, R.drawable.ic_at_work, R.drawable.ic_time, R.drawable.ic_education, R.drawable.ic_movie};



    private static final int[] ID_TEXTS = {R.string.txt_mess, R.string.txt_flight, R.string.txt_hospital,

            R.string.txt_hotel, R.string.txt_restaurant, R.string.txt_coctail,

            R.string.txt_store, R.string.txt_work, R.string.txt_time, R.string.txt_education, R.string.txt_movie};



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        initView();

    }



    private void initView() {

        LinearLayout lnMain = findViewById(R.id.ln_main);

        lnMain.removeAllViews();

        //Tạo ra các Item topic động và add vào LinearLayout

        for (int i = 0; i < ID_DRAWABLES.length; i++) {

            View v = LayoutInflater.from(this).inflate(R.layout.item_topic, null);

            ImageView ivTopic = v.findViewById(R.id.iv_topic);

            TextView tvTopic = v.findViewById(R.id.tv_topic);

            ivTopic.setImageResource(ID_DRAWABLES[i]);

            tvTopic.setText(ID_TEXTS[i]);

            //Quy định không gian chiếm của mỗi item view = 1

            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(

                    LinearLayout.LayoutParams.MATCH_PARENT,

                    LinearLayout.LayoutParams.MATCH_PARENT,

                    1.0f

            );

            v.setLayoutParams(param);

            lnMain.addView(v);

        }

    }

}