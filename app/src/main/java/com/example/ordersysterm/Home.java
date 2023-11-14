package com.example.ordersysterm;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    Button bt1;
    orderDao orderDao;
    RecyclerView menuRv;
    List<MenuItem> dataList;
    MenuAdapter menuAdapter;
    List<MenuItem> cartDataList;

    public Home(List<MenuItem> cartDataList) {
        this.cartDataList=cartDataList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderDao = orderDatabase.Companion.getDatabase(requireActivity()).orderDao();
        dataList = getDataList();
        menuAdapter=new MenuAdapter(getContext(),dataList,cartDataList);
    }

    private List<MenuItem> getDataList() {
        List<MenuItem> list=new ArrayList<>();
        return list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);

        menuRv=view.findViewById(R.id.home_menu_rv);

        menuRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        menuRv.setAdapter(menuAdapter);
//        bt1=view.findViewById(R.id.bt1);//暂时不存在
//        bt1.setOnClickListener(v -> {
//            new Thread(()->{
//                orderDao.insertOrder(new orderData("asdqw",111,"2023-10-18"));
//            }).start();
//        });

//        FloatingActionButton fab=view.findViewById(R.id.home_menu_FAB);
//        DrawerLayout drawerLayout = requireActivity().findViewById(R.id.drawerLayout);
//        fab.setOnClickListener(v -> {
//            drawerLayout.openDrawer(GravityCompat.END);
//        });
        return view;
    }


}