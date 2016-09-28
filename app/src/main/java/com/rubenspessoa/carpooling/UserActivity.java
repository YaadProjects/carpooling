package com.rubenspessoa.carpooling;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rubenspessoa.carpooling.Util.Manager;
import com.rubenspessoa.carpooling.Util.User;

import java.text.DecimalFormat;

public class UserActivity extends AppCompatActivity {

    private TextView username;
    private TextView ridesCount;
    private TextView owe;
    private Button addRideBtn;
    private Button decreaseRideBtn;
    private Button saveUserInfoBtn;
    private User user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        username = (TextView) findViewById(R.id.userName);
        ridesCount = (TextView) findViewById(R.id.ridesCount);
        owe = (TextView) findViewById(R.id.owe);
        addRideBtn = (Button) findViewById(R.id.addRideBtn);
        decreaseRideBtn = (Button) findViewById(R.id.decreaseRideBtn);
        saveUserInfoBtn = (Button) findViewById(R.id.saveUserInfoBtn);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        this.user = Manager.getUser(intent.getExtras().getInt("index"));
        showUpdatedValues();

        addRideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.addRide();
                showUpdatedValues();
            }
        });

        decreaseRideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.decreaseRide();
                showUpdatedValues();
            }
        });

        saveUserInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserActivity.this.finish();
            }
        });


    }

    private void showUpdatedValues() {
        this.username.setText(this.user.name);
        this.ridesCount.setText(String.valueOf(this.user.ridesCount));
        DecimalFormat df = new DecimalFormat("0.00");
        this.owe.setText(("R$ " + String.valueOf(df.format(user.ridesCount * 2.5))));
    }
}
