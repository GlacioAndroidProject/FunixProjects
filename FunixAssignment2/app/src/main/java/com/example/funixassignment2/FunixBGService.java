package com.example.funixassignment2;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class FunixBGService extends Service {
    private MediaPlayer mediaPlayer;

    // Trả về thực thế Service để giao tiếp ở một đối tượng khác
    @Override
    public IBinder onBind(Intent intent) {
        // Phương thức này được gọi khi bindService
        // Do trong ví dụ này, chúng ta chọn startService
        // nên phương thức này không có tác dụng gì
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        // Tạo trình nghe nhạc để chạy nhạc
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.cau_hen_cau_the);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Phát nhạc
        mediaPlayer.start();
        return START_STICKY;
    }

    // Destroy
    @Override
    public void onDestroy() {
        // Giải phóng tài nguyên và dừng nhạc
        mediaPlayer.release();
        super.onDestroy();
    }
}