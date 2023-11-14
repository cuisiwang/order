package com.example.ordersysterm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageButton;

import com.example.ordersysterm.Adapter.FragmentAdapter;
import com.example.ordersysterm.Adapter.Item.MenuItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabLayout tab;
    ViewPager2 vp;
    ImageButton cart;
    DrawerLayout drawerLayout;
    List<MenuItem> cartDataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tab=findViewById(R.id.tabLayout);
        vp=findViewById(R.id.viewPager);
        cart=findViewById(R.id.mainBottomCart);
        drawerLayout=findViewById(R.id.drawerLayout);

        cartDataList=new ArrayList<>();

        cart.setOnClickListener(v -> {
            drawerLayout.openDrawer(GravityCompat.END);
        });
        final String[] bottomMenu = new String[]{"首页", "我的订单", "订单统计"};

        vp.setAdapter(new FragmentAdapter(this,tab,cartDataList));
        new TabLayoutMediator(tab,vp,(tab1,position) -> tab1.setText(bottomMenu[position])).attach();

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode== KeyEvent.KEYCODE_BACK) {//当购物车打开时返回，关闭购物车而不是关闭软件
            if(drawerLayout.isDrawerOpen(GravityCompat.END)){
                drawerLayout.closeDrawer(GravityCompat.END);
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


}