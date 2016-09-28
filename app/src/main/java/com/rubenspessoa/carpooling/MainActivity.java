package com.rubenspessoa.carpooling;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rubenspessoa.carpooling.Util.Manager;
import com.rubenspessoa.carpooling.Util.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    TextView mConditionTextView;
    Button mButtonSunny;
    Button mButtonFoggy;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mUsersRef = mRootRef.child("users");

    static Manager manager;
    ListView usersList;
    ArrayAdapter<String> adapter;
    Button mButtonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (mUsersRef != null) {
            manager = new Manager((List<User>) mUsersRef);
        } else {
            manager = new Manager();
        }

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


        // Verifica mudanças no servidor de banco de dados.
        mUsersRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println(dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("The read failed: " + databaseError.getMessage());

            }
        });

        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.rubenspessoa.carpooling.AddActivity.class);
                startActivity(intent);
            }
        });
        /*

        mButtonSunny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUsersRef.setValue("Sunny");
            }
        });

        mButtonFoggy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUsersRef.setValue("Foggy");
            }
        });
        */

    }
}
