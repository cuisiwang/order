package com.example.ordersysterm;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CpuUsageInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
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
    LineChart lineChart;

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
        createLineChart(view);
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
        horizontalBarChart.setTouchEnabled(false);
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 28.7F));
        entries.add(new BarEntry(1f, 19.4F));
        entries.add(new BarEntry(2f, 24.3F));
        entries.add(new BarEntry(3f, 7.8F));
        BarDataSet dataSet = new BarDataSet(entries, "平均消费金额（元）");
        // 设置数据集的颜色
        dataSet.setColors(Color.parseColor("#50C878"),
                Color.parseColor("#FFD700"),
                Color.parseColor("#FF6F61"),
                Color.parseColor("#0F52BA"));

        Description description = new Description();
        description.setText("哪个年级吃的最多"); // 设置描述文本
        description.setTextSize(12f); // 设置描述文本的字体大小
        description.setYOffset(-14);
        horizontalBarChart.setDescription(description);
// 创建一个数据对象
        BarData barData = new BarData(dataSet);
        barData.setBarWidth(0.7f);
        barData.setDrawValues(true);

// 设置 HorizontalBarChart 的数据
        horizontalBarChart.setData(barData);
// 隐藏网格线
        horizontalBarChart.getAxisLeft().setDrawGridLines(false);

// 隐藏右侧数值
        horizontalBarChart.getAxisRight().setEnabled(false);

        XAxis xAxis = horizontalBarChart.getXAxis();
        YAxis yAxis = horizontalBarChart.getAxisLeft();

        yAxis.setDrawGridLines(true);

// 设置 X 轴的位置为在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawLabels(false);
// 隐藏 X 轴的网格线
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setTextSize(15);
        xAxis.setDrawLabels(true);
        xAxis.setDrawGridLinesBehindData(false);

        // 自定义标签值
        List<String> labels = new ArrayList<>();
        labels.add("大一");
        labels.add("大二");
        labels.add("大三");
        labels.add("大四");
// 设置自定义的标签值
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
// 刷新图表
        horizontalBarChart.invalidate();
    }
    private void createLineChart(View view) {
        lineChart = view.findViewById(R.id.lineChart);
        lineChart.setTouchEnabled(false);
        lineChart.setDrawBorders(false);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(new String[]{"11-10","11-11","11-12","11-13","11-14"}));
        lineChart.getXAxis().setGranularity(1f);
        lineChart.getDescription().setText("各年级用餐人数");
        lineChart.getDescription().setTextSize(12f);

        //x轴
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        List<Entry> dayi = new ArrayList<>();
        dayi.add(new Entry(0,481));
        dayi.add(new Entry(1,355));
        dayi.add(new Entry(2,634));
        dayi.add(new Entry(3,367));
        dayi.add(new Entry(4,425));
        LineDataSet dataSet1 = new LineDataSet(dayi, "大一");
        dataSet1.setCircleRadius(2f);
        dataSet1.setDrawCircleHole(false);
        dataSet1.setCircleColor(Color.parseColor("#50C878"));
        dataSet1.setColors(Color.parseColor("#50C878"));
        dataSet1.setLineWidth(3f);
        dataSet1.setValueTextSize(6f);

        List<Entry> daer = new ArrayList<>();
        daer.add(new Entry(0,232));
        daer.add(new Entry(1,435));
        daer.add(new Entry(2,334));
        daer.add(new Entry(3,510));
        daer.add(new Entry(4,412));
        LineDataSet dataSet2 = new LineDataSet(daer, "大二");
        dataSet2.setCircleRadius(2f);
        dataSet2.setDrawCircleHole(false);
        dataSet2.setCircleColor(Color.parseColor("#FFD700"));
        dataSet2.setColors(Color.parseColor("#FFD700"));
        dataSet2.setLineWidth(3f);
        dataSet2.setValueTextSize(6f);

        List<Entry> dasan = new ArrayList<>();
        dasan.add(new Entry(0,463));
        dasan.add(new Entry(1,202));
        dasan.add(new Entry(2,562));
        dasan.add(new Entry(3,612));
        dasan.add(new Entry(4,623));
        LineDataSet dataSet3 = new LineDataSet(dasan, "大三");
        dataSet3.setCircleRadius(2f);
        dataSet3.setDrawCircleHole(false);
        dataSet3.setCircleColor(Color.parseColor("#FF6F61"));
        dataSet3.setColors(Color.parseColor("#FF6F61"));
        dataSet3.setLineWidth(3f);
        dataSet3.setValueTextSize(6f);

        List<Entry> dasi = new ArrayList<>();
        dasi.add(new Entry(0,631));
        dasi.add(new Entry(1,523));
        dasi.add(new Entry(2,224));
        dasi.add(new Entry(3,190));
        dasi.add(new Entry(4,320));
        LineDataSet dataSet4 = new LineDataSet(dasi, "大四");
        dataSet4.setCircleRadius(2f);
        dataSet4.setDrawCircleHole(false);
        dataSet4.setCircleColor(Color.parseColor("#0F52BA"));
        dataSet4.setColors(Color.parseColor("#0F52BA"));
        dataSet4.setLineWidth(3f);
        dataSet4.setValueTextSize(6f);

        LineData lineData = new LineData();
        lineData.addDataSet(dataSet1);
        lineData.addDataSet(dataSet2);
        lineData.addDataSet(dataSet3);
        lineData.addDataSet(dataSet4);

        // 设置 LineChart 的数据
        lineChart.setData(lineData);
        lineChart.invalidate();
    }

}
