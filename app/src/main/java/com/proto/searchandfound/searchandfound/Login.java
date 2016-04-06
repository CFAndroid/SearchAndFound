package com.proto.searchandfound.searchandfound;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;


public class Login extends ActionBarActivity {

    private Button login;
    private TextView username, passwort;
    private String userT,passwT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        JSONParser jsonParser = new JSONParser();
        username = (TextView) findViewById(R.id.Username);
        passwort = (TextView) findViewById(R.id.Passwort);
        login = (Button) findViewById(R.id.login);
        this.loginListener();
    }

    public void loginListener(){

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new LoginExecute().execute();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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


    /**
     * Created by #Chris on 05.04.2016.
     */
    class LoginExecute extends AsyncTask<String,String,String> {

        //Login progressdialog
        private ProgressDialog pDialog;
        private static final String LOGIN_URL = "";
        //JSON element ids from repsonse of php script:
        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Login.this);
            pDialog.setMessage("Prüft Login...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);

        }

        @Override
        protected String doInBackground(String... strings) {

            userT = username.getText().toString();
            passwT = passwort.getText().toString();
            try{

                List<Pair<String,String>> params = new ArrayList<Pair<String,String>>();
                params.add(new Pair<String, String>("username",userT));
                params.add(new Pair<String, String>("passwort",passwT));

            }catch (){

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);
            pDialog.dismiss();
        }
    }


}
