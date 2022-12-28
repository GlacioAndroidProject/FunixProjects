package com.example.prm391x_project_2_truongbxxm01956.ui.seas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.prm391x_project_2_truongbxxm01956.R;
import com.example.prm391x_project_2_truongbxxm01956.databinding.FragmentSeasBinding;
import com.example.prm391x_project_2_truongbxxm01956.ui.common.adapters.GrilViewAdapter;
import com.example.prm391x_project_2_truongbxxm01956.ui.common.models.Aniamls;
import com.example.prm391x_project_2_truongbxxm01956.ui.common.utils.Functions;

import java.util.ArrayList;

public class SeasFragment extends Fragment {

    private FragmentSeasBinding binding;
    private ArrayList<Aniamls> users;
    private GridView gallery;
    private GrilViewAdapter grilViewAdapter;
    private String[] names={
            //"animal",
            "crab","dolphin","jellyfish","octopus", "red snapper",
            "shark","squid","swordfish","whale", "dolphin",
    };
    private int[] description ={
            R.string.ant,R.string.big_foot,R.string.bird,R.string.cat,R.string.dog,
            R.string.dolphin,R.string.dragon_fly,R.string.elephant,R.string.fish,R.string.frog,
    };
    private int[] photos={
            R.drawable.ic_crab,R.drawable.ic_dolphin,R.drawable.ic_jellyfish,R.drawable.ic_octopus,R.drawable.ic_red_snapper,
            R.drawable.ic_shark,R.drawable.ic_squid,R.drawable.ic_swordfish,R.drawable.ic_whale,R.drawable.dolphin
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSeasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        users=new ArrayList<>();
        gallery=binding.gallery;
        getDatas();
        grilViewAdapter =new GrilViewAdapter(users,this.getActivity());
        gallery.setAdapter(grilViewAdapter);

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
            boolean isFavorite = Functions.getSharedPreferencesByBooleanValue((this.getActivity()),users.get(count).getName());
            users.get(count).setFavorite(isFavorite);
        }
        grilViewAdapter =new GrilViewAdapter(users,this.getActivity());
        gallery.setAdapter(grilViewAdapter);
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