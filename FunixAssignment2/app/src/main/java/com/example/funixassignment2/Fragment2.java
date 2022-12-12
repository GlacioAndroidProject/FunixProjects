package com.example.funixassignment2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment2 extends Fragment {
    private TextView tvName, tvAge;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_2,container,false);
        tvName = (TextView) view.findViewById(R.id.tvName);
        tvAge = (TextView) view.findViewById(R.id.tvAge);
        return view;
    }
    public void showInfor(String name, String age){
        tvName.setText(name);
        tvAge.setText(age);
    }
}