package com.pramana.gilang.responsiuts;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

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

    private Toolbar toolbar;

    public static final String EXT_TITLE = "extra_title";
    public static final String EXT_OVERVIEW = "extra_overview";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<String> title = new ArrayList<String>();
        final ArrayList<String> overview = new ArrayList<String>();
        final ArrayList<String> img = new ArrayList<String>();

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
                String responseData = response.body().string();
                try{
                    JSONObject objResponse = new JSONObject(responseData);
                    JSONArray arrayResults = objResponse.getJSONArray("results");

                    for(int i = 0 ; i<arrayResults.length();i++) {
                        JSONObject objResults = new JSONObject(arrayResults.get(i).toString());

                        String titleText = (objResults.get("title").toString());
                        String overviewText = (objResults.get("overview").toString());
                        String urlImg = "http://image.tmdb.org/t/p/w185"+objResults.get("poster_path");

                        title.add(titleText);
                        overview.add(overviewText);
                        img.add(urlImg);
                    }

                    AdapterList adapter = new AdapterList(MainActivity.this, title, overview, img);
                    ListView listFilm = (ListView) findViewById(R.id.list_view);
                    listFilm.setAdapter(adapter);

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });

        //Log.d("Testing",String.valueOf(overview));


        Button btnTitle = (Button)findViewById(R.id.btn_title);
        /*btnTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this, DetailActivity.class );
                in.putExtra(EXT_TITLE, title);

                startActivity(in);
            }
        });*/
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
            Button btnSettings = findViewById(R.id.action_settings);
            btnSettings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                    startActivity(intent);
                }
            });

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
