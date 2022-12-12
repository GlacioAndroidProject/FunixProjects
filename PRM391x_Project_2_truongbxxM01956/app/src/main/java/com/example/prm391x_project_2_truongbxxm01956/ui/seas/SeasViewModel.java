package com.example.prm391x_project_2_truongbxxm01956.ui.seas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SeasViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SeasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}