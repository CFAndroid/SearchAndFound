package com.proto.searchandfound.searchandfound;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;


public class HomeScreen extends ActionBarActivity {

    private Button anmelden;
    private PopupWindow meldung;
    private LayoutInflater layoutInflater;
    private RelativeLayout relativeLayout;
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

    //Verhindern, dass die App durch Back-Button zurück auf die Startseite geht
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR

            layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.popup,null);
            meldung = new PopupWindow(container,200,400,true);
            meldung.showAtLocation(relativeLayout, Gravity.NO_GRAVITY,500,500);

            container.setOnTouchListener(new View.OnTouchListener(){

                public boolean onTouch(View view,MotionEvent motionEvent){

                       meldung.dismiss();
                        return true;
            }
            });
    }
        return super.onKeyDown(keyCode, event);
    }

}
