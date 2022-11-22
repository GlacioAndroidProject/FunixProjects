package com.example.prm391x_project_1_truongbxxm01956.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.prm391x_project_1_truongbxxm01956.AnimalDetail;
import com.example.prm391x_project_1_truongbxxm01956.R;
import com.example.prm391x_project_1_truongbxxm01956.models.Aniamls;

import java.util.ArrayList;

public class GrilViewAdapter extends BaseAdapter implements View.OnClickListener{
    private ArrayList<Aniamls> users;
    private Context context;

    public GrilViewAdapter(ArrayList<Aniamls> users, Context context) {
        this.users = users;
        this.context = context;
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
        photo.setImageResource(user.getPhoto());
        if(i == 0)
        {
           // viewGroup.setMinimumWidth(viewGroup.getWidth()*2);
        }
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aniamls user=users.get(i);
                Intent myIntent = new Intent(context, AnimalDetail.class);
                myIntent.putExtra("animalObjectDetail", user); //Optional parameters
                context.startActivity(myIntent);
            }
        });
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean isfavorite = users.get(i).isFavorite();
                isfavorite =!isfavorite;
               users.get(i).setFavorite(isfavorite);
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

        return view;
    }

    @Override
    public void onClick(View view) {
        showOptionMenu(view);
    }

    private void showOptionMenu(View view) {
        PopupMenu popupMenu=new PopupMenu(context,view);
        popupMenu.getMenuInflater().inflate(R.menu.option_menu,popupMenu.getMenu());
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.edit:
                        Toast.makeText(context, "Edit", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.remove:
                        Toast.makeText(context, "Remove", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
}
