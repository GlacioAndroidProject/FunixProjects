package com.example.lab5.fragments;


import android.os.Bundle;

import android.os.Handler;

import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.lab5.MainActivity;
import com.example.lab5.R;


public class M000SplashFrg extends Fragment {

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        initViews();

        return inflater.inflate(R.layout.m000_frg_splash, container, false);

    }

    private void initViews() {

        new Handler().postDelayed(this::gotoM001Screen, 2000);

    }

    private void gotoM001Screen() {

        ((MainActivity) getActivity()).gotoM001Screen();

    }

}