package com.threedots.brri;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class PieChartFragment extends Fragment {

    RiceSpecies riceSpecies;

    public PieChartFragment(RiceSpecies riceSpecies) {
        // Required empty public constructor
        this.riceSpecies = riceSpecies;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_pie_chart_fragemnt, container, false);
        PieChart chart = view.findViewById(R.id.pie_chart);

        List<PieEntry> entries = new ArrayList<>();
        if (riceSpecies.coldToleranceScore != -1) {
            entries.add(new PieEntry(riceSpecies.coldToleranceScore, "Cold Tolerance"));
        }
        if (riceSpecies.submergenceScore != -1) {
            entries.add(new PieEntry(riceSpecies.submergenceScore, "Submergence"));
        }
        if (riceSpecies.droughtScore != -1) {
            entries.add(new PieEntry(riceSpecies.droughtScore, "Drought Tolerance"));
        }
        if (riceSpecies.salinityScore != -1) {
            entries.add(new PieEntry(riceSpecies.salinityScore, "Salinity Tolerance"));
        }

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.MATERIAL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());


        PieDataSet pieDataSet = new PieDataSet(entries, "Rice Species Score");
        pieDataSet.setColors(colors);
        PieData pieData = new PieData(pieDataSet);
        chart.setData(pieData);
        chart.invalidate();

        return view;
    }
}