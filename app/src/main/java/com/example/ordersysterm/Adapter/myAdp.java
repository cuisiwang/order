package com.example.ordersysterm.Adapter;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ordersysterm.Home;
import com.example.ordersysterm.MyOrder;
import com.example.ordersysterm.OrderStatics;
import com.google.android.material.tabs.TabLayout;

public class myAdp extends FragmentStateAdapter {

    TabLayout tab;
    public myAdp(@NonNull FragmentActivity fragmentActivity, TabLayout tabLayout) {
        super(fragmentActivity);
        tab=tabLayout;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Home();
            case 1:
                return new MyOrder(tab);
            case 2:
                return new OrderStatics();
        }
        return new Home();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
