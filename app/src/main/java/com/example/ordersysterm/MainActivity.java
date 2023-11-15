package com.example.ordersysterm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ordersysterm.Adapter.CartAdapter;
import com.example.ordersysterm.Adapter.FragmentAdapter;
import com.example.ordersysterm.Adapter.Item.CartItem;
import com.example.ordersysterm.Adapter.Item.MenuItem;
import com.example.ordersysterm.Adapter.MenuAdapter;
import com.example.ordersysterm.orderDatabase.orderDao;
import com.example.ordersysterm.orderDatabase.orderData;
import com.example.ordersysterm.orderDatabase.orderDatabase;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabLayout tab;
    ViewPager2 vp;
    orderDao orderDao;
    ImageButton cart_btn;
    DrawerLayout drawerLayout;
    List<CartItem> cartDataList;
    RecyclerView cart_rv;
    CartAdapter cartAdapter;
    TextView dueButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        orderDao = orderDatabase.Companion.getDatabase(this).orderDao();

        tab=findViewById(R.id.tabLayout);
        vp=findViewById(R.id.viewPager);
        cart_btn=findViewById(R.id.mainBottomCart);
        drawerLayout=findViewById(R.id.drawerLayout);
        cart_rv = findViewById(R.id.cart_rv);
        dueButton = findViewById(R.id.due_button);

        cartDataList=new ArrayList<>();
        cartAdapter=new CartAdapter(this,cartDataList);
        cart_rv.setLayoutManager(new LinearLayoutManager(this));
        cart_rv.setAdapter(cartAdapter);

        cart_btn.setOnClickListener(v -> {
            drawerLayout.openDrawer(GravityCompat.END);
        });
        dueButton.setOnClickListener(v -> {
            new Thread(()->{
                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String time=currentDateTime.format(formatter);
                for(CartItem cartItem : cartDataList){
                    orderData order = new orderData(cartItem.getName(),cartItem.getPrice(),
                            cartItem.getDescription(),time,cartItem.getNumber());
                    orderDao.insertOrder(order);
                }
                cartDataList.clear();
            }).start();
            Toast.makeText(this, "点单成功！心急吃不了热豆腐哦~", Toast.LENGTH_SHORT).show();
            drawerLayout.closeDrawer(GravityCompat.END);
        });

        final String[] bottomMenu = new String[]{"首页", "我的订单", "订单统计"};
        vp.setAdapter(new FragmentAdapter(this,tab,this));
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

    public void addToCartDataList(MenuItem item){
        getShakingAnimator().start();
        for(CartItem cartItem : cartDataList){
            if(cartItem.getName().equals(item.getName())){
                int position=cartDataList.indexOf(cartItem);
                int number=cartItem.getNumber()+1;
                cartDataList.set(position,
                        new CartItem(cartItem.getName(),cartItem.getDescription(),item.getPrice()*number,number));
                cartAdapter.notifyItemChanged(position);
                return;
            }
        }
        CartItem cartItem=new CartItem(item.getName(),item.getDescription(),item.getPrice(),1);
        cartDataList.add(cartItem);
        cartAdapter.notifyItemInserted(cartDataList.size());

    }
    private Animator getShakingAnimator() {
        Animator shakingAnimator = AnimatorInflater.loadAnimator(this, R.animator.cart_shake);
        shakingAnimator.setTarget(cart_btn); // 替换为你的按钮ID
        shakingAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animation) {

            }

            @Override
            public void onAnimationEnd(@NonNull Animator animation) {
                cart_btn.setRotation(0);
            }

            @Override
            public void onAnimationCancel(@NonNull Animator animation) {

            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animation) {

            }
        });
        return shakingAnimator;
    }

}