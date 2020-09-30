package com.threedots.brri;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

public class BarPlotFragment extends Fragment {

    BarChart chart;

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
        chart = view.findViewById(R.id.bar_chart);

        setupChart(0);
        Spinner dropdown = view.findViewById(R.id.bar_chart_spinner);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setupChart(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }

    private void setupChart(int j) {
        List<BarEntry> entries = new ArrayList<>();
        for (int i=0; i<11; i++) {
            if (ListActivity.Scores[j][i] != 0) {
                entries.add(new BarEntry(i, ListActivity.Scores[j][i]));
            }
        }
        chart.setHighlightPerTapEnabled(false);
        chart.setPinchZoom(true);

        BarDataSet dataSet = new BarDataSet(entries, "Data Label");

        BarData barData = new BarData(dataSet);
        chart.setData(barData);
        chart.invalidate();
    }
}