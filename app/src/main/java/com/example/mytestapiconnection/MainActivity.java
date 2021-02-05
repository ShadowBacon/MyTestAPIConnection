package com.example.mytestapiconnection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.example.mytestapiconnection.warte;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    String weather;
    String movie;
    String genre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickStart(View view) {

        WeatherAPIRequest();
        TheMovieDBRequest();

        TextView Weather = findViewById(R.id.tVWeather);
        TextView Movie = findViewById(R.id.tVMovie);

        //ConstraintLayout background = findViewById(R.id.background);
        //background.setBackground();

        Weather.setText(weather);
        Movie.setText(movie);

    }

    public void WeatherAPIRequest(){

        EditText plz = findViewById(R.id.editTextTextPersonName);

        String zip = plz.getText().toString();

        String Url =  "https://api.openweathermap.org/data/2.5/weather?zip="+ zip + ",de&appid=a1bf60f16e9591de80543884372c8cd8";
                //"https://api.openweathermap.org/data/2.5/weather?id=" + stadt + "&appid=a1bf60f16e9591de80543884372c8cd8";
        String wetter;
        //System.out.println(Url);

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET,Url,null, new Response.Listener<JSONObject>() {

            public void onResponse(JSONObject response) {
                try {
                    //JSONObject main_object = response.getJSONObject("main");
                    JSONArray array = response.getJSONArray("weather");
                    JSONObject object = array.getJSONObject(0);

                    String main = object.getString("description");
                    //System.out.println(object);

                    System.out.println(main);
                    weather = main;
                    TextView Weather = findViewById(R.id.tVWeather);
                    Weather.setText(main);

                    String wetter = main;

                }catch(JSONException e)
                {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error){

            }
        }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jor);



    }


    public void TheMovieDBRequest() {

        String wetter = "scattered clouds";

        System.out.println(wetter);

        switch(wetter){
            case("clear sky"):
                genre = "35";
                break;
            case("few clouds"):
                genre = "12";
                break;
            case("scattered clouds"):
                genre = "99";
                break;
            case("broken clouds"):
                genre = "10751";
                break;
            case("shower rain"):
                genre = "18";
                break;
            case("rain"):
                genre = "10";
                break;
            case("thunderstorm"):
                genre = "80";
                break;
            case("snow"):
                genre = "16";
                break;
            case("mist"):
                genre = "27";
                break;
            default:
                    break;
        }




        Random r = new Random();
        int low = 0; int high = 19;
        int result = r.nextInt(high-low) + low;

        String Url =  "https://api.themoviedb.org/3/discover/movie?api_key=ac7c6e47f9f97ddc2fe955d0904a36a4&with_genres=" + genre + "&sort_by=vote_average.desc&vote_count.gte=10&include_adult=true";

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET,Url,null, new Response.Listener<JSONObject>() {

            public void onResponse(JSONObject response) {
                try {
                    //JSONObject main_object = response.getJSONObject("");
                    JSONArray array = response.getJSONArray("results");
                    JSONObject object = array.getJSONObject(result);

                    String main = object.getString("title");
                    //System.out.println(object);

                    System.out.println(main);

                    movie = main;
                    TextView Movie = findViewById(R.id.tVMovie);
                    Movie.setText(movie);


                }catch(JSONException e)
                {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error){

            }
        }
        );

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jor);

    }

    public void clickClear(View view){
    TextView wetter = findViewById(R.id.tVWeather);
    TextView film = findViewById(R.id.tVMovie);
    EditText plz = findViewById(R.id.editTextTextPersonName);
    wetter.setText("");
    film.setText("");
    plz.setText("");
    }



}