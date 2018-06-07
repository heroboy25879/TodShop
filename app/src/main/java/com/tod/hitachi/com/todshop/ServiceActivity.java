package com.tod.hitachi.com.todshop;

import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import com.tod.hitachi.com.todshop.Utility.DrawerAdapter;
import com.tod.hitachi.com.todshop.Utility.MasterConstant;
import com.tod.hitachi.com.todshop.fragment.ListProductFragment;

public class ServiceActivity extends AppCompatActivity {

    private  String nameUserSting;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        //รับค่า จากหน้าก่อนหน้า รับชื่อผู้ใช้งานเข้ามา
        ReveiceFromMain();

//        Create Toolbar
        createToolbar();

//        Add Fragment to Activity
        addFragment(savedInstanceState);


//        create Listview
        createListview();

    }// Main Method

    private void createListview() {
        ListView listView = findViewById(R.id.listViewDrawer);
        MasterConstant masterConstant = new MasterConstant();

        DrawerAdapter drawerAdapter = new DrawerAdapter(ServiceActivity.this,
                masterConstant.getIconInts(),
                masterConstant.getTitleStrings());

        listView.setAdapter(drawerAdapter);


    }

    private void addFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contentServiceFragment,new ListProductFragment())
                    .commit();
        }
    }

    private void ReveiceFromMain() {
        nameUserSting = getIntent().getStringExtra("NameUser");
        Log.d("7JuneV1","name user ==>"+ nameUserSting );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return  true;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();

    }

    private void createToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbarService);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Service");
        getSupportActionBar().setSubtitle(nameUserSting);

        getSupportActionBar().setHomeButtonEnabled(true); //แสดงปุ่มกด
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(ServiceActivity.this,
                drawerLayout,
                R.string.open,
                R.string.close);


    }


}// Main Class
