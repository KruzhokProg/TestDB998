package com.example.testdb998.Adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testdb998.R;

public class RvViewHolder extends RecyclerView.ViewHolder {

    ImageView imgvItem;
    TextView tvItem;

    public RvViewHolder(@NonNull View itemView) {
        super(itemView);

        imgvItem = itemView.findViewById(R.id.imgvItem);
        tvItem = itemView.findViewById(R.id.tvItem);
    }
}
