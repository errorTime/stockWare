package com.example.stock.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

public class AveActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context ctx, int id, int id_group, int action) {
        Intent intent = new Intent(ctx, AveActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return AveFragment.newInstance();
    }
}
