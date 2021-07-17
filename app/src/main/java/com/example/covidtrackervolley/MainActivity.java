package com.example.covidtrackervolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;



public class MainActivity extends AppCompatActivity {
    private TextView totalCasesWorld, totalDeathsWorld, totalRecoveredWorld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalCasesWorld = findViewById(R.id.newsCasesWorld);
        totalDeathsWorld = findViewById(R.id.newsDeathsWorld);
        totalRecoveredWorld = findViewById(R.id.newsRecoveredWorld);
        getData();
    }

    private void getData() {
        String myUrl = "https://corona.lmao.ninja/v2/all";
        StringRequest myRequest = new StringRequest(Request.Method.GET, myUrl,
                response -> {
                    try {
                        JSONObject myJsonObject = new JSONObject(response);
                        totalCasesWorld.setText(myJsonObject.getString("cases"));
                        totalRecoveredWorld.setText(myJsonObject.getString("recovered"));
                        totalDeathsWorld.setText(myJsonObject.getString("deaths"));

                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                },
                error -> Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show());


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(myRequest);

    }
}