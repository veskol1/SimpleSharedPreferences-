package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Switch aSwitch;
    private TextView mainTextView;

    private Boolean switchStatus=false;
    private View rootView;

    public static final String SHARED_PREF="com.example.sharepreferences.SharedPrefs";
    public static final String SWITCH="com.example.sharepreferences.switch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aSwitch = findViewById(R.id.switch1);
        mainTextView = findViewById(R.id.textView);
        rootView = findViewById(R.id.main_layout);

        loadData();
        updateViews();
        aSwitch = findViewById(R.id.switch1);
        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aSwitch.isChecked()) {
                    rootView.setBackgroundColor(Color.GRAY);
                    aSwitch.setTextColor(Color.WHITE);
                    mainTextView.setTextColor(Color.WHITE);
                    switchStatus=true;
                }
                else{
                    rootView.setBackgroundColor(Color.WHITE);
                    aSwitch.setTextColor(Color.BLACK);
                    mainTextView.setTextColor(Color.BLACK);
                    switchStatus=false;
                }
                saveData();
            }
        });
    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(SWITCH,switchStatus);
        editor.apply();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF,MODE_PRIVATE);
        switchStatus = sharedPreferences.getBoolean(SWITCH,true);
    }

    public void updateViews(){
        if(switchStatus){
            aSwitch.setChecked(true);
            aSwitch.setTextColor(Color.WHITE);
            mainTextView.setTextColor(Color.WHITE);
            rootView.setBackgroundColor(Color.GRAY);
        }
        else{
            aSwitch.setChecked(false);
            aSwitch.setTextColor(Color.BLACK);
            mainTextView.setTextColor(Color.BLACK);
            rootView.setBackgroundColor(Color.WHITE);
        }
    }

}
