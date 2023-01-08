package com.example.prm391x_project_2_truongbxxm01956.ui.common.adapters;

import static androidx.core.app.ActivityCompat.startActivityForResult;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.prm391x_project_2_truongbxxm01956.R;
import com.example.prm391x_project_2_truongbxxm01956.ui.common.AnimalDetail;
import com.example.prm391x_project_2_truongbxxm01956.ui.common.models.Aniamls;
import com.example.prm391x_project_2_truongbxxm01956.ui.common.utils.Functions;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.StoryHolder> {
    private ArrayList<Aniamls> aniamls;
    private Context mContext;
    public RecyclerViewAdapter(ArrayList<Aniamls> aniamls, Context context) {
        this.aniamls = aniamls;
        this.mContext = context;
    }

    @Override
    public StoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.items_layout, parent, false);
        return new StoryHolder(view);
    }

    @Override
    public void onBindViewHolder(StoryHolder holder, int position) {
        Aniamls item = aniamls.get(position);
        setFavoriteImagePhoto(holder.favorite, item.isFavorite());
        holder.photo.setImageResource(item.getPhoto());
        holder.photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(mContext, androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom));
                Intent myIntent = new Intent(mContext, AnimalDetail.class);
                myIntent.putExtra("animalObjectDetail", item); //Optional parameters
                Activity activity = (Activity) mContext;
                startActivityForResult(activity, myIntent, 101, null );

            }
        });
        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isfavorite = item.isFavorite();
                isfavorite =!isfavorite;
                item.setFavorite(isfavorite);
                setFavoriteImagePhoto(holder.favorite, isfavorite);
                Functions.setSharedPreferencesByBooleanValue((Activity) mContext, item.getName(), item.isFavorite());
            }
        });
    }

    @Override
    public int getItemCount() {
        return aniamls.size();
    }

    public class StoryHolder extends RecyclerView.ViewHolder {
        ImageView photo,favorite;

        public StoryHolder(View itemView) {
            super(itemView);
            if(itemView==null){
                photo=new ImageView(mContext);
            }
            photo = (ImageView)itemView.findViewById(R.id.photo);
            favorite = (ImageView)itemView.findViewById(R.id.favorite);

        }
    }
    void setFavoriteImagePhoto(ImageView favorite, boolean isFavorite){
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