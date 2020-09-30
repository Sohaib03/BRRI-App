package com.threedots.brri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.InputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class LoginActivity extends AppCompatActivity {

    String TAG = "LoginActivity";
    public static String data = "";
    public static String SHARED_PREFS = "shared_prefs";
    public static String DATA = "data";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextInputLayout passwordLayout = findViewById(R.id.password_text_input);
        final TextInputEditText passwordTextInput = findViewById(R.id.password_edit_text);
        final Button nextButton = findViewById(R.id.next_button);
        loadData();
        if (data != "") {
            Log.i(TAG, "onCreate: " + "Found data");
            Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mainIntent);
            finish();
        }

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = passwordTextInput.getText().toString();
                Log.i(TAG, "onClick: " + password);
                String output = getDecryptedString(password);
                if (output.isEmpty()) {
                    passwordLayout.setError("Wrong Password");
                }
                else {
                    Log.i(TAG, "onClick: " + "correct password");
                    passwordLayout.setError(null);
                    data = output;
                    saveData();
                    Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
            }
        });


    }
    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Log.i(TAG, "saveData: " + "Data Saved");
        editor.putString(DATA, data);
        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        data = sharedPreferences.getString(DATA, "");
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


}