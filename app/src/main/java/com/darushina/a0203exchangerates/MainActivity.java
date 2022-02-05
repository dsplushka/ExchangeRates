package com.darushina.a0203exchangerates;

import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String JSON_URL = "https://www.cbr-xml-daily.ru/daily_json.js";
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        loadJSONFromURL(JSON_URL);
    }

    private void  loadJSONFromURL(String url){
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(ListView.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.INVISIBLE);
                        ArrayList<Currency> listItems = new ArrayList<>();
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONObject valute = object.getJSONObject("Valute");

                            JSONObject aud = valute.getJSONObject("AUD");
                            String charCodeAUD = aud.getString("CharCode");
                            String valueAUD = aud.getString("Value");
                            Currency rateAUD = new Currency(charCodeAUD, valueAUD);
                            listItems.add(rateAUD);

                            JSONObject azn = valute.getJSONObject("AZN");
                            String charCodeAZN = azn.getString("CharCode");
                            String valueAZN = azn.getString("Value");
                            Currency rateAZN = new Currency(charCodeAZN, valueAZN);
                            listItems.add(rateAZN);
                            ListAdapter adapter = new ListViewAdapter(getApplicationContext(),R.layout.row,R.id.textViewCharCode,listItems);
                            listView.setAdapter(adapter);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}