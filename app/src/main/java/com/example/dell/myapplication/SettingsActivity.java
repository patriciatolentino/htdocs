package com.example.dell.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    Switch switch1;
    Switch switch2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        switch1 = (Switch)findViewById(R.id.switch1);
        switch2 = (Switch)findViewById(R.id.switch2);

        switch1.setOnCheckedChangeListener(this);

    }


    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(switch1.isChecked()) {
            Toast.makeText(SettingsActivity.this, "Vibrator is on", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(SettingsActivity.this, "Vibrator is off", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
