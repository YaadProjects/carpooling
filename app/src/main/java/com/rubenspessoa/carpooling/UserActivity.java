/**
 App for controlling the charge of carpoolers.
 Copyright (C) 2016 Rubens Pessoa

 This file is part of Carpooling app.

 Carpooling is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.rubenspessoa.carpooling;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rubenspessoa.carpooling.Util.User;

import java.text.DecimalFormat;

public class UserActivity extends AppCompatActivity {

    private TextView username;
    private TextView ridesCount;
    private TextView owe;
    private Button addRideBtn;
    private Button decreaseRideBtn;
    private Button saveUserInfoBtn;

    public static DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    public static DatabaseReference mUsersRef = mRootRef.child("users");


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
        String name = intent.getExtras().getString("name");

        mUsersRef.child(name).addValueEventListener(new ValueEventListener() {
            User user;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                showUpdatedValues();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        databaseError.getMessage(),
                        Toast.LENGTH_SHORT);
                toast.show();
            }

            private void showUpdatedValues() {
                username.setText(user.getName());
                ridesCount.setText(String.valueOf(user.getRidesCount()));
                DecimalFormat df = new DecimalFormat("0.00");
                owe.setText(("R$ " + String.valueOf(df.format(user.getRidesCount() * 2.5))));
            }
        });

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





}
