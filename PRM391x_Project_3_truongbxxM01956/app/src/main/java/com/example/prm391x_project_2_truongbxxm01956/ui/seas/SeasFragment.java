package com.example.prm391x_project_2_truongbxxm01956.ui.seas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.prm391x_project_2_truongbxxm01956.R;
import com.example.prm391x_project_2_truongbxxm01956.databinding.FragmentSeasBinding;
import com.example.prm391x_project_2_truongbxxm01956.ui.common.adapters.RecyclerViewAdapter;
import com.example.prm391x_project_2_truongbxxm01956.ui.common.models.Aniamls;
import com.example.prm391x_project_2_truongbxxm01956.ui.common.utils.Functions;

import java.util.ArrayList;

public class SeasFragment extends Fragment {

    private FragmentSeasBinding binding;
    private ArrayList<Aniamls> aniamls;
    private RecyclerView gallery;
    private RecyclerViewAdapter recyclerViewAdapter;
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

        aniamls =new ArrayList<>();
        gallery=binding.gallery;
        getDatas();
        gallery.setLayoutManager(new GridLayoutManager(this.getActivity(), 2));
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