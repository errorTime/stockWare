package com.example.stock.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stock.R;

public class GroupListFragment extends Fragment {
    private RecyclerView mRecycleView;

    public static GroupListFragment newInstance() {
        Bundle args = new Bundle();
        GroupListFragment fragment = new GroupListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_group_list, container, false);
        mRecycleView = (RecyclerView) v.findViewById(R.id.group_recycler_view);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return v;
    }

    private class GroupHolder extends RecyclerView.ViewHolder {
        public GroupHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_group, parent, false));
        }
    }

    private class GroupAdapter extends RecyclerView.Adapter<GroupHolder>{

        @Override
        public GroupHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(GroupHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}
