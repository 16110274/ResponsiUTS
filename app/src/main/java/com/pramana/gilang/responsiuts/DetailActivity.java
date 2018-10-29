package com.pramana.gilang.responsiuts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static com.pramana.gilang.responsiuts.MainActivity.EXT_OVERVIEW;
import static com.pramana.gilang.responsiuts.MainActivity.EXT_TITLE;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView txtTitle = (TextView)findViewById(R.id.txt_title);
        TextView txtOverview = (TextView)findViewById(R.id.txt_overview);

        String title = getIntent().getStringExtra(EXT_TITLE);
        String overview = getIntent().getStringExtra(EXT_OVERVIEW);

        txtTitle.setText(title);
        txtOverview.setText(overview);
    }
}
