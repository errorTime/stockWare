package com.example.stock.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

@Database(entities = {Group.class}, version = 2)
public abstract class StockDatabase extends RoomDatabase {
    private static StockDatabase INSTANCE;

    public abstract GroupDao groupDao();

    private static final Object sLock = new Object();
    @VisibleForTesting
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    };

    public static StockDatabase getInstance(Context ctx) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(ctx.getApplicationContext(),
                    StockDatabase.class, "stock.db")
                    .addMigrations(MIGRATION_1_2)
                    .build();
        }
        return INSTANCE;
    }
}
