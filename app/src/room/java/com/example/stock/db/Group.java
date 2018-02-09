package com.example.stock.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "tbl_group")
public class Group {
    @PrimaryKey
    @ColumnInfo(name = "id")
    public int mId;

    @ColumnInfo(name = "title")
    public String mTitle;

    @ColumnInfo(name = "addon")
    public String mAddon;

    @ColumnInfo(name = "fixed")
    public int mFixed;

    public Group(int id, String title, String addon, int fixed) {
        this.mId = id;
        this.mTitle = title;
        this.mAddon = addon;
        this.mFixed = fixed;
    }
}
