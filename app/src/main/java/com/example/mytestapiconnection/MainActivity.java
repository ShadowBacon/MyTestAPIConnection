package com.example.mytestapiconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    String city;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickStart(View view) throws UnirestException {

       String Url =  "https://api.openweathermap.org/data/2.5/weather?id=" + city + "&appid=a1bf60f16e9591de80543884372c8cd8";

       JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET,Url,null, new Response.Listener<JSONObject>() {
           public void onResponse(JSONObject response) {
               try {
                   JSONObject main_object = response.getJSONObject("main");
                   JSONArray array = response.getJSONArray("weather");
                   JSONObject object = array.getJSONObject(0);

                   String main = object.getString("main");
                   System.out.println(object);
                   System.out.println(main);

               }catch(JSONException e)
               {
                   e.printStackTrace();
               }
           }

       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error){

           }
       }
               );

        unogsNGRequest();

    }

    public void unogsNGRequest(){

        String Url =  "https://unogs-unogs-v1.p.rapidapi.com/aaapi.cgi?q=%7Bquery%7D-!%7Bsyear%7D%2C%7Beyear%7D-!%7Bsnfrate%7D%2C%7Benfrate%7D-!%7Bsimdbrate%7D%2C%7Beimdbrate%7D-!%7Bgenreid%7D-!%7Bvtype%7D-!%7Baudio%7D-!%7Bsubtitle%7D-!%7Bimdbvotes%7D-!%7Bdownloadable%7D&t=ns&cl=%7Bclist%7D&st=adv&ob=%7Bsortby%7D&p=%7Bpage%7D&sa=%7Bandor%7D";

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET,Url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject main_object = response.getJSONObject("main");
                    JSONArray array = response.getJSONArray("weather");
                    JSONObject object = array.getJSONObject(0);

                    String main = object.getString("main");
                    System.out.println(object);
                    System.out.println(main);

                }catch(JSONException e)
                {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){

            }
        }
        );
    }



    public void clickBerlin(View view){
        city = "2950157"; /*Berlin Land?*/
    }

    public void clickBrandenburg(View view){
        city = "2945356"; /*Brandenburg*/
    }




}