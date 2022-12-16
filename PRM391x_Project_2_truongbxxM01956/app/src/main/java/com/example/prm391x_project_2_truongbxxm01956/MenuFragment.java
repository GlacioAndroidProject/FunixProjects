package com.example.prm391x_project_2_truongbxxm01956;

import android.content.Context;

import android.graphics.Bitmap;

import android.graphics.BitmapFactory;

import android.os.Bundle;

import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;

import android.view.animation.AnimationUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.prm391x_project_2_truongbxxm01956.ui.common.AnimalDetail;
import java.util.ArrayList;
import java.util.List;
public class MenuFragment extends Fragment {

    private Context mContext;
    private RecyclerView rvAnimal;

    private List<AnimalDetail> listAnimals;

    private DrawerLayout mDrawer;
    @Nullable

    @Override

    public View onCreateView(@NonNull LayoutInflater inflater,

                             @Nullable ViewGroup container,

                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_menu, container, false);

        initView(v);

        return v;

    }
    @Override

    public void onAttach(@NonNull Context context) {

        super.onAttach(context);

        mContext = context;

    }
    private void initView(View v) {

        mDrawer = v.findViewById(R.id.drawer);

        //rvAnimal = v.findViewById(R.id.rv_animals);

        //Xử lý mở menu trái
        //v.findViewById(R.id.iv_menu).setOnClickListener(v12 -> mDrawer.openDrawer(GravityCompat.START));

        //Hiển thị ảnh động vật biển

        v.findViewById(R.id.iv_sea).setOnClickListener(v1 -> {

            v.startAnimation(AnimationUtils.loadAnimation(mContext, androidx.appcompat.R.anim.abc_fade_in));

            showAnimals("sea");

        });



        //Hiển thị ảnh động vật có vú

        v.findViewById(R.id.iv_mammal).setOnClickListener(v1 -> {

            v.startAnimation(AnimationUtils.loadAnimation(mContext, androidx.appcompat.R.anim.abc_fade_in));

            showAnimals("mammal");

        });



        //Hiển thị ảnh chim muông

        v.findViewById(R.id.iv_bird).setOnClickListener(v1 -> {

            v.startAnimation(AnimationUtils.loadAnimation(mContext, androidx.appcompat.R.anim.abc_fade_in));

            showAnimals("bird");

        });



        //Hiển thị danh sách ảnh lên RecyclerView

        if (listAnimals != null) {

/*            GrilViewAdapter animalAdapter = new GrilViewAdapter(listAnimals, mContext, vv -> doCLickAnimal((AnimalDetail) vv.getTag()));

            rvAnimal.setAdapter(animalAdapter);*/

        }

    }



    private void showAnimals(String animalType) {

        listAnimals = new ArrayList<>();

        //Học sinh tự xử lý để xây dựng được danh sách Animal tương ứng dựa vào animalType

        //Hiển thị danh sách ảnh lên RecyclerView
/*        GrilViewAdapter animalAdapter = new GrilViewAdapter(listAnimals, mContext, v -> doCLickAnimal((AnimalDetail) v.getTag()));

        rvAnimal.setAdapter(animalAdapter);*/

        mDrawer.closeDrawers();

    }



    private void doCLickAnimal(AnimalDetail animal) {

        //Chuyển sang màn hình chi tiết

        MainActivity act = (MainActivity) mContext;

        //act.showDetail(listAnimals, animal);

    }

}