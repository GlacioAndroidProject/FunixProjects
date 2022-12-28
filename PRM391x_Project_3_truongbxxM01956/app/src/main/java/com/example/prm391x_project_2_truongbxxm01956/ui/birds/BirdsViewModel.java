package com.example.prm391x_project_2_truongbxxm01956.ui.birds;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BirdsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public BirdsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}