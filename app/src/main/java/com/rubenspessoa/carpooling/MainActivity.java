package com.rubenspessoa.carpooling;

import android.content.Intent;
import android.os.Bundle;
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
        mButtonAdd = (Button) findViewById(R.id.buttonAdd);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Cria visualizaçao dos dados na ListView
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, manager.getNames());
        usersList = (ListView) findViewById(R.id.listView);
        usersList.setAdapter(adapter);
        registerForContextMenu(usersList);

        /*
        // Verifica mudanças no servidor de banco de dados.
        mUsersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println(databaseError.getMessage());
            }
        }); */

        // Open AddUser Activity
        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
