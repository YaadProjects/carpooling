package com.rubenspessoa.carpooling;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.rubenspessoa.carpooling.Util.Manager;
import com.rubenspessoa.carpooling.Util.User;

import java.text.DecimalFormat;

public class UserActivity extends AppCompatActivity {

    TextView name;
    TextView totalCount;
    TextView owe;
    Button addRide;
    Button decRide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        name = (TextView) findViewById(R.id.userName);
        totalCount = (TextView) findViewById(R.id.ridesCount);
        owe = (TextView) findViewById(R.id.cost);
        addRide = (Button) findViewById(R.id.button2);
        decRide = (Button) findViewById(R.id.button3);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        User user = Manager.getUser(intent.getExtras().getInt("index"));
        name.setText(user.name);
        totalCount.setText(String.valueOf(user.rideCount));
        DecimalFormat df = new DecimalFormat("0.00");
        owe.setText(
                ("R$ " +
                String.valueOf(
                        df.format(
                                user.rideCount * 2.5
                        )
                )
        )
        );
    }
}
