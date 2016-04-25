package com.proto.searchandfound.searchandfound;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Login extends ActionBarActivity implements View.OnClickListener{

    private Button login;
    private TextView user, pass;
    private static final String LOGIN_FILE_PHP = "Login.php";
    private Map<String, String> params;
    private UrlDomain domain;
    private Response.Listener listener;
    private Response.ErrorListener error;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        domain = new UrlDomain();
        user = (TextView) findViewById(R.id.Username);
        pass = (TextView) findViewById(R.id.Passwort);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){

        if(v.equals(login)){

            listener = new Response.Listener<String>(){


                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject obj = new JSONObject(response);
                        boolean resp = obj.getBoolean("success");
                        final String TAG = "responseServer" ;
                        Log.v(TAG,obj.getString("message"));
                        if(resp){

                            Intent in = new Intent(Login.this,HomeScreen.class);
                            Login.this.startActivity(in);

                        }else{
                            String message = obj.getString("message");
                            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                            builder.setMessage(message).setNegativeButton("Retry", null).create().show();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    error = new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    };

                }
            };
            this.logUser();

        }
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

    private void logUser() {

        String username = this.user.getText().toString().trim();
        String password = this.pass.getText().toString().trim();
        String url = urlBuilder(this.domain.getURL(), this.LOGIN_FILE_PHP);
        if((username != null && username != "") && (password != null && password != "") ){
            this.params = new HashMap<>();
            this.params.put("Username", username);
            this.params.put("Passwort", password);
            this.params.put("Url", url);
            final String TAG = "url" ;
            Log.v(TAG,username);

            AndroidRequestHandler request = new AndroidRequestHandler(params,listener,error);
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(request);

        }else{

        }

    }

    public String urlBuilder(String domain, String path){

        StringBuilder builder = new StringBuilder(domain);
        String url = builder.append(path).toString();
        return url;
    }



}
