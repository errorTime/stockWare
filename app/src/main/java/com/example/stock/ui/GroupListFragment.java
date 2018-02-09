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

import java.util.List;

import com.example.stock.db.Group;
import com.example.stock.model.CustomItem;
import com.example.stock.view.GroupView;

public class GroupListFragment extends Fragment implements GroupView {
    private RecyclerView mRecycleView;
    private GroupAdapter mAdapter;

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

    @Override
    public void showGroupList(List<Group> group) {

    }

    private class GroupHolder extends RecyclerView.ViewHolder {
        public GroupHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_group, parent, false));
        }
    }

    private class GroupAdapter extends RecyclerView.Adapter<GroupHolder> {
        private List<Group> mList;

        public GroupAdapter(List<Group> groups) {
            mList = groups;
        }

        @Override
        public GroupHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new GroupHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(GroupHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }
}
