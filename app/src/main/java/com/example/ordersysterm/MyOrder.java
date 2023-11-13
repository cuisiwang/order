package com.example.ordersysterm;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ordersysterm.Adapter.orderAdapter;

import com.example.ordersysterm.orderDatabase.*;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class MyOrder extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton fab;
    TabLayout tab;  //通过得到TabLayout实例，在点击添加按钮时触发选择TabLayout中首页对应的tab，实现跳转（权宜之计！）
    List<orderData> dataList;
    orderAdapter orderAdapter;
    orderDao orderDao;
    int flag=0;
    public MyOrder(TabLayout tabLayout) {
        tab=tabLayout;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderDao= orderDatabase.Companion.getDatabase(requireActivity()).orderDao();
        dataList = new ArrayList<>();
        orderAdapter=new orderAdapter(getContext(),dataList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_my_order, container, false);

        recyclerView=view.findViewById(R.id.recyclerview);
        fab=view.findViewById(R.id.MyOrderFAB);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(orderAdapter);

        fab.setOnClickListener(v -> {
            Toast.makeText(getContext(), "请到主页下单！", Toast.LENGTH_SHORT).show();
            tab.selectTab(tab.getTabAt(0));
        });
        return view;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onResume() {
        super.onResume();
        dataList.clear();
        new Thread(()->{
            dataList.addAll(orderDao.selectAll());
            flag=1;
        }).start();
        while(flag==0){
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        flag=0;
        orderAdapter.notifyDataSetChanged();
    }
}