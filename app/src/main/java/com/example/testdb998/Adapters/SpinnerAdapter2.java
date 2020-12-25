package com.example.testdb998.Adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.testdb998.Models.Hobby;

public class SpinnerAdapter2 extends ArrayAdapter<Hobby> {
    public SpinnerAdapter2(@NonNull Context context, int resource) {
        super(context, resource);
    }
}
