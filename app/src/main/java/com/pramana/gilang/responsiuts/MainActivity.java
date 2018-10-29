package com.pramana.gilang.responsiuts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private ListView listFilm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<String> title = new ArrayList<String>();
        final ArrayList<String> overview = new ArrayList<String>();

        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=92d0674ae46c5b33877b1822c884894c";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(getApplicationContext(),
                        "Tidak dapat terhubung server", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseData = response.body().string();
                try{
                    JSONObject objResponse = new JSONObject(responseData);
                    final JSONArray arrayResults = objResponse.getJSONArray("results");

                    for(int i = 0 ; i<20;i++) {
                        final JSONObject objResults = new JSONObject(arrayResults.get(i).toString());
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    //int index = Integer.parseInt(objResults.get("id").toString());
                                    String titleText = (objResults.get("title").toString());
                                    String overviewText = (objResults.get("overview").toString());
                                    //String urlImg = "http://image.tmdb.org/t/p/w185"+objResults.get("poster_path");
                                    title.add(titleText);
                                    overview.add(overviewText);
                                    //Glide.with(MainActivity.this).load(urlImg).into(img);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
                //Log.d("cobaCoba", String.valueOf(title));
            }
        });
        //Log.d("cobaCoba", String.valueOf(title));

         String[] strTitle = new String[]{"Venom", "Halloween", "A Star Is Born", "Incredibles 2",
                 "Alpha", "Night School", "Mile 22", "The Predator", "The Nun",
                 "Johnny English Strikes Again", "First Man", "A.X.L.", "Kung Fu League",
                 "BlacKkKlansman", "Christopher Robin", "Smallfoot", "Skyscraper", "Air Strike",
                 "Goosebumps 2: Haunted Halloween"};
        listFilm = findViewById(R.id.list_view);
        AdapterList adapter = new AdapterList(this, strTitle, overview);
        listFilm.setAdapter(adapter);

        
    }
}
