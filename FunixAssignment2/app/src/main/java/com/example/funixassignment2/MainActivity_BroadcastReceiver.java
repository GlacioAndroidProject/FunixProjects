package com.example.funixassignment2;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;

public class MainActivity_BroadcastReceiver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_broadcast_receiver);
        initViews();
    }

    private void initViews() {
        try {
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_SCREEN_ON);
            filter.addAction(Intent.ACTION_SCREEN_OFF);
            BroadcastReceiver receiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    doScreenTalk(intent.getAction());
                }
            };
            registerReceiver(receiver, filter);
        }catch (Exception ignored){

        }

        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{

                    Manifest.permission.READ_PHONE_STATE

            }, 101);

        }
    }

    private void doScreenTalk(String action) {
        if(action.equals(Intent.ACTION_SCREEN_ON)){
            sayHello();
        }else if(action.equals(Intent.ACTION_SCREEN_OFF)){
            sayGoodbye();
        }
    }

    private void sayGoodbye() {
        MediaPlayer.create(this, R.raw.bye_bye).start();
    }

    private void sayHello() {
        MediaPlayer.create(this, R.raw.hello).start();
    }
}
/*
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity_BroadcastReceiver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_broadcast_receiver);
    }
}*/
