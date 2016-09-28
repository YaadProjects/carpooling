package com.rubenspessoa.carpooling;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rubenspessoa.carpooling.Util.User;

public class AddActivity extends AppCompatActivity {

    EditText input;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        input = (EditText) findViewById(R.id.addName);
        addBtn = (Button) findViewById(R.id.button);
    }

    @Override
    protected void onStart() {
        super.onStart();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = input.getText().toString();
                User newUser = new User(userName);
                MainActivity.manager.add(newUser);
                //MainActivity.mUsersRef.child(userName).setValue(newUser);
                AddActivity.this.finish();
            }
        });
    }

}
