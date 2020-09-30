package com.threedots.brri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {
    final String TAG = "MainActivity";
    public static List<RiceSpecies> riceSpeciesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        riceSpeciesList = new ArrayList<>();
        loadRiceSpecies();


        Log.i(TAG, "onCreate: " + LoginActivity.data.substring(0, 30));
        Intent menuIntent = new Intent(this, MenuActivity.class);
        startActivity(menuIntent);
        finish();

    }

    public void loadRiceSpecies() {
        String json = LoginActivity.data;
        try {
            JSONObject mainObj = new JSONObject(json);
            JSONArray mainSheet = mainObj.getJSONArray("Sheet1");
            Log.i(TAG, "loadRiceSpecies: " + mainSheet.length());
            for (int i=0; i<mainSheet.length(); i++) {
                JSONObject riceObj = mainSheet.getJSONObject(i);
                try {
                    String designation =  riceObj.getString("Designation");
                    String id = riceObj.getString("Acc No.");
                    //Log.i(TAG, "loadRiceSpecies: "  + "FOUND " + id + " -- " + designation);
                    if ( designation != null && !designation.isEmpty()) {

                        RiceSpecies rice = new RiceSpecies(Integer.parseInt(id),designation);

                        if (riceObj.has("Submergence Score"))
                            rice.setSubmergenceScore(Integer.parseInt(riceObj.getString("Submergence Score")));
                        if (riceObj.has("Cold tolerance score"))
                            rice.coldToleranceScore = Integer.parseInt(riceObj.getString("Cold tolerance score"));
                        if (riceObj.has("Drought score"))
                            rice.droughtScore = Integer.parseInt(riceObj.getString("Drought score"));
                        //Log.i(TAG, "loadRiceSpecies: "  + "FOUND " + id + " -- " + designation);
                        if (riceObj.has("Salinity  Score"))
                            rice.salinityScore = (int) Float.parseFloat(riceObj.getString("Salinity  Score"));


                        riceSpeciesList.add(rice);


                    }
                } catch (Exception e) {
                    Log.i(TAG, "loadRiceSpecies: " + e.getStackTrace());
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
    /*
    public void encryptFile() {


        try {

            String key = "1234567812345678";
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");

            Cipher desCipher = Cipher.getInstance("AES");

            InputStream is = this.getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            desCipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = desCipher.doFinal(buffer);

            String encyptedString = new String(encrypted);

            desCipher.init(Cipher.DECRYPT_MODE, aesKey);
            byte[] textDecrypted = desCipher.doFinal(encrypted);

            String decryptedString = new String(textDecrypted);

            Log.i(TAG, "encryptFile: " + encyptedString.substring(0, 50));
            Log.i(TAG, "encryptFile: " + decryptedString.substring(0, 50));

        } catch (Exception e){

        }
    }

    public String getDecryptedString(String key) {
        String out = "";

        try{
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher desCipher = Cipher.getInstance("AES");

            InputStream is = this.getAssets().open("Output.txt");

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            desCipher.init(Cipher.DECRYPT_MODE, aesKey);

            byte[] textDecrypted = desCipher.doFinal(buffer);

            String decryptedString = new String(textDecrypted);
            return  decryptedString;

        } catch (Exception e) {
            Log.i(TAG, "getDecryptedString: " + e.getStackTrace());
        }
        return out;
    }

    */
}