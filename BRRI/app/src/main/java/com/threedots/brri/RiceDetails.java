package com.threedots.brri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RiceDetails extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private BarPlotFragment barPlotFragment;
    private PieChartFragment pieChartFragment;
    private LineChartFragment lineChartFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rice_details);
        RiceSpecies rice = (RiceSpecies)getIntent().getSerializableExtra("Rice");

        TextView nameView = findViewById(R.id.details_rice_title);

        TextView idView = findViewById(R.id.details_rice_id);
        TextView submergenceView = findViewById(R.id.details_rice_submergence);
        TextView salinityView = findViewById(R.id.details_rice_salinity);
        TextView coldView = findViewById(R.id.details_rice_cold);
        TextView droughtView = findViewById(R.id.details_rice_drought);




        nameView.setText(rice.name);
        idView.setText("#" + String.valueOf(rice.id) );

        if (rice.salinityScore == -1) salinityView.setText("N/A");
        else salinityView.setText(String.valueOf(rice.salinityScore));

        if (rice.submergenceScore == -1) submergenceView.setText("N/A");
        else submergenceView.setText(String.valueOf(rice.submergenceScore));

        if (rice.coldToleranceScore == -1) coldView.setText("N/A");
        else coldView.setText(String.valueOf(rice.coldToleranceScore));

        if (rice.droughtScore == -1) droughtView.setText("N/A");
        else droughtView.setText(String.valueOf(rice.droughtScore));


        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        tabLayout.setupWithViewPager(viewPager);
        lineChartFragment = new LineChartFragment();
        barPlotFragment = new BarPlotFragment();
        pieChartFragment = new PieChartFragment();

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        viewPagerAdapter.addFragment(lineChartFragment, "Line Chart");
        viewPagerAdapter.addFragment(barPlotFragment, "Bar Chart");
        viewPagerAdapter.addFragment(pieChartFragment, "Pie Chart");
        viewPager.setAdapter(viewPagerAdapter);

    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragmentList = new ArrayList<>();
        private List<String> fragmentTitle = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitle.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitle.get(position);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}