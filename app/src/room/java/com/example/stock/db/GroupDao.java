package com.example.stock.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface GroupDao {
    @Query("SELECT * from tbl_group")
    List<Group> getGroups();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrUpdateGroup(Group group);

    @Query("SELECT * FROM tbl_group WHERE id = :id")
    Group getGroup(int id);
}
