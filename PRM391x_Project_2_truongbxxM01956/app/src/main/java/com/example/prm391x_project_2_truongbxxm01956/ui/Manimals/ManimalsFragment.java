package com.example.prm391x_project_2_truongbxxm01956.ui.Manimals;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.prm391x_project_2_truongbxxm01956.R;
import com.example.prm391x_project_2_truongbxxm01956.databinding.FragmentHomeBinding;
import com.example.prm391x_project_2_truongbxxm01956.ui.common.adapters.GrilViewAdapter;
import com.example.prm391x_project_2_truongbxxm01956.ui.common.models.Aniamls;
import com.example.prm391x_project_2_truongbxxm01956.ui.common.utils.Functions;

import java.util.ArrayList;

public class ManimalsFragment extends Fragment {

    private FragmentHomeBinding binding;

    private ArrayList<Aniamls> users;
    private GridView gallery;
    private GrilViewAdapter grilViewAdapter;
    private String[] names={
            //"animal",
            "ant","big foot","bird","cat",
            "dog","dolphin","dragon fly","elephant","fish",
            "frog","jelly fish","kbugbuster","lion","nessy",
            "penguin","pig","staroffice","turtle","xfiles monster",
            "ant","big foot","bird","cat",
            "dog","dolphin","dragon fly","elephant","fish",
            "frog","jelly fish","kbugbuster","lion","nessy",
            "penguin","pig","staroffice","turtle","xfiles monster",
    };
    private int[] description ={
            //"animal",
            R.string.ant,R.string.big_foot,R.string.bird,R.string.cat,
            R.string.dog,R.string.dolphin,R.string.dragon_fly,R.string.elephant,R.string.fish,
            R.string.frog,R.string.elly_fish,R.string.kbugbuster,R.string.lion,R.string.nessy,
            R.string.penguin,R.string.pig,R.string.staroffice,R.string.turtle,R.string.xfiles_monster,
            R.string.ant,R.string.big_foot,R.string.bird,R.string.cat,
            R.string.dog,R.string.dolphin,R.string.dragon_fly,R.string.elephant,R.string.fish,
            R.string.frog,R.string.elly_fish,R.string.kbugbuster,R.string.lion,R.string.nessy,
            R.string.penguin,R.string.pig,R.string.staroffice, R.string.turtle,R.string.xfiles_monster
    };
    private int[] photos={//R.drawable.bg_animal,
            R.drawable.ant,R.drawable.big_foot,R.drawable.bird,R.drawable.cat,R.drawable.dog,
            R.drawable.dolphin,R.drawable.dragon_fly,R.drawable.elephant,R.drawable.fish,R.drawable.frog,
            R.drawable.jelly_fish,R.drawable.kbugbuster,R.drawable.lion,R.drawable.nessy,R.drawable.penguin,
            R.drawable.pig,R.drawable.staroffice,R.drawable.turtle,R.drawable.xfiles_monster,

            R.drawable.ant,R.drawable.big_foot,R.drawable.bird,R.drawable.cat,R.drawable.dog,
            R.drawable.dolphin,R.drawable.dragon_fly,R.drawable.elephant,R.drawable.fish,R.drawable.frog,
            R.drawable.jelly_fish,R.drawable.kbugbuster,R.drawable.lion,R.drawable.nessy,R.drawable.penguin,
            R.drawable.pig,R.drawable.staroffice,R.drawable.turtle,R.drawable.xfiles_monster,
    };
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
/*        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);*/

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        users=new ArrayList<>();
        gallery=binding.gallery;
        getDatas();
        grilViewAdapter =new GrilViewAdapter(users,this.getActivity());
        gallery.setAdapter(grilViewAdapter);
/*

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);*/
        return root;
    }

    // getting all the datas
    private void getDatas(){
        for(int count=0;count<names.length;count++){
            users.add(new Aniamls(names[count], getString(description[count]),photos[count], false));
        }
    }
    private void updateDatas(){
        for(int count=0;count<users.size();count++){
            boolean isFavorite = Functions.getsetSharedPreferences((this.getActivity()),users.get(count).getName());
            users.get(count).setFavorite(isFavorite);
        }
        grilViewAdapter =new GrilViewAdapter(users,this.getActivity());
        gallery.setAdapter(grilViewAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    @Override
    public void onResume() {
        super.onResume();
        updateDatas();
    }
}