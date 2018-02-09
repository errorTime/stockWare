package com.example.stock.repository;

import com.example.stock.db.Group;
import com.example.stock.db.GroupDataSource;
import com.example.stock.presenter.GroupCallback;

import java.lang.ref.WeakReference;
import java.util.List;

public class StockRepository {
    private AppExecutors mAppExecutors;
    private GroupDataSource mGroupDataSource;
    private List<Group> mCachedGroupList;
    private Group mCachedGroup;

    public StockRepository(AppExecutors appExecutors, GroupDataSource groupDataSource) {
        mAppExecutors = appExecutors;
        mGroupDataSource = groupDataSource;
    }

    public void getGroups(GroupCallback callback) {
        final WeakReference<GroupCallback> groupCallback = new WeakReference<GroupCallback>(callback);
        mAppExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<Group> groupList = mGroupDataSource.getGroups();
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        final GroupCallback getCallback = groupCallback.get();
                        if (getCallback == null) return;
                        if (groupList == null) getCallback.onDataNotAvailable();
                        else {
                            mCachedGroupList = groupList;
                            getCallback.onGroupListLoad(mCachedGroupList);
                        }
                    }
                });
            }
        });
    }

    public void updateGroup(final Group group, GroupCallback callback){
        final WeakReference<GroupCallback> groupCallback = new WeakReference<GroupCallback>(callback);
        mAppExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mGroupDataSource.insertOrUpdateGroup(group);
                mCachedGroup = group;
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        GroupCallback getCallback = groupCallback.get();
                        if (getCallback != null) getCallback.onGroupUpdateOrInsert(group);
                    }
                });
            }
        });
    }
}
