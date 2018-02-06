package com.example.stock.ui;

import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.example.stock.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String FLAG_RELOAD = "com.examples.stock.flag.reload";
    static final String STATE_TITLE = "toolbarTitle";
    static final String STATE_COLOR = "toolbarColor";
    static final String STATE_STATUS_BAR_COLOR = "statusBarColor";
    static final String STATE_MODE = "toolbarMode";
    static final String STATE_SEARCH_STR = "toolbarSearchStr";
    static final String STATE_SELECTED_ITEM = "drawerSelectedItem";
    static final int FRAGMENT_AVE_ID = 0;
    static final int FRAGMENT_GROUP_EXES_ID = 1;
    static final int FRAGMENT_AUTO_ID = 2;
    static final int FRAGMENT_EXES_ID = 3;
    static final int FRAGMENT_STATS_ID = 4;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private NavigationView mNavigationView;
    private Integer mColor;
    private Integer mStatusBarColor;
    private CharSequence mTitle;
    private String mSearchStr;
    private MenuItem newMenuItem;
    private MenuItem searchMenuItem;
    private MenuItem removeMenuItem;
    private MenuItem impMenuItem;
    private SearchView mSearchView;
    private Integer mSelectedItem;

    public static enum Mode {FREE, NEW, NEW_SEARCH, STATS, REMOVE, SEARCH}

    ;
    public static Mode mode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //replace ActionBar Toolbar
        mToolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(mToolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mNavigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        mode = Mode.FREE;
        mSelectedItem = FRAGMENT_AVE_ID;
        mTitle = getResources().getString(R.string.activity_main_drawer_item_groups_exes);
        //check recreated destroyed instance
        if (savedInstanceState != null) {
            mColor = savedInstanceState.getInt(STATE_COLOR);
            mTitle = savedInstanceState.getCharSequence(STATE_TITLE);
            mSearchStr = savedInstanceState.getString(STATE_SEARCH_STR);
            mStatusBarColor = savedInstanceState.getInt(STATE_STATUS_BAR_COLOR);
            mToolbar.setBackgroundColor(mColor);
            changeStatusBarColor(mStatusBarColor);
            ColorStateList stateList = makeStateList(mColor);
            mNavigationView.setItemTextColor(stateList);
            mNavigationView.setItemIconTintList(stateList);
            mode = Mode.valueOf(savedInstanceState.getString(STATE_MODE));
            mSelectedItem = savedInstanceState.getInt(STATE_SELECTED_ITEM);
        }
        setTitle(mTitle);
    }

    void changeStatusBarColor(Integer color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(color);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        newMenuItem = menu.findItem(R.id.action_new);
        searchMenuItem = menu.findItem(R.id.action_search);
        removeMenuItem = menu.findItem(R.id.action_delete);
        impMenuItem = menu.findItem(R.id.action_imp_exp);
        mSearchView = (SearchView) searchMenuItem.getActionView();
        //assign the listener to search item
        MenuItemCompat.setOnActionExpandListener(searchMenuItem, expandListener);
        startMode(mode);
        if (mode == Mode.SEARCH) {
            searchMenuItem.expandActionView();
            mSearchView.setQuery(mSearchStr, true);
            mSearchView.clearFocus();
        }
        return true;
    }

    private void startMode(Mode startMode) {
        if (startMode == Mode.FREE) {
            newMenuItem.setVisible(false);
            searchMenuItem.setVisible(false);
            removeMenuItem.setVisible(false);
            impMenuItem.setVisible(false);
        }
        if (startMode == Mode.NEW) {
            newMenuItem.setVisible(true);
            searchMenuItem.setVisible(false);
            removeMenuItem.setVisible(false);
            impMenuItem.setVisible(true);
        }
        if (startMode == Mode.NEW_SEARCH) {
            newMenuItem.setVisible(true);
            searchMenuItem.setVisible(true);
            removeMenuItem.setVisible(false);
            impMenuItem.setVisible(true);
        }
        if (startMode == Mode.STATS) {
            newMenuItem.setVisible(false);
            searchMenuItem.setVisible(false);
            removeMenuItem.setVisible(false);
            impMenuItem.setVisible(true);
        }
        if (startMode == Mode.SEARCH) {
            newMenuItem.setVisible(false);
            searchMenuItem.setVisible(false);
            removeMenuItem.setVisible(false);
            impMenuItem.setVisible(true);
        }
    }

    //called when activity is start-up after onStart()
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //pass configuration changed to drawer toggles
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    //allow ActionBarToggle handle events
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) return true;
        switch (item.getItemId()) {
            case R.id.action_new:
                switch (mSelectedItem) {
                    case FRAGMENT_GROUP_EXES_ID:
                        break;
                    case FRAGMENT_AUTO_ID:
                        break;
                    case FRAGMENT_EXES_ID:
                        break;
                    case FRAGMENT_STATS_ID:
                        break;
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        selectNavItem(item);
        return true;
    }

    void selectNavItem(MenuItem item) {
        Fragment fragment = null;
        Class fragmentClass;
        mColor = ContextCompat.getColor(getApplicationContext(), R.color.material_red_500);
        mStatusBarColor = ContextCompat.getColor(getApplicationContext(), R.color.material_red_700);
        switch (item.getItemId()) {
            case R.id.nav_item_groups_exes:
                fragment = GroupListFragment.newInstance();
                mode = Mode.NEW;
                mSelectedItem = FRAGMENT_GROUP_EXES_ID;
                break;
            case R.id.nav_item_auto:
                break;
            case R.id.nav_item_exes:
                break;
            case R.id.nav_item_stats:
                break;
            default:
                fragment = AveFragment.newInstance();
                mode = Mode.FREE;
                mSelectedItem = FRAGMENT_AVE_ID;
                break;
        }
        try {
            //fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager mng = getSupportFragmentManager();
        mng.beginTransaction().replace(R.id.activity_main_container_view, fragment).commit();
        item.setChecked(true);
        //many questions to this point
        mToolbar.setBackgroundColor(mColor);
        changeStatusBarColor(mStatusBarColor);
        ColorStateList stateList = makeStateList(mColor);
        mNavigationView.setItemTextColor(stateList);
        mNavigationView.setItemIconTintList(stateList);
        setTitle(item.getTitle());
        mTitle = item.getTitle();
        mDrawerLayout.closeDrawers();
        startMode(mode);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //save state
        if (mTitle == null) mTitle = getTitle();
        outState.putCharSequence(STATE_TITLE, mTitle);
        if (mColor == null)
            mColor = ContextCompat.getColor(getApplicationContext(), R.color.material_red_500);
        if (mStatusBarColor == null)
            mStatusBarColor = ContextCompat.getColor(getApplicationContext(), R.color.material_red_700);
        outState.putInt(STATE_COLOR, mColor);
        outState.putInt(STATE_STATUS_BAR_COLOR, mStatusBarColor);
        outState.putString(STATE_MODE, mode.name());
        if (mSearchView != null)
            outState.putString(STATE_SEARCH_STR, mSearchView.getQuery().toString());
        outState.putInt(STATE_SELECTED_ITEM, mSelectedItem);
        super.onSaveInstanceState(outState);
    }

    private ColorStateList makeStateList(int color) {
        int[][] states = new int[][]{
                new int[]{-android.R.attr.state_checked},  // unchecked
                new int[]{android.R.attr.state_checked},   // checked
                new int[]{}                                // default
        };

        int[] colors = new int[]{
                ContextCompat.getColor(getApplicationContext(), R.color.black),
                color,
                ContextCompat.getColor(getApplicationContext(), R.color.black),
        };
        return new ColorStateList(states, colors);
    }

    MenuItemCompat.OnActionExpandListener expandListener = new MenuItemCompat.OnActionExpandListener() {
        @Override
        public boolean onMenuItemActionExpand(MenuItem item) {
            mode = Mode.SEARCH;
            startMode(mode);
            return true;
        }

        @Override
        public boolean onMenuItemActionCollapse(MenuItem item) {
            mode = Mode.NEW_SEARCH;
            startMode(mode);
            return true;
        }
    };

}
