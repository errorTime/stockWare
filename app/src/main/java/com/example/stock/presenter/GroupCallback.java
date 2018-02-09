package com.example.stock.presenter;

import android.support.annotation.MainThread;

import com.example.stock.db.Group;

import java.util.List;

public interface GroupCallback {
    @MainThread
    void onGroupListLoad(List<Group> groups);

    @MainThread
    void onGroupUpdateOrInsert(Group group);

    @MainThread
    void onDataNotAvailable();
}
