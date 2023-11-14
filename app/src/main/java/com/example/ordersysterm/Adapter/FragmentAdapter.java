package com.example.ordersysterm.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ordersysterm.Adapter.Item.MenuItem;
import com.example.ordersysterm.Home;
import com.example.ordersysterm.MainActivity;
import com.example.ordersysterm.MyOrder;
import com.example.ordersysterm.OrderStatics;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class FragmentAdapter extends FragmentStateAdapter {

    TabLayout tab;
    MainActivity activity;
    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity, TabLayout tabLayout,MainActivity activity) {
        super(fragmentActivity);
        tab=tabLayout;
        this.activity=activity;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return switch (position) {
            case 1 -> new MyOrder(tab);
            case 2 -> new OrderStatics();
            default -> new Home(activity);
        };
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
