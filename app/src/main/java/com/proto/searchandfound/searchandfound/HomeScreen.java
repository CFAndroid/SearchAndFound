package com.proto.searchandfound.searchandfound;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;


public class HomeScreen extends ActionBarActivity {

    private Button anmelden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        anmelden = (Button) findViewById(R.id.button4);
        this.onClickLoginListener();
    }


    // Betätigen von dem Anmelden-Button

    public void onClickLoginListener() {

        anmelden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeScreen.this, Login.class);
                startActivity(intent);
            }

        });
    }

}
