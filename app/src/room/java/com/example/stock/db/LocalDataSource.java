package com.example.stock.db;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import java.util.List;

public class LocalDataSource implements GroupDataSource {
    private static volatile LocalDataSource INSTANCE;
    private GroupDao mGroupDao;

    @VisibleForTesting
    LocalDataSource(GroupDao groupDao) {
        mGroupDao = groupDao;
    }

    public static LocalDataSource getInstance(@NonNull Context ctx) {
        if (INSTANCE == null) {
            synchronized (LocalDataSource.class) {
                if (INSTANCE == null) {
                    StockDatabase database = StockDatabase.getInstance(ctx);
                    INSTANCE = new LocalDataSource(database.groupDao());
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public List<Group> getGroups() {
        return mGroupDao.getGroups();
    }

    @Override
    public void insertOrUpdateGroup(Group group) {
        mGroupDao.insertOrUpdateGroup(group);
    }

    @Override
    public Group getGroup(int id) {
        return mGroupDao.getGroup(id);
    }
}
