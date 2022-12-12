package com.example.funixassignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class AnimalAdapter extends PagerAdapter {
    private final List<Integer> listData;
    private final Context context;

    public AnimalAdapter(List<Integer> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }
    //Là hành vi khai báo số lượng item view (page) sẽ được sinh ra
    @Override
    public int getCount() {
        return listData.size();
    }

    @NonNull
    @Override
    //Là hành vi tiến hành ánh xạ 1 item view từ thư mục layout vào trong chương trình
    //Sau đó đổ dữ liệu ứng với data tương ứng vào
    public Object instantiateItem(@NonNull ViewGroup viewPager, int position) {
        //Ánh xạ item view vào trong môi trường code
        View v = LayoutInflater.from(context).inflate(R.layout.item_view, viewPager, false);
        //Ánh xạ ImageView ra dựa vào id
        ImageView ivAnimal = v.findViewById(R.id.iv_animal);
        //Đổ dữ liệu vào ImagView
        ivAnimal.setImageResource(listData.get(position));
        //Đưa vào trong ViewPager
        viewPager.addView(v);
        return v;
    }

    @Override
    //Là hành vi khi người dùng vuốt sang trái-phải để hiển thị page mới, khi đó
    //Nếu người dùng vuốt chưa được 1 nửa và nhả tay ra, Page cũ sẽ được giữ lại hiển thị trên màn hình
    //Cơ chế so sánh giữa View mới và view cũ để đảm bảo thao tác của người dùng không bị lỗi
    public boolean isViewFromObject(@NonNull View view, @NonNull Object oldView) {
        return view.equals(oldView);
    }

    @Override
    //Khi 1 Page không còn được hiển thị trên màn hình, nó sẽ bị ViewPager destroy
    public void destroyItem(@NonNull ViewGroup viewPager, int position, @NonNull Object object) {
        viewPager.removeView((View) object);
    }
}