package com.threedots.brri;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    final String TAG = "MainActivity";
    public static List<RiceSpecies> riceSpeciesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        riceSpeciesList = new ArrayList<>();
        loadRiceSpecies();
        for (int i=0; i<riceSpeciesList.size();i++)
            Log.i(TAG, "onCreate: " + riceSpeciesList.get(i).name);


    }

    public void loadRiceSpecies() {
        String json = loadJSONFromAsset();
        try {
            JSONObject mainObj = new JSONObject(json);
            JSONArray mainSheet = mainObj.getJSONArray("Sheet1");
            Log.i(TAG, "loadRiceSpecies: " + mainSheet.length());
            for (int i=0; i<mainSheet.length(); i++) {
                JSONObject riceObj = mainSheet.getJSONObject(i);
                try {
                    String designation =  riceObj.getString("Designation");
                    String id = riceObj.getString("Acc No.");
                    String submerganceScore = riceObj.getString("Submergence Score");
                    if (id != null && designation != null && !designation.isEmpty()) {
                        RiceSpecies rice = new RiceSpecies(Integer.parseInt(id),designation);
                        if (submerganceScore != null)
                            rice.setSubmergenceScore(Integer.parseInt(submerganceScore));

                        riceSpeciesList.add(rice);
                    }
                } catch (Exception e) {

                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}