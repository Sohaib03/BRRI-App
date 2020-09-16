package com.threedots.brri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.edmodo.rangebar.RangeBar;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.suke.widget.SwitchButton;

import java.util.List;

public class ListActivity extends AppCompatActivity implements MyAdapter.OnRiceClickListener {
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    List<RiceSpecies> riceSpeciesList;
    ConstraintLayout playerSheet;
    BottomSheetBehavior bottomSheetBehavior;
    Menu topMenu;
    Boolean reversed = false;
    TextView sortStatus;
    Button sortBySubmergence;
    Button sortById;
    Button sortBySalinity;
    Button sortByCold;
    Button sortByDrought;
    Toolbar mToolbar;


    int sortedBy = RiceSpecies.ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        riceSpeciesList = MainActivity.riceSpeciesList;

        sortStatus = findViewById(R.id.sheet_sort_status);

        recyclerView = findViewById(R.id.recyclerView);
        myAdapter = new MyAdapter(this, riceSpeciesList, this);

        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        playerSheet = findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(playerSheet);

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);

        initSliderRangeBar();


        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                MenuItem opener = topMenu.findItem(R.id.action_open_bottom_sheet);


                if (newState == BottomSheetBehavior.STATE_EXPANDED) opener.setIcon(R.drawable.ic_baseline_keyboard_arrow_down_24);
                else opener.setIcon(R.drawable.ic_baseline_keyboard_arrow_up_24);
            }
            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        initializeButtons();

        initializeButtonClickProperty();

        SwitchButton orderSwitch = findViewById(R.id.switchButton);
        orderSwitch.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                reversed = isChecked;
                myAdapter.reverse();
            }
        });
    }
    private void initializeButtons() {
        sortByDrought = findViewById(R.id.sort_by_drought);
        sortByCold = findViewById(R.id.sort_by_cold);
        sortBySalinity = findViewById(R.id.sort_by_salinity);
        sortById = findViewById(R.id.sort_by_id);
        sortBySubmergence = findViewById(R.id.sort_by_submergence);
    }

    private void initializeButtonClickProperty() {

        sortBySubmergence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAdapter.filterRange(0, 10, RiceSpecies.SUBMERGENCE);
                myAdapter.sortBy(RiceSpecies.SUBMERGENCE, reversed);
                sortStatus.setText("Sorted by: Submergence");
                resetButtonColor();
                sortedBy = RiceSpecies.SUBMERGENCE;
                sortBySubmergence.setBackground(getDrawable(R.drawable.selected_button_style));

            }
        });

        sortById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAdapter.sortBy(RiceSpecies.ID, reversed);
                sortStatus.setText("Sorted by: ID");
                sortedBy = RiceSpecies.ID;
                resetButtonColor();
                sortById.setBackground(getDrawable(R.drawable.selected_button_style));
            }
        });

        sortBySalinity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAdapter.filterRange(0, 10, RiceSpecies.SALINITY);
                myAdapter.sortBy(RiceSpecies.SALINITY, reversed);
                sortStatus.setText("Sorted by: Salinity");
                sortedBy = RiceSpecies.SALINITY;
                resetButtonColor();
                sortBySalinity.setBackground(getDrawable(R.drawable.selected_button_style));
            }
        });

        sortByCold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAdapter.filterRange(0, 10, RiceSpecies.COLD_TOLERANCE);

                myAdapter.sortBy(RiceSpecies.COLD_TOLERANCE, reversed);
                sortStatus.setText("Sorted by: Cold");
                sortedBy = RiceSpecies.COLD_TOLERANCE;
                resetButtonColor();
                sortByCold.setBackground(getDrawable(R.drawable.selected_button_style));
            }
        });


        sortByDrought.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAdapter.filterRange(0, 10, RiceSpecies.DROUGHT);

                myAdapter.sortBy(RiceSpecies.DROUGHT, reversed);
                sortStatus.setText("Sorted by: Drought");
                sortedBy = RiceSpecies.DROUGHT;
                resetButtonColor();
                sortByDrought.setBackground(getDrawable(R.drawable.selected_button_style));
            }
        });
    }

    private void initSliderRangeBar() {
        RangeBar rangeBar = findViewById(R.id.range_bar);
        rangeBar.setTickCount(10);
        rangeBar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onIndexChangeListener(RangeBar rangeBar, int i, int i1) {
                Log.i("TAG", "onIndexChangeListener: " + "Range " + i + " to " + i1);
                myAdapter.filterRange(i, i1, sortedBy);
                sortBySorted();
            }
        });
    }

    private void sortBySorted() {
        myAdapter.sortBy(sortedBy, reversed);
    }

    public void resetButtonColor() {
        sortById.setBackground(getDrawable(R.drawable.unselected_button_style));
        sortBySubmergence.setBackground(getDrawable(R.drawable.unselected_button_style));
        sortByDrought.setBackground(getDrawable(R.drawable.unselected_button_style));
        sortByCold.setBackground(getDrawable(R.drawable.unselected_button_style));
        sortBySalinity.setBackground(getDrawable(R.drawable.unselected_button_style));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        topMenu = menu;
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)item.getActionView();

        MenuItem item2 = menu.findItem(R.id.action_open_bottom_sheet);
        item2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
                return true;
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                myAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public void onRiceClick( RiceSpecies riceSpecies) {
        Intent intent = new Intent(this, RiceDetails.class);
        intent.putExtra("Rice", riceSpecies);
        startActivity(intent);
    }
}