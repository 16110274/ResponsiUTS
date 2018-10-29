package com.pramana.gilang.responsiuts;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterList extends BaseAdapter {

    Context context;
    ArrayList<String> title;
    ArrayList<String> overview;
    LayoutInflater inflater;

    public AdapterList(Context context, ArrayList<String> title, ArrayList<String> overview) {
        this.context = context;
        this.title = title;
        this.overview = overview;
        this.inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return title.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.layout_list,null);
        TextView btnTitle = (TextView) view.findViewById(R.id.btn_title);
        TextView txtOverview = (TextView) view.findViewById(R.id.txt_overview);
        //ImageView imgPoster = (ImageView) view.findViewById(R.id.img_poster);

        btnTitle.setText(title.get(i));
        txtOverview.setText(overview.get(i));

        return view;
    }
}
