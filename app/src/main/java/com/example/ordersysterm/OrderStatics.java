package com.example.ordersysterm;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class OrderStatics extends Fragment {

    PieChart pieChart;
    HorizontalBarChart horizontalBarChart;
    BarChart barChart;

    public OrderStatics() {
        // Required empty public constructor
    }

    public static OrderStatics newInstance() {
        OrderStatics fragment = new OrderStatics();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_order_statics, container, false);
        createPieChart(view);
        createHorizonBarChart(view);
        createBarChart(view);
        return view;
    }

    private void createPieChart(View view) {
        pieChart=view.findViewById(R.id.pieChart);
        pieChart.setDrawHoleEnabled(false);
        pieChart.getDescription().setText("大家在吃");
        pieChart.getDescription().setTextSize(12f);

        // 创建数据集
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(30f, "川菜"));
        entries.add(new PieEntry(25f, "粤菜"));
        entries.add(new PieEntry(20f, "湘菜"));
        entries.add(new PieEntry(15f, "鲁菜"));
        entries.add(new PieEntry(10f, "西餐"));

        // 创建数据集对象
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setDrawValues(false);
        // 设置数据集的颜色
        dataSet.setColors(Color.rgb(255, 99, 71),
                Color.rgb(255, 165, 0),
                Color.rgb(255, 215, 0),
                Color.rgb(50, 205, 50),
                Color.rgb(173, 216, 230));
        // 设置图表的数据
        PieData data=new PieData(dataSet);
        pieChart.setData(data);
        pieChart.invalidate();
    }

    private void createHorizonBarChart(View view) {
        horizontalBarChart=view.findViewById(R.id.hBarChart);
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 28.7F)); // 第一个数据点，数值为 10，索引为 0
        entries.add(new BarEntry(1f, 19.4F)); // 第二个数据点，数值为 20，索引为 1
        entries.add(new BarEntry(2f, 24.3F)); // 第三个数据点，数值为 30，索引为 2
        entries.add(new BarEntry(3f, 7.8F)); // 第三个数据点，数值为 30，索引为 2
        BarDataSet dataSet = new BarDataSet(entries, "平均消费金额（元）");
        // 设置数据集的颜色
        dataSet.setColor(Color.parseColor("#FFD700"));
        dataSet.setDrawValues(true);

        Description description = new Description();
        description.setText("哪个年级吃的最多"); // 设置描述文本
        description.setTextSize(12f); // 设置描述文本的字体大小
        description.setTextColor(Color.parseColor("#FF007F")); // 设置描述文本的颜色
        description.setYOffset(-14);

// 将 Description 设置给 HorizontalBarChart
        horizontalBarChart.setDescription(description);
// 创建一个数据对象
        BarData barData = new BarData(dataSet);
        barData.setBarWidth(0.7f);
        barData.setDrawValues(true);
        horizontalBarChart.setScaleEnabled(false);
        horizontalBarChart.setClickable(false);
        horizontalBarChart.setHighlightPerDragEnabled(false);
        horizontalBarChart.setHighlightPerTapEnabled(false);

// 设置 HorizontalBarChart 的数据
        horizontalBarChart.setData(barData);
// 隐藏网格线
        horizontalBarChart.getAxisLeft().setDrawGridLines(false);

// 隐藏右侧数值
        horizontalBarChart.getAxisRight().setEnabled(false);

        XAxis xAxis = horizontalBarChart.getXAxis();
        YAxis yAxis = horizontalBarChart.getAxisLeft();

// 设置 X 轴的位置为在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

// 隐藏 X 轴的网格线
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setTextSize(15);
        xAxis.setDrawLabels(true);
        xAxis.setDrawGridLinesBehindData(true);
        // 自定义标签值
        List<String> labels = getLabels(); // 例如：A, B, C, ...

// 设置自定义的标签值
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
// 刷新图表
        horizontalBarChart.invalidate();
    }
    private List<String> getLabels() {
        List<String> labels = new ArrayList<>();
        labels.add("大一");
        labels.add("大二");
        labels.add("大三");
        labels.add("大四");
        return labels;
    }


    private void createBarChart(View view) {
    }

}
