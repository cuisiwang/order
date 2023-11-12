package com.example.ordersysterm;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.ordersysterm.orderDatabase.orderDao;
import com.example.ordersysterm.orderDatabase.orderData;
import com.example.ordersysterm.orderDatabase.orderDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {

    Button bt1;

    orderDao orderDao;
    public Home() {
        // Required empty public constructor
    }

    public static Home newInstance() {
        Home fragment = new Home();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderDao = orderDatabase.Companion.getDatabase(requireActivity()).orderDao();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        bt1=view.findViewById(R.id.bt1);
        bt1.setOnClickListener(v -> {
            new Thread(()->{
                orderDao.insertOrder(new orderData("asdqw",111,"2023-10-18"));
            }).start();
        });
        return view;
    }
}