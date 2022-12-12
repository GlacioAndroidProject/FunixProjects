package com.example.funixassignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity_Zodiac extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_zodiac);
        CircleMenu cm = (CircleMenu) findViewById(R.id.c_menu);
        cm.setDefautsubColor(R.color.white);
        cm.setMainColor(R.color.purple_500);
        cm.addMenuItem("one",1,R.color.colorPrimary, R.drawable.ic_bach_duong);
        cm.addMenuItem("two",2,R.color.colorPrimary, R.drawable.ic_bach_duong);
        cm.addMenuItem("three",3,R.color.colorPrimary, R.drawable.ic_bach_duong);
        cm.addMenuItem("ten",4,R.color.colorPrimary, R.drawable.ic_bach_duong);
        cm.addMenuItem("oh oh",5,R.color.colorPrimary, R.drawable.ic_bach_duong);
        cm.addMenuItem("exit",6,R.color.colorPrimary, R.drawable.ic_bach_duong);
        cm.addMenuItem("one",7,R.color.colorPrimary, R.drawable.ic_bach_duong);
        cm.addMenuItem("two",8,R.color.colorPrimary, R.drawable.ic_bach_duong);
        cm.addMenuItem("three",9,R.color.colorPrimary, R.drawable.ic_bach_duong);
        cm.addMenuItem("ten",10,R.color.colorPrimary, R.drawable.ic_bach_duong);
        cm.addMenuItem("oh oh",11,R.color.colorPrimary, R.drawable.ic_bach_duong);
        cm.addMenuItem("exit",12,R.color.colorPrimary, R.drawable.ic_bach_duong);
        cm.setListener(new CircleMenu.IMenuListener() {
            @Override
            public void onMenuClick(CircleMenu.MenuCircle item) {
                Toast.makeText(MainActivity_Zodiac.this,item.text+" "+item.id,Toast.LENGTH_LONG).show();
            }
        });
    }
}