package com.example.prm391x_project_2_truongbxxm01956;

import static com.example.prm391x_project_2_truongbxxm01956.MainActivity.CONTENT_VIEW_ID;

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
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm391x_project_2_truongbxxm01956.ui.Manimals.ManimalsFragment;
import com.example.prm391x_project_2_truongbxxm01956.ui.birds.BirdsFragment;
import com.example.prm391x_project_2_truongbxxm01956.ui.common.AnimalDetail;
import com.example.prm391x_project_2_truongbxxm01956.ui.seas.SeasFragment;

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

    }

    private void showAnimals(String animalType) {
        switch (animalType){
            case "sea":
            {
                SeasFragment newFragment = new SeasFragment();
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.add(R.id.fragment_container_view_seas, newFragment).commit();

/*                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container_view_seas, SeasFragment.class, null)
                        .setReorderingAllowed(true)
                        .commit();*/

                break;
            }
            case "mammal":
            {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container_view_mammal, ManimalsFragment.class, null)
                        .setReorderingAllowed(true)
                        .commit();
            }
            case "bird":
            {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container_view_bird, BirdsFragment.class, null)
                        .setReorderingAllowed(true)
                        .commit();
            }

        }
        mDrawer.closeDrawers();

    }

}