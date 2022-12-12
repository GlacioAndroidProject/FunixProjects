package com.example.funixassignment2;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class M002StoryFrg extends Fragment {
    private static final String TAG = M002StoryFrg.class.getName();
    private Context mContext;
    private String topicName;
    private ArrayList<StoryEntity> listStory;

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.m002_frg_story, container, false);
        initViews(rootView);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    private void initViews(View v) {
        try {
            v.findViewById(R.id.iv_back).setVisibility(View.VISIBLE);
            v.findViewById(R.id.iv_back).setOnClickListener(v1 -> backToM001Screen());
            ((TextView) v.findViewById(R.id.tv_name)).setText(topicName);
            loadListStory();
            RecyclerView rvStory = v.findViewById(R.id.rv_story);
            rvStory.setLayoutManager(new LinearLayoutManager(mContext));
            rvStory.setAdapter(new StoryAdapter(listStory, mContext));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void backToM001Screen() {
        ((MainActivity) getActivity()).backToM001Screen();
    }

    private void loadListStory() throws Exception {
        InputStream in = mContext.getAssets().open("story/" + topicName + ".txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
        String str;
        String name = null;
        StringBuilder content = new StringBuilder();
        listStory = new ArrayList<>();
        while ((str = br.readLine()) != null) {
            if (str.isEmpty()) continue;
            if (name == null) {
                name = str;
            } else if (!str.startsWith("','0');")) {
                content.append(str).append("\n");
            } else {
                listStory.add(new StoryEntity(topicName, name, content.toString()));
                name = null;
                content = new StringBuilder();
            }
        }
        br.close();
        Log.i(TAG, "Story list = " + listStory.size());
    }
}