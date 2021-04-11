package com.unasat.sr.moviereview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unasat.sr.moviereview.dto.TvShowDto;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View view) {
        EditText username = (EditText) findViewById(R.id.userName);
        String usernameValue = String.valueOf(username.getText());

        EditText password = (EditText) findViewById(R.id.userPassword);
        String passwordValue = String.valueOf(password.getText());

        String notification = "";

        if (usernameValue.isEmpty() || passwordValue.isEmpty()) {
            notification = "Vul a.u.b. eerst een gebruikersnaam en wachtwoord in.";
        } else if (usernameValue.equals("Clyde") && passwordValue.equals("1234")) {
            loadHomescreenActivity(usernameValue);
            notification = "Welcome Clyde";
        } else {
            notification = "Username of password niet correct!";
        }
        Toast.makeText(this, notification, Toast.LENGTH_SHORT).show();

    }


    public void loadHomescreenActivity(String username) {
        Intent intent = new Intent(this, Homescreen.class);
        startActivity(intent);
    }


    public void onClickContinue(View v) {
        Intent intent = new Intent(this, Homescreen.class);
        startActivity(intent);
    }

    public void onClickAccount(View view) {
        Intent intent = new Intent(this, CreateAccount.class);
        startActivity(intent);
    }


//    @Override
//    public void onBackPressed() {
//        if () {
//
//        } else {
//            super.onBackPressed();
//        }
//    }
}