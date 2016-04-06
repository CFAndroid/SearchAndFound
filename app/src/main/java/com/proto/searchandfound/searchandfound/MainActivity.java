package com.proto.searchandfound.searchandfound;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    private TextView status;
    private ProgressBar laden;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    private  boolean voll;
    private Button weiter;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        laden = (ProgressBar) findViewById(R.id.progressBar);
        status = (TextView) findViewById(R.id.textView14);
        weiter = (Button) findViewById(R.id.button8);

        weiter.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HomeScreen.class);
                startActivity(intent);
            }
        });
        new Thread(new Runnable(){
            public void run(){
                while(progressStatus < 100){

                    progressStatus +=10;
                    handler.post(new Runnable(){
                       public void run(){
                           laden.setProgress(progressStatus);
                           status.setText("Progress: " + progressStatus +"/" +laden.getMax());
                            if(progressStatus == 100){
                                voll = true;
                                Intent intent = new Intent(MainActivity.this, HomeScreen.class);
                                startActivity(intent);
                           }

                       }
                    });
                    try{
                        Thread.sleep(100);
                        }
                    catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();

           if(voll == true) {
               Intent intent = new Intent(MainActivity.this,HomeScreen.class);
                startActivity(intent);
           }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
