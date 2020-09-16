package com.threedots.brri;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class BarPlotFragment extends Fragment {


    public BarPlotFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_bar_plot, container, false);
        BarChart chart = view.findViewById(R.id.bar_chart);

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(1, 3));
        entries.add(new BarEntry(2, 5));
        entries.add(new BarEntry(3, 7));
        entries.add(new BarEntry(4, 3));
        entries.add(new BarEntry(5, 5));
        entries.add(new BarEntry(6, 1));
        entries.add(new BarEntry(7, 9));

        BarDataSet dataSet = new BarDataSet(entries, "Data Label");

        BarData barData = new BarData(dataSet);
        chart.setData(barData);
        chart.invalidate();

        return view;
    }
}