package com.example.funixassignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.widget.Toast;

public class MainActivity_Fragment extends AppCompatActivity implements DemoFragmentInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        Fragment1 fragment1 = new Fragment1();
        transaction.add(R.id.containerTop,fragment1);

        Fragment2 fragment2 = new Fragment2();
        transaction.add(R.id.containerBottom,fragment2);
        transaction.commit();
    }
    @Override
    public void sendData(String name, String age) {
        Fragment2 fragment2 = (Fragment2) getSupportFragmentManager().findFragmentById(R.id.containerBottom);
        if (fragment2 != null || fragment2.isInLayout()){
            fragment2.showInfor(name,age);
        }else {
            Toast.makeText(getApplicationContext(),"???",Toast.LENGTH_LONG).show();
        }
    }
}