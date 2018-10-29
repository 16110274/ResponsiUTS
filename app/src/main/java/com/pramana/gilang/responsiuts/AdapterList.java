package com.pramana.gilang.responsiuts;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;

import java.util.ArrayList;

public class AdapterList extends ArrayAdapter<String> {

    Context context;
    ArrayList<String> title;
    ArrayList<String> overview;
    ArrayList<String> img;

    public AdapterList(Context context, ArrayList<String> title, ArrayList<String> overview, ArrayList<String> img) {
        super(context, 0, title);
        this.title = title;
        this.overview = overview;
        this.img = img;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.layout_list, viewGroup, false);
        }

        TextView btnTitle = (TextView) view.findViewById(R.id.btn_title);
        TextView txtOverview = (TextView) view.findViewById(R.id.txt_overview);
        ImageView imgPoster = (ImageView) view.findViewById(R.id.img_poster);

        btnTitle.setText(title.get(i));
        txtOverview.setText(overview.get(i));
        Glide.with(context).load(img).into(imgPoster);

        return view;
    }
}
