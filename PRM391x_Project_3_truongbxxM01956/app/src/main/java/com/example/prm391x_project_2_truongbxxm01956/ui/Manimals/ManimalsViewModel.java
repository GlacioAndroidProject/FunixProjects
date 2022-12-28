package com.example.prm391x_project_2_truongbxxm01956.ui.Manimals;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ManimalsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ManimalsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}