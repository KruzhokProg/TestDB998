package com.example.testdb998.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testdb998.Models.Hobby;
import com.example.testdb998.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SpinnerAdapter extends BaseAdapter {

    List<Hobby> hobbies;
    Context context;

    public SpinnerAdapter(List<Hobby> hobbies, Context context) {
        this.hobbies = hobbies;
        this.context = context;
    }

    @Override
    public int getCount() {
        return hobbies.size();
    }

    @Override
    public Object getItem(int position) {
        return hobbies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View view, ViewGroup parent){
        view = LayoutInflater.from(context).inflate(R.layout.spinner_item, null);
        CircleImageView imgv = view.findViewById(R.id.cimgv_hobby);
        TextView tv = view.findViewById(R.id.tvHobbyName);
//        ImageView imgv_arrow = view.findViewById(R.id.imgv_arrow);

        imgv.setImageResource(hobbies.get(position).getImage());
        tv.setText(hobbies.get(position).getName());
//        parent.setBackgroundResource(R.drawable.blue_outline);
//        if(position ==0){
//            imgv_arrow.setVisibility(View.VISIBLE);
//            imgv.setVisibility(View.GONE);
//        }
//        else{
//            imgv_arrow.setVisibility(View.INVISIBLE);
//            imgv.setVisibility(View.VISIBLE);
//        }

        return view;
    }
}
