package com.example.alfred.recycleview;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        configureTextViews();
        configureBackButton();
        configureDeleteButton();
    }

    private void configureTextViews() {
        Bundle extras = getIntent().getExtras();
        TextView headerText = (TextView) findViewById(R.id.headerText);
        TextView mainText = (TextView) findViewById(R.id.mainText);
        if(extras != null) {
            headerText.setText((String) extras.get("headerText"));
            mainText.setText((String) extras.get("mainText"));
        }
    }

    private void configureDeleteButton() {
        final Button deleteButton = (Button) findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("delete", "true");
                resultIntent.putExtra("pos", (int)getIntent().getExtras().get("pos"));
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    private void configureBackButton() {
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getIntent().putExtra("delete", "false");
                finish();
            }
        });
    }


}
