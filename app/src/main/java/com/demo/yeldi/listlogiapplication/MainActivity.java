package com.demo.yeldi.listlogiapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.demo.yeldi.listlogiapplication.activity.DeviceActivity;

/**
 * Created by pudumai on 6/14/2016.
 */
public class MainActivity extends AppCompatActivity {

    Button btnSub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btnSub = (Button)findViewById(R.id.btn_sub);
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DeviceActivity.class);
                startActivity(intent);
            }
        });
    }
}
