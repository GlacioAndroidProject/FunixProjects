package com.example.prm391x_project_2_truongbxxm01956.ui.common.adapters;

import static androidx.core.app.ActivityCompat.startActivityForResult;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.prm391x_project_2_truongbxxm01956.R;
import com.example.prm391x_project_2_truongbxxm01956.ui.common.AnimalDetail;
import com.example.prm391x_project_2_truongbxxm01956.ui.common.models.Aniamls;
import com.example.prm391x_project_2_truongbxxm01956.ui.common.utils.Functions;

import java.util.ArrayList;

public class GrilViewAdapter extends BaseAdapter{
    private ArrayList<Aniamls> users;
    private Context context;

    public GrilViewAdapter(ArrayList<Aniamls> users, Context context) {
        this.users = users;
        this.context = context;
        // GetContent creates an ActivityResultLauncher<String> to allow you to pass
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=layoutInflater.inflate(R.layout.custom_gallery_layout,null);
        ImageView photo,favorite;
        if(view==null){
            photo=new ImageView(context);
        }
        photo=(ImageView)view.findViewById(R.id.photo);
        favorite =(ImageView)view.findViewById(R.id.favorite);
        Aniamls user=users.get(i);
        setImagePhoto(favorite, user.isFavorite());

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aniamls user=users.get(i);
                v.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom));
                Intent myIntent = new Intent(context, AnimalDetail.class);
                myIntent.putExtra("animalObjectDetail", user); //Optional parameters
                //context.startActivity(myIntent);
                Activity activity = (Activity) context;
                startActivityForResult(activity, myIntent, 101, null );

            }
        });
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean isfavorite = users.get(i).isFavorite();
                isfavorite =!isfavorite;
                users.get(i).setFavorite(isfavorite);
                setImagePhoto(favorite, isfavorite);
                Functions.setSharedPreferences((Activity) context, users.get(i).getName(), users.get(i).isFavorite());
            }
        });

        return view;
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
