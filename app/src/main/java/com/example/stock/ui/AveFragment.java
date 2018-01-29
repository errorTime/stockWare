package com.example.stock.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stock.R;

public class AveFragment extends Fragment {

    public static AveFragment newInstance() {
        Bundle args = new Bundle();
        AveFragment fragment = new AveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ave, container, false);
        return v;
    }
}
