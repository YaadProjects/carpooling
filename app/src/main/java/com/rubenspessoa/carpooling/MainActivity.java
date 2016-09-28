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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rubenspessoa.carpooling.Util.Manager;

public class MainActivity extends AppCompatActivity {

    static DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    static DatabaseReference mUsersRef = mRootRef.child("users");
    static Manager manager;

    public ListView usersList;
    public ArrayAdapter<String> adapter;
    public Button mButtonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = new Manager();
        mButtonAdd = (Button) findViewById(R.id.buttonAddUserMain);
    }

    @Override
    protected void onStart() {
        super.onStart();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, manager.getNames());
        usersList = (ListView) findViewById(R.id.usersList);
        usersList.setAdapter(adapter);
        registerForContextMenu(usersList);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.rubenspessoa.carpooling.AddActivity.class);
                startActivity(intent);
            }
        });

        usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, com.rubenspessoa.carpooling.UserActivity.class);
                intent.putExtra("index", position);
                startActivity(intent);
            }
        });
    }
}
