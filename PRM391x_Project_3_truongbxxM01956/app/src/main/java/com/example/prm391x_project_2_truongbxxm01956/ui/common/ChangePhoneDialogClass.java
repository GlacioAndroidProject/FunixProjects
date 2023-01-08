package com.example.prm391x_project_2_truongbxxm01956.ui.common;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.prm391x_project_2_truongbxxm01956.R;
import com.example.prm391x_project_2_truongbxxm01956.ui.common.models.Aniamls;

public class ChangePhoneDialogClass extends Dialog {

    public AnimalDetail activity;
    public Dialog dialog;
    public Button btnSave, btnCancel;
    public ImageView imgAvata;
    public EditText editTextPhone;
    Aniamls aniamlsInfo;
    public ChangePhoneDialogClass(AnimalDetail a, Aniamls aniamlsInfo) {
        super(a);
        // TODO Auto-generated constructor stub
        this.activity = a;
        this.aniamlsInfo = aniamlsInfo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        imgAvata = (ImageView) findViewById(R.id.imgAvata);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextPhone.setText(aniamlsInfo.getPhone());
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneTemp = editTextPhone.getText().toString();
                activity.onSavePhone(phoneTemp);
                dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        imgAvata.setImageResource(aniamlsInfo.getPhoto());

    }

}