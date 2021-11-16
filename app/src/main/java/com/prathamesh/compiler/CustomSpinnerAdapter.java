package com.prathamesh.compiler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomSpinnerAdapter extends BaseAdapter {

    Context context;;
    int[] icons;
    String[] languages;
    LayoutInflater layoutInflater;

    public CustomSpinnerAdapter(Context applicationContext, int[] icons, String[] languages){
        this.context = applicationContext;
        this.icons = icons;
        this.languages = languages;
        layoutInflater =(LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() { return icons.length; }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView =layoutInflater.inflate(R.layout.custom_spinner_layout,null);
        ImageView icon = convertView.getRootView().findViewById(R.id.IVSpinner);
        TextView text = convertView.getRootView().findViewById(R.id.TVSpinner);
        icon.setImageResource(icons[position]);
        text.setText(languages[position]);
        return convertView;
    }
}
