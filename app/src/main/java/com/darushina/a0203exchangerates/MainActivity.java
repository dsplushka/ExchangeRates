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
        init();
    }

    private void init() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                loadJSONFromURL(JSON_URL);
            }
        };
        Thread secThread = new Thread(runnable);
        secThread.start();
    }

    private void loadJSONFromURL(String url){
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
                            JSONObject azn = valute.getJSONObject("AZN");
                            JSONObject gbp = valute.getJSONObject("GBP");
                            JSONObject amd = valute.getJSONObject("AMD");
                            JSONObject byn = valute.getJSONObject("BYN");
                            JSONObject bgn = valute.getJSONObject("BGN");
                            JSONObject brl = valute.getJSONObject("BRL");
                            JSONObject huf = valute.getJSONObject("HUF");
                            JSONObject hkd = valute.getJSONObject("HKD");
                            JSONObject dkk = valute.getJSONObject("DKK");
                            JSONObject usd = valute.getJSONObject("USD");
                            JSONObject eur = valute.getJSONObject("EUR");
                            JSONObject inr = valute.getJSONObject("INR");
                            JSONObject kzt = valute.getJSONObject("KZT");
                            JSONObject cad = valute.getJSONObject("CAD");
                            JSONObject kgs = valute.getJSONObject("KGS");
                            JSONObject cny = valute.getJSONObject("CNY");
                            JSONObject mdl = valute.getJSONObject("MDL");
                            JSONObject nok = valute.getJSONObject("NOK");
                            JSONObject pln = valute.getJSONObject("PLN");
                            JSONObject ron = valute.getJSONObject("RON");
                            JSONObject xdr = valute.getJSONObject("XDR");
                            JSONObject sgd = valute.getJSONObject("SGD");
                            JSONObject tjs = valute.getJSONObject("TJS");
                            JSONObject try_ = valute.getJSONObject("TRY");
                            JSONObject tmt = valute.getJSONObject("TMT");
                            JSONObject uzs = valute.getJSONObject("UZS");
                            JSONObject uah = valute.getJSONObject("UAH");
                            JSONObject czk = valute.getJSONObject("CZK");
                            JSONObject sek = valute.getJSONObject("SEK");
                            JSONObject chf = valute.getJSONObject("CHF");
                            JSONObject zar = valute.getJSONObject("ZAR");
                            JSONObject krw = valute.getJSONObject("KRW");
                            JSONObject jpy = valute.getJSONObject("JPY");

                            JSONObject[] valutes = {aud, azn, gbp, amd, byn, bgn, brl, huf, hkd, dkk, usd, eur, inr, kzt, cad, kgs, cny, mdl, nok, pln, ron, xdr, sgd, tjs, try_, tmt, uzs, uah, czk, sek, chf, zar, krw, jpy};
                            for(int i = 0; i < valutes.length; i++) {
                                String charCodeItem = valutes[i].getString("CharCode");
                                String valueItem = valutes[i].getString("Value");
                                Currency rateItem = new Currency(charCodeItem, valueItem);
                                listItems.add(rateItem);
                            }

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