package com.threedots.brri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class ListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    List<RiceSpecies> riceSpeciesList;
    ConstraintLayout playerSheet;
    BottomSheetBehavior bottomSheetBehavior;
    Menu topMenu;
    Boolean reveresed = false;
    TextView sortStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        riceSpeciesList = MainActivity.riceSpeciesList;

        sortStatus = findViewById(R.id.sheet_sort_status);

        recyclerView = findViewById(R.id.recyclerView);
        myAdapter = new MyAdapter(this, riceSpeciesList);

        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        playerSheet = findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(playerSheet);

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        RangeBar rangeBar = findViewById(R.id.range_bar);
        rangeBar.setTickCount(10);
        rangeBar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onIndexChangeListener(RangeBar rangeBar, int i, int i1) {
                Log.i("TAG", "onIndexChangeListener: " + "Range " + i + " to " + i1);
                myAdapter.filterRange(i, i1, RiceSpecies.SUBMERGENCE);
            }
        });

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

        Button sortBySubmergence = findViewById(R.id.sort_by_submergence);
        sortBySubmergence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAdapter.sortBySubmergence(reveresed);
                sortStatus.setText("Sorted by: Submergence");
            }
        });

        SwitchButton orderSwitch = findViewById(R.id.switchButton);
        orderSwitch.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                reveresed = isChecked;
                myAdapter.reverse();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
        return super.onCreateOptionsMenu(menu);
    }
}