package com.example.funixassignment2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Fragment1 extends Fragment implements View.OnClickListener{
    private EditText edtName, edtAge;
    private Button btnSend;
    private DemoFragmentInterface listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity_Fragment)
            this.listener = (DemoFragmentInterface) context;
        else
            throw new RuntimeException(context.toString() + "must implement onViewSelected!");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_1,container,false);
        edtName = view.findViewById(R.id.edtName);
        edtAge = view.findViewById(R.id.edtAge);
        btnSend = view.findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnSend:
                String name = edtName.getText().toString().trim();
                String age = edtAge.getText().toString().trim();

                if (!name.equals("") && !age.equals("")) {
                    listener.sendData(name, age);
                } else if (name.equals("")) {
                    edtName.requestFocus();
                } else {
                    edtAge.requestFocus();
                }
                break;
        }
    }
}