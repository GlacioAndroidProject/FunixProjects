package com.example.prm391x_project_2_truongbxxm01956.ui.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prm391x_project_2_truongbxxm01956.R;
import com.example.prm391x_project_2_truongbxxm01956.ui.common.models.Aniamls;
import com.example.prm391x_project_2_truongbxxm01956.ui.common.utils.Functions;

public class AnimalDetail extends AppCompatActivity {
    Aniamls aniamlsInfo;
    ImageView avatar, favorite, imvPhone;
    Button btn_phone;

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
        avatar =(ImageView) findViewById(R.id.avata_animal);
        favorite =(ImageView) findViewById(R.id.favorite);
        name =(TextView) findViewById(R.id.txt_name);
        description =(TextView) findViewById(R.id.txt_description);
        imvPhone = (ImageView) findViewById(R.id.ic_phone);
        btn_phone = (Button) findViewById(R.id.btn_phone);
        avatar.setImageResource(aniamlsInfo.getPhoto());
        setImagePhoto(favorite, aniamlsInfo.isFavorite());
        getPhoneFromMemory();
        imvPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallPhone();
            }
        });
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
                boolean isfavorite = aniamlsInfo.isFavorite();
                isfavorite =!isfavorite;
                aniamlsInfo.setFavorite(isfavorite);
                setImagePhoto(favorite, isfavorite);
                Functions.setSharedPreferencesByBooleanValue(((Activity)context),aniamlsInfo.getName(), aniamlsInfo.isFavorite());
            }
        });
        btn_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
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
    void getPhoneFromMemory()
    {
        String key = aniamlsInfo.getName() + String.valueOf(aniamlsInfo.getPhoto());
        String phone =  Functions.getSharedPreferencesByStringValue(context, key);
        aniamlsInfo.setPhone(phone);
        btn_phone.setText(phone);
    }
    void setPhoneToMemory()
    {
        String keySavePhone = aniamlsInfo.getName() + String.valueOf(aniamlsInfo.getPhoto());
        String keySaveImage = aniamlsInfo.getPhone();
        Functions.setSharedPreferencesByStringValue(context, keySavePhone, aniamlsInfo.getPhone());
        Functions.setSharedPreferencesByStringValue(context, keySaveImage, String.valueOf(aniamlsInfo.getPhoto()));

    }
    void showDialog(){
        String phone = btn_phone.getText().toString();
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText edittext = new EditText(context);
        edittext.setText(phone);
        alert.setMessage("Enter Phone");
        alert.setTitle("Enter Phone");

        alert.setView(edittext);

        alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String phoneTemp = edittext.getText().toString();
                aniamlsInfo.setPhone(phoneTemp);
                btn_phone.setText(phoneTemp);
                setPhoneToMemory();
            }
        });

        alert.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });

        alert.show();
    }
    void CallPhone(){

    }
}