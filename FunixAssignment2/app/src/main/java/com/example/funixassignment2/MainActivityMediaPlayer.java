package com.example.funixassignment2;

import android.Manifest;

import android.annotation.SuppressLint;

import android.content.pm.PackageManager;

import android.database.Cursor;

import android.media.MediaPlayer;

import android.os.Bundle;

import android.provider.MediaStore;

import android.view.View;

import android.widget.ImageView;

import android.widget.SeekBar;

import android.widget.TextView;

import android.widget.Toast;



import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.recyclerview.widget.RecyclerView;



import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Date;



public class MainActivityMediaPlayer extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private static final int LEVEL_PAUSE = 0;

    private static final int LEVEL_PLAY = 1;

    private static final MediaPlayer player = new MediaPlayer();

    private static final int STATE_IDE = 1;

    private static final int STATE_PLAYING = 2;

    private static final int STATE_PAUSED = 3;

    private final ArrayList<SongEntity> listSong = new ArrayList<>();

    private TextView tvName, tvAlbum, tvTime;

    private SeekBar seekBar;

    private ImageView ivPlay;



    private int index;

    private SongEntity songEntity;

    private Thread thread;

    private int state = STATE_IDE;

    private String totalTime;



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_media_player);
        initViews();
    }
    private void initViews() {

        ivPlay = findViewById(R.id.iv_play);

        ivPlay.setOnClickListener(this);

        findViewById(R.id.iv_back).setOnClickListener(this);

        findViewById(R.id.iv_next).setOnClickListener(this);

        tvName = findViewById(R.id.tv_name);

        tvAlbum = findViewById(R.id.tv_album);

        tvTime = findViewById(R.id.tv_time);

        seekBar = findViewById(R.id.seekbar);

        seekBar.setOnSeekBarChangeListener(this);



        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 101);

            return;

        }

        loadingListSongOffline();

    }



    @Override

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            loadingListSongOffline();

        } else {

            Toast.makeText(this, R.string.txt_alert, Toast.LENGTH_SHORT).show();

            finish();

        }

    }





    @SuppressLint("Range")
    private void loadingListSongOffline() {
        //ContentResolver cho phép truy cập đến tài nguyên của ứng dụng thông qua 1 đường dẫn uri

        Cursor c = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,

                null, null, null, null);

        if (c != null) {

            c.moveToFirst();

            listSong.clear();

            while (!c.isAfterLast()) {

                @SuppressLint("Range") String name = c.getString(c.getColumnIndex(MediaStore.Audio.Media.TITLE));

                @SuppressLint("Range") String path = c.getString(c.getColumnIndex(MediaStore.Audio.Media.DATA));

                String album = "N/A";

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {

                    album = c.getString(c.getColumnIndex(MediaStore.Audio.Media.ALBUM_ARTIST));

                }

                listSong.add(new SongEntity(name, path, album));

                c.moveToNext();

            }

            c.close();

        }
        RecyclerView rv = findViewById(R.id.rv_song);

        rv.setLayoutManager(new LinearLayoutManager(this));

        rv.setAdapter(new MusicAdapter(listSong, this));

        play();

        playPause();

    }



    public void playSong(SongEntity songEntity) {
        index = listSong.indexOf(songEntity);
        this.songEntity = songEntity;
        play();
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_play) {
            playPause();
        } else if (v.getId() == R.id.iv_next) {
            next();
        } else if (v.getId() == R.id.iv_back) {
            back();
        }
    }
    private void back() {

        if (index == 0) {

            index = listSong.size() - 1;

        } else {

            index--;

        }

        play();

    }

    private void next() {
        if (index >= listSong.size()) {
            index = 0;
        } else {
            index++;
        }
        play();
    }
    private void playPause() {

        if (state == STATE_PLAYING && player.isPlaying()) {

            player.pause();

            ivPlay.setImageLevel(LEVEL_PAUSE);

            state = STATE_PAUSED;

        } else if (state == STATE_PAUSED) {

            player.start();

            state = STATE_PLAYING;

            ivPlay.setImageLevel(LEVEL_PLAY);
        } else {
            play();
        }
    }

    private void play() {
        songEntity = listSong.get(index);

        tvName.setText(songEntity.getName());

        tvAlbum.setText(songEntity.getAlbum());

        player.reset();
        try {
            player.setDataSource(songEntity.getPath());

            player.prepare();

            player.start();

            ivPlay.setImageLevel(LEVEL_PLAY);

            state = STATE_PLAYING;

            totalTime = getTime(player.getDuration());

            seekBar.setMax(player.getDuration());

            if (thread == null) {

                startLooping();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void startLooping() {
        thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(200);
                    } catch (Exception e) {
                        return;
                    }
                    runOnUiThread(() -> updateTime());
                }
            }
        };
        thread.start();
    }
    private void updateTime() {

        if (state == STATE_PLAYING || state == STATE_PAUSED) {

            int time = player.getCurrentPosition();

            tvTime.setText(String.format("%s/%s", getTime(time), totalTime));

            seekBar.setProgress(time);

        }

    }
    @SuppressLint("SimpleDateFormat")

    private String getTime(int time) {

        return new SimpleDateFormat("mm:ss").format(new Date(time));

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

        thread.interrupt();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }
    @Override

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override

    public void onStopTrackingTouch(SeekBar seekBar) {

        if (state == STATE_PLAYING || state == STATE_PAUSED) {

            player.seekTo(seekBar.getProgress());

        }

    }

}

/*import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivityMediaPlayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_media_player);

        this.findViewById(R.id.bt_start).setOnClickListener(v -> playSong());
        this.findViewById(R.id.bt_stop).setOnClickListener(v -> stopSong());
    }

    public void playSong() {
        //Tạo Intent để chuẩn bị kích hoạt Service phát nhạc
        Intent myIntent = new Intent(MainActivityMediaPlayer.this, FunixBGService.class);
        //Kích hoạt service
        this.startService(myIntent);
    }

    public void stopSong() {
        //Tạo Intent để chuẩn bị hủy Service phát nhạc
        Intent myIntent = new Intent(MainActivityMediaPlayer.this, FunixBGService.class);
        //Dừng Service phát nhạc
        this.stopService(myIntent);
    }
}*/

/*public class MainActivityMediaPlayer extends AppCompatActivity {

    //Khai báo trình nghe nhạc MediaPlayer
    private MediaPlayer player;
    private ImageView ivPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_media_player);
        initViews();
    }

    private void initViews() {
        //Khởi tạo trình nghe nhạc trỏ đến bài hát trong thư mục raw
        player = MediaPlayer.create(this, R.raw.file_example_mp4_1280);

        ivPlay = findViewById(R.id.iv_play);
        ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Chạy animation hiệu ứng cho button
                v.startAnimation(AnimationUtils.loadAnimation(MainActivityMediaPlayer.this, androidx.appcompat.R.anim.abc_fade_in));
                playOrPause();
            }
        });
    }

    private void playOrPause() {
        //Nếu trình nghe nhạc đang phát thì tạm thời dừng lại
        if (player.isPlaying()) {
            player.pause();
            //Đổi ảnh button sang trạng thái pause
            ivPlay.setImageResource(R.drawable.ic_pause);
        }else{
            player.start();
            //Đổi ảnh button sang trạng thái pause
            ivPlay.setImageResource(R.drawable.ic_play);
        }
    }
}*/
/*public class MainActivityMediaPlayer extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private VideoView videoView;
    private int position = 0;
    private MediaController mediaController;

    // Phát 1 video trong thư mục RAW.
    public static void playRawVideo(Context context, VideoView videoView, int resVideoId) {
        try {
            Uri uri = Uri.parse("android.resource://" + context.getPackageName() + "/" + resVideoId);
            Log.i(TAG, "Video URI: " + uri);
            videoView.setVideoURI(uri);
            videoView.requestFocus();

        } catch (Exception e) {
            Log.e(TAG, "Error Play Raw Video: " + e.getMessage());
            Toast.makeText(context, "Error Play Raw Video: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    //Phat video online
    public static void playURLVideo(Context context, VideoView videoView, String videoURL) {
        try {
            Log.i(TAG, "Video URL: " + videoURL);

            Uri uri = Uri.parse(videoURL);
            videoView.setVideoURI(uri);
            videoView.requestFocus();
        } catch (Exception e) {
            Log.e(TAG, "Error Play URL Video: " + e.getMessage());
            Toast.makeText(context, "Error Play URL Video: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_media_player);

        this.videoView = findViewById(R.id.videoView);
        Button buttonURL = findViewById(R.id.button_url);
        Button buttonRaw = findViewById(R.id.button_raw);

        // Thiết lập trình điều khiển Media
        if (this.mediaController == null) {
            this.mediaController = new MediaController(this);

            //Thiết lập video view vào trong trình điều khiển
            this.mediaController.setAnchorView(videoView);

            // Thiết lập trình điều khiển cho video View
            this.videoView.setMediaController(mediaController);
        }


        // Khi video view đã sẵn sàng phát
        this.videoView.setOnPreparedListener(mediaPlayer -> {
            videoView.seekTo(position);
            if (position == 0) {
                videoView.start();
            }
            // Quy định thay đổi kích thước cho VideoView khi màn hình thay đổi (dọc-ngang)
            mediaPlayer.setOnVideoSizeChangedListener((mp, width, height) -> {
                mediaController.setAnchorView(videoView);
            });
        });

        //Phát video trong thư mục raw
        buttonRaw.setOnClickListener(v -> {
            playRawVideo(MainActivityMediaPlayer.this, videoView, R.raw.file_example_mp4_1280);
        });

        //Phát video online
        buttonURL.setOnClickListener(v -> {
            String videoURL = "https://file-examples-com.github.io/uploads/2017/04/file_example_MP4_480_1_5MG.mp4";
            playURLVideo(MainActivityMediaPlayer.this, videoView, videoURL);
        });
    }

    //Khi có sự thay đổi giao diện, trạng thái thì dừng video
    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("CurrentPosition", videoView.getCurrentPosition());
        videoView.pause();
    }

    // Khôi phục video
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        position = savedInstanceState.getInt("CurrentPosition");
        videoView.seekTo(position);
    }
}*/
