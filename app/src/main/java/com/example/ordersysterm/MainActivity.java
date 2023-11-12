package com.example.ordersysterm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.ordersysterm.Adapter.fragmentAdp;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout tab=findViewById(R.id.tabLayout);
        ViewPager2 vp=findViewById(R.id.viewPager);
        final String[] bottomMenu = new String[]{"首页", "我的订单", "订单统计"};

        vp.setAdapter(new fragmentAdp(this,tab));
        new TabLayoutMediator(tab,vp,(tab1,position)->tab1.setText(bottomMenu[position])).attach();

    }
}