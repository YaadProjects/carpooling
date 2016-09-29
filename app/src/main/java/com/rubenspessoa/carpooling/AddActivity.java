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

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rubenspessoa.carpooling.Util.User;

public class AddActivity extends AppCompatActivity {

    EditText inputEmail, inputName;
    Button createUserBtn;
    private ProgressDialog mProgress;

    public static DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    public static DatabaseReference mUsersRef = mRootRef.child("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        inputName = (EditText) findViewById(R.id.nameEditText);
        inputEmail = (EditText) findViewById(R.id.emailEditText);
        createUserBtn = (Button) findViewById(R.id.createUserBtn);
        mProgress = new ProgressDialog(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        createUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mProgress.setMessage("Creating user...");
                mProgress.show();

                String userName = inputName.getText().toString();
                String userEmail = inputEmail.getText().toString();
                User newUser = new User(userName, userEmail);

                mUsersRef.child(userName).setValue(newUser);

                AddActivity.this.finish();
            }
        });
    }

}
