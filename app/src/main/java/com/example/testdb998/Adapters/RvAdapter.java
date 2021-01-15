package com.example.testdb998.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testdb998.Models.RvModel;
import com.example.testdb998.R;

import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvViewHolder> {

    private List<RvModel> data;
    private Context context;

    public RvAdapter(List<RvModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item, parent, false);
        return new RvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RvViewHolder holder, int position) {
        holder.tvItem.setText(data.get(position).getName());
        holder.imgvItem.setImageResource(data.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        if(data!=null) {
            return data.size();
        }
        return 0;
    }
}
