package com.example.ordersysterm;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.ordersysterm.Adapter.Item.MenuItem;
import com.example.ordersysterm.Adapter.MenuAdapter;
import com.example.ordersysterm.orderDatabase.orderDao;
import com.example.ordersysterm.orderDatabase.orderData;
import com.example.ordersysterm.orderDatabase.orderDatabase;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {

    RecyclerView menuRv;
    List<MenuItem> dataList;
    MenuAdapter menuAdapter;
    MainActivity activity;

    public Home(MainActivity activity) {
        this.activity=activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataList = getDataList();
        menuAdapter=new MenuAdapter(getContext(),dataList,activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);

        menuRv=view.findViewById(R.id.home_menu_rv);

        menuRv.setLayoutManager(new LinearLayoutManager(requireActivity()));
        menuRv.setAdapter(menuAdapter);

        return view;
    }
    private List<MenuItem> getDataList() {
        List<MenuItem> list=new ArrayList<>();
        list.add(new MenuItem("宫保鸡丁", "鸡丁、花生、辣椒等炒制而成，香辣可口", 38));
        list.add(new MenuItem("麻辣香锅", "多种肉类、蔬菜混合烹饪，麻辣鲜香", 45));
        list.add(new MenuItem("北京炸酱面", "面条搭配浓郁的炸酱，回味无穷", 28));
        list.add(new MenuItem("小笼包", "鲜肉或虾仁包裹在薄皮中蒸制而成，鲜汁十足", 25));
        list.add(new MenuItem("红烧排骨", "排骨经过红烧，肉质酥烂，味道鲜美", 48));
        list.add(new MenuItem("干煸四季豆", "四季豆炸至外脆内嫩，香味十足", 32));
        list.add(new MenuItem("酸辣汤", "酸辣可口的汤，里面有肉丝、木耳、豆腐等", 20));
        list.add(new MenuItem("葱爆羊肉", "羊肉炒至外焦里嫩，葱香扑鼻", 50));
        list.add(new MenuItem("宫廷炖鸡", "采用宫廷秘方慢炖而成，滋补养生", 55));
        list.add(new MenuItem("梅菜扣肉", "五花肉和梅干菜一起蒸制，鲜香美味", 42));
        list.add(new MenuItem("鱼香肉丝", "猪肉丝搭配鱼香调味，酸辣可口", 35));
        list.add(new MenuItem("蒜蓉西兰花", "西兰花炒至脆嫩，蒜香四溢", 30));
        list.add(new MenuItem("水煮鱼", "鲜嫩的鱼片搭配麻辣的汤底，火辣爽口", 40));
        list.add(new MenuItem("京酱肉丝", "猪肉丝配以甜面酱，经典美味", 38));
        list.add(new MenuItem("芝麻鸡", "鸡肉裹上芝麻炸至金黄，外脆里嫩", 42));
        list.add(new MenuItem("清蒸鲈鱼", "鲈鱼清蒸，肉质鲜嫩，清香可口", 55));
        list.add(new MenuItem("手撕包菜", "包菜经过快火炒制，保持蔬菜的原汁原味", 25));
        list.add(new MenuItem("蚝油生菜", "生菜拌蚝油，清新爽口", 28));
        list.add(new MenuItem("陈皮牛肉", "牛肉片搭配陈皮炖制而成，香气扑鼻", 48));
        list.add(new MenuItem("叉烧包", "香糯的叉烧包裹着美味的叉烧肉", 22));
        return list;
    }
}