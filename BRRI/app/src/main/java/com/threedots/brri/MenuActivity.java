package com.threedots.brri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        CardView database_card = findViewById(R.id.database_card);
        CardView info_card = findViewById(R.id.info_card);
        final CardView help_card = findViewById(R.id.help_card);

        database_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ListIntent = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(ListIntent);
            }
        });

        info_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent InfoIntent = new Intent(getApplicationContext(), InfoActivity.class);
                startActivity(InfoIntent);
            }
        });

        help_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HelpIntent = new Intent(getApplicationContext(), HelpActivity.class);
                startActivity(HelpIntent);
            }
        });
    }
}