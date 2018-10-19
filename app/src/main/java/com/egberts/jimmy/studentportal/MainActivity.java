package com.egberts.jimmy.studentportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PortalAdapter.PortalClickListener {

    public static final int REQUEST_CODE = 1234;
    public static final String PORTAL_CODE = "portal";
    public static final String URL_CODE = "URL";
    public PortalAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private List<Portal> mPortals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPortals = new ArrayList<>();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //When the fab gets clicked go to the addportal screen and start an activityForResult with an request code that gets returned when the activity is finished
                Intent intent = new Intent(MainActivity.this, AddPortal.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setHasFixedSize(true);

        updateUI();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        //If the returned result is ok and the resquest code is the correct one(This is important with multiple results) get the object and add it to the list then update the UI
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Portal portal = data.getParcelableExtra(PORTAL_CODE);
            mPortals.add(portal);
            //notify adapter
            updateUI();
        }
    }

    private void updateUI() {
        if (mAdapter == null) {
            mAdapter = new PortalAdapter(this, mPortals, this);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    public void reminderOnClick(int i) {
        Intent intent = new Intent(MainActivity.this, WebView.class);
        intent.putExtra(URL_CODE, mPortals.get(i).getmUrl());
        startActivity(intent);
    }
}
