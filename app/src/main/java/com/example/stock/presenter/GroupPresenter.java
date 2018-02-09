package com.example.stock.presenter;

import com.example.stock.db.Group;
import com.example.stock.repository.StockRepository;
import com.example.stock.view.GroupView;

import java.util.List;

public class GroupPresenter {
    private StockRepository mRepository;
    private GroupView mView;
    private GroupCallback mGroupCallback;

    public GroupPresenter(StockRepository repository, GroupView view) {
        mRepository = repository;
        mView = view;
        mGroupCallback = createGroupCallback();
    }

    public void start() {
        Group group = new Group(1, "title 01", "addon 01", 0);
        mRepository.updateGroup(group, mGroupCallback);
        group = new Group(2, "title 02", "addon 02", 0);
        mRepository.updateGroup(group, mGroupCallback);
        group = new Group(3, "title 03", "addon 03", 0);
        mRepository.updateGroup(group, mGroupCallback);
    }

    public void stop() {
        mView = null;
    }

    private GroupCallback createGroupCallback() {
        return new GroupCallback() {
            @Override
            public void onGroupListLoad(List<Group> groups) {
                mView.showGroupList(groups);
            }

            @Override
            public void onGroupUpdateOrInsert(Group group) {

            }

            @Override
            public void onDataNotAvailable() {

            }
        };
    }

}
