package com.example.prm391x_project_2_truongbxxm01956.ui.birds;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm391x_project_2_truongbxxm01956.R;
import com.example.prm391x_project_2_truongbxxm01956.databinding.FragmentBirdsBinding;
import com.example.prm391x_project_2_truongbxxm01956.ui.common.adapters.RecyclerViewAdapter;
import com.example.prm391x_project_2_truongbxxm01956.ui.common.models.Aniamls;
import com.example.prm391x_project_2_truongbxxm01956.ui.common.utils.Functions;

import java.util.ArrayList;

public class BirdsFragment extends Fragment {

    private FragmentBirdsBinding binding;
    private ArrayList<Aniamls> aniamls;
    private RecyclerView gallery;
    private RecyclerViewAdapter recyclerViewAdapter;
    private String[] names={
            //"animal",
            "eagle","falcon","hawk","parrot",
            "peacock","penguin","raven","sparrow","woodpecker",
    };
    private int[] description ={
            //"animal",
            R.string.ant,R.string.big_foot,R.string.bird,R.string.cat,
            R.string.dog,R.string.dolphin,R.string.dragon_fly,R.string.elephant,R.string.fish,
    };
    private int[] photos={//R.drawable.bg_animal,
            R.drawable.ic_eagle,R.drawable.ic_falcon,R.drawable.ic_hawk,R.drawable.ic_parrot,R.drawable.ic_peacock,
            R.drawable.ic_peguin,R.drawable.ic_raven,R.drawable.ic_sparrow,R.drawable.ic_woodpecker
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentBirdsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        aniamls =new ArrayList<>();
        gallery=binding.gallery;
        gallery.setLayoutManager(new GridLayoutManager(this.getActivity(), 2));
        getDatas();
        recyclerViewAdapter =new RecyclerViewAdapter(aniamls,this.getActivity());
        gallery.setAdapter(recyclerViewAdapter);
        return root;

    }
    // getting all the datas
    private void getDatas(){
        for(int count=0;count<names.length;count++){
            aniamls.add(new Aniamls(names[count], getString(description[count]),photos[count], false));
        }
    }
    private void updateDatas(){
        for(int count = 0; count< aniamls.size(); count++){
            boolean isFavorite = Functions.getSharedPreferencesByBooleanValue((this.getActivity()), aniamls.get(count).getName());
            aniamls.get(count).setFavorite(isFavorite);
        }
        recyclerViewAdapter =new RecyclerViewAdapter(aniamls,this.getActivity());
        gallery.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateDatas();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}