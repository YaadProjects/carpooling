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
