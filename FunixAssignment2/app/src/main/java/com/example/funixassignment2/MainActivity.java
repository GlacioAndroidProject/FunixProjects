package com.example.funixassignment2;
import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;



import java.util.ArrayList;

import java.util.List;



public class MainActivity extends AppCompatActivity {
    private String topicName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        showFrg(new M000SplashFrg());

    }
    private void showFrg(Fragment frg) {

        getSupportFragmentManager().beginTransaction().replace(R.id.ln_main, frg, null).commit();

    }
    public void gotoM001Screen() {

        getSupportFragmentManager().beginTransaction().replace(R.id.ln_main, new M001TopicFrg(), null).commit();

    }
    public void gotoM002Screen(String topicName) {

        this.topicName = topicName;

        M002StoryFrg frg = new M002StoryFrg();

        frg.setTopicName(topicName);

        showFrg(frg);

    }
    public void backToM001Screen() {

        gotoM001Screen();

    }
    public void gotoM003Screen(ArrayList<StoryEntity> listStory, StoryEntity story) {

        M003DetailStoryFrg frg = new M003DetailStoryFrg();

        frg.setData(topicName, listStory, story);

        showFrg(frg);
    }

}

/*import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String topicName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showFrg(new M000SplashFrg());
    }

    private void showFrg(Fragment frg) {
        getSupportFragmentManager().beginTransaction().replace(R.id.ln_main, frg, null).commit();
    }

    public void gotoM001Screen() {
        getSupportFragmentManager().beginTransaction().replace(R.id.ln_main, new M001TopicFrg(), null).commit();
    }

    public void gotoM002Screen(String topicName) {

    }

    public void backToM001Screen() {
        gotoM001Screen();
    }

    public void gotoM003Screen(ArrayList<StoryEntity> listStory, StoryEntity story) {

    }
}*/
/*import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String topicName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showFrg(new M000SplashFrg());
    }

    private void showFrg(Fragment frg) {
        getSupportFragmentManager().beginTransaction().replace(R.id.ln_main, frg, null).commit();
    }

    public void gotoM001Screen() {
        getSupportFragmentManager().beginTransaction().replace(R.id.ln_main, new M001TopicFrg(), null).commit();
    }

    public void gotoM002Screen(String topicName) {

    }

    public void backToM001Screen() {
        gotoM001Screen();
    }

    public void gotoM003Screen(ArrayList<StoryEntity> listStory, StoryEntity story) {

    }
}*/
/*import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String topicName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showFrg(new M000SplashFrg());
    }

    private void showFrg(Fragment frg) {
        getSupportFragmentManager().beginTransaction().replace(R.id.ln_main, frg, null).commit();
    }

    public void gotoM001Screen() {

    }

    public void gotoM002Screen(String topicName) {

    }

    public void backToM001Screen() {
        gotoM001Screen();
    }

    public void gotoM003Screen(ArrayList<StoryEntity> listStory, StoryEntity story) {

    }
}*/
/*
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}*/

/*
import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        findViewById(R.id.fr_mom).setOnClickListener(this);
        findViewById(R.id.fr_dad).setOnClickListener(this);
        findViewById(R.id.fr_crush).setOnClickListener(this);
        findViewById(R.id.fr_best_friend).setOnClickListener(this);
       // findViewById(R.id.iv_dialer).setOnClickListener(this);
    }

    @Override

    public void onClick(View v) {
        v.startAnimation(AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_popup_enter));
        if (v instanceof FrameLayout) {
            processCall((String) v.getTag());
            return;
        }
        //Hiển thị dialog thông báo
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("Thông báo");
        alert.setMessage("Mở màn hình quay số?");
        alert.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gotoDialPad();
            }
        });
        gotoDialPad();
    }

    private void gotoDialPad() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        startActivity(intent);
    }


    private void processCall(String phone) {
        if (checkSelfPermission(Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 101);
            Toast.makeText(this, "Hãy thực hiện lại sau khi cấp quyền!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel: " + phone));
        startActivity(intent);
    }
}*/

/*
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.viewpager.widget.ViewPager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        //Khởi tạo list data
        List<Integer> listData = new ArrayList<>();
        listData.add(R.drawable.face_smile_big);
        listData.add(R.drawable.angry);
        listData.add(R.drawable.confuse);

        //Khởi tạo adapter
        AnimalAdapter adapter = new AnimalAdapter(listData, this);
        ViewPager vpAnimal = findViewById(R.id.vp_animal);
        vpAnimal.setAdapter(adapter);
    }

    private void createNotificationChannel() {
        // Tạo một NotificationChannel, dành cho API 26+
        // vì NotificationChannel class là đối tượng mới và chỉ hỗ trợ cho các phiên bản Android từ 26 trở lên
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "name_channel_demo";
            String description = "This is demo channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("channel_id_demo", name, importance);
            channel.setDescription(description);
        // Đăng ký channel với hệ thống;
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void onCreateNotification(View v) {
        createNotificationChannel();
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "channel_id_demo")
                .setSmallIcon(R.drawable.angry)
                .setContentTitle("Thông báo của tôi")
                .setContentText("Nội dung ngắn gọn thông báo")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Nội dung chi tiết của thông báo (Có thể nhiều dòng)"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        //notificationId là định danh duy nhất cho mỗi thông báo
        //Do đó nên quy định 1 số nguyên duy nhất cho notificationId
        //Ví dụ: 7625
        notificationManager.notify(7625, mBuilder.build());
    }
}*/
