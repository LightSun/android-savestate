package com.heaven7.android.savestate2.test;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.heaven7.android.savestate2.BundleSaveStateDelegate;
import com.heaven7.android.savestate2.JsonStateWrapper;
import com.heaven7.android.savestate2.SaveStateField;

import static android.os.Build.MANUFACTURER;

/**
 * android 开启不保留活动.
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @SaveStateField
    private int mState;
    @SaveStateField
    private Persion mPerson = new Persion("default", 1);

    private BundleSaveStateDelegate mBundleWrapper;
    private JsonStateWrapper mJsonWrapper;
    private TestHolder mHolder = new TestHolder();

    private static String JSON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBundleWrapper = new BundleSaveStateDelegate(this);

        mJsonWrapper = JsonStateWrapper.of(this, mHolder);
        mHolder.setAnchor("heaven7");
        mHolder.setVersion(1.68);
        NestHolder nestHolder = new NestHolder();
        nestHolder.setAnchor("nestHolder");
        mHolder.setNestHolder(nestHolder);
        System.out.println("MANUFACTURER: " + MANUFACTURER);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            mState = 3;
            mPerson.setAge(18);
            mPerson.setName("heaven7");
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //throw new RuntimeException("action_settings");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
       // mBundleWrapper.onSaveInstanceState(outState);
        JSON = mJsonWrapper.onSaveInstanceState();
        System.out.println("onSaveInstanceState: state = " + mState);
        System.out.println("onSaveInstanceState: JSON = " + JSON);
        //after save just reset to check data is ok or not.
        mState = 0;
        mPerson = new Persion("", 0);
        mHolder.reset();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("before onRestoreInstanceState: state = " + mState);
       // mBundleWrapper.onRestoreInstanceState(savedInstanceState);
        mJsonWrapper.onRestoreInstanceState(JSON);
        System.out.println("after onRestoreInstanceState: state = " + mState);
        System.out.println("onRestoreInstanceState Person: " + mPerson.toString());
        System.out.println("onRestoreInstanceState TestHolder: " + mHolder.toString());
    }
}
