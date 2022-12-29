package com.example.prm391x_project_2_truongbxxm01956;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.prm391x_project_2_truongbxxm01956.ui.common.utils.Functions;

import java.io.IOException;
import java.io.InputStream;

public class CallReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {

        try {
            // TELEPHONY MANAGER class object to register one listner
            TelephonyManager tmgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

            //Create Listner
            MyPhoneStateListener PhoneListener = new MyPhoneStateListener(context);

            // Register listener for LISTEN_CALL_STATE
            tmgr.listen(PhoneListener, PhoneStateListener.LISTEN_CALL_STATE);

        } catch (Exception e) {
            Log.e("Phone Receive Error", " " + e);
        }

    }

    private class MyPhoneStateListener extends PhoneStateListener {
        Context mContext;
        MyPhoneStateListener(Context context)
        {
            mContext = context;
        }

        public void onCallStateChanged(int state, String incomingNumber) {
            String msg = "";
            switch (state)
            {
                case 0:
                {
                     msg = "Phone was cancel : "+ incomingNumber;
                    break;
                }
                case 1:
                {
                     msg = "New Phone Call Event. Incomming Number : "+ incomingNumber;
                    showImageFromAssetFolder(getImageDrawableFromPhoneNumber(incomingNumber));
                    break;
                }
                case 2:
                {
                     msg = "Phone was accept : "+ incomingNumber;
                    break;
                }
            }
        }
        public int getImageDrawableFromPhoneNumber(String phoneNumber){
            int drawableId = R.drawable.cat;
            String drawableStr = Functions.getSharedPreferencesByStringValue(mContext, phoneNumber);
            if(!drawableStr.isEmpty())
            {
                drawableId = Integer.parseInt(drawableStr);
            }
            return drawableId;
        }
        public void showImageFromAssetFolder(int drawableId){
            Toast toast = new Toast(mContext);
            ImageView view = new ImageView(mContext);
            view.setImageResource(drawableId);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();

        }
    }

}
