package com.example.funixassignment2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity_SMS extends AppCompatActivity {
    private static final int MY_PERMISSION_REQUEST_CODE_SEND_SMS = 1;
    private static final String TAG = MainActivity.class.getName();

    private EditText editTextPhoneNumber;
    private EditText editTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sms);
        this.editTextPhoneNumber = this.findViewById(R.id.editText_phoneNumber);
        this.editTextMessage = this.findViewById(R.id.editText_message);
        Button buttonSend = this.findViewById(R.id.button_send);

        //Kiểm tra quyền người dùng
        buttonSend.setOnClickListener(v -> askPermissionAndSendSMS());
    }

    private void askPermissionAndSendSMS() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) { // 23
            int sendSmsPermisson = ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.SEND_SMS);
            if (sendSmsPermisson != PackageManager.PERMISSION_GRANTED) {
                this.requestPermissions(
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSION_REQUEST_CODE_SEND_SMS
                );
                return;
            }
        }
        this.sendSMSBySmsManager();
    }
    private void sendSMSBySmsManager() {
        String phoneNumber = this.editTextPhoneNumber.getText().toString();
        String message = this.editTextMessage.getText().toString();
        try {
            // Tạo đối tượng SmsManager
            SmsManager smsManager = SmsManager.getDefault();
            // Tiến hành gửi tin nhắn đi
            // Trong phương thức sendTextMessage có các thông số:
            // destinationAddress: Số điện thoại nhận tin nhắn
            // scAddress: Địa chỉ xử lý tin nhắn trung tâm: truyền null thì sẽ mặc định dùng SMSC (Đây là thông số không quan trọng)
            // text: Nội dung tin nhắn gửi đi
            // sentIntent, deliveryIntent: Là 2 tham số để nhận phản hồi khi tin nhắn được gửi thành công đến SĐT người nhận
            // (Đây là thông số không quan trọng)
            smsManager.sendTextMessage(phoneNumber,
                    null,
                    message,
                    null,
                    null);
            Log.i(TAG, "Tin nhắn đã được gửi đi");
            Toast.makeText(getApplicationContext(), "Tin nhắn đã được gửi đi",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Log.e(TAG, "Gửi tin nhắn thất bại", ex);
            Toast.makeText(getApplicationContext(), "Gửi tin nhắn thất bại... " + ex.getMessage(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }
    // When you have the request results
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSION_REQUEST_CODE_SEND_SMS) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, "Quyền được cấp!");
                Toast.makeText(this, "Quyền được cấp!", Toast.LENGTH_LONG).show();
                this.sendSMSBySmsManager();
            } else {
                Log.i(TAG, "Quyền bị từ chối!");
                Toast.makeText(this, "Quyền bị từ chối!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
/*
public class MainActivity_SMS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sms);
    }
}*/
