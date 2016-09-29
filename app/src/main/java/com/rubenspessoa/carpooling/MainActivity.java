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
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rubenspessoa.carpooling.Util.User;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mUsersList;
    private DatabaseReference mDatabase;
    private Button mButtonAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonAdd = (Button) findViewById(R.id.buttonAddUserMain);

        mUsersList = (RecyclerView) findViewById(R.id.usersList);
        mUsersList.setHasFixedSize(true);
        mUsersList.setLayoutManager(new LinearLayoutManager(this));

        mDatabase = FirebaseDatabase.getInstance().getReference().child("users");
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<User, UserViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<User, UserViewHolder>(
                User.class,
                R.layout.user_row,
                UserViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(UserViewHolder viewHolder, final User model, final int position) {
                viewHolder.setName(model.getName());
                viewHolder.setEmail(model.getEmail());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, com.rubenspessoa.carpooling.UserActivity.class);
                        intent.putExtra("name", model.getName());
                        startActivity(intent);
                    }
                });
            }
        };

        mUsersList.setAdapter(firebaseRecyclerAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.rubenspessoa.carpooling.AddActivity.class);
                startActivity(intent);
            }
        });
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        View mView;
        public UserViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setName(String name) {
            TextView userNameRow = (TextView) mView.findViewById(R.id.userNameRow);
            userNameRow.setText(name);
        }

        public void setEmail(String email) {
            TextView userEmailRow = (TextView) mView.findViewById(R.id.userEmailRow);
            userEmailRow.setText(email);
        }
    }
}
