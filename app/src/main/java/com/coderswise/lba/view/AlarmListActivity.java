package com.coderswise.lba.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.coderswise.lba.R;
import com.coderswise.lba.model.ModelAlarmList;
import com.coderswise.lba.model.SQL_Helper;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class AlarmListActivity extends BaseActivity {
    @InjectView(R.id.fabAddNewAlarm)
    FloatingActionButton fab;
    @InjectView(R.id.toolbarAlarmList)
    Toolbar toolbar;
    @InjectView(R.id.rvAlarmList)
    RecyclerView rvAlarmList;

    private ArrayList<ModelAlarmList> alarmLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_list);
        setSupportActionBar(toolbar);

        ButterKnife.inject(this);
        initializations();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAddNewAlarm);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initializations() {
        LinearLayoutManager llm=new LinearLayoutManager(this);
        rvAlarmList.setLayoutManager(llm);
        alarmLists=new ArrayList<>();
        sql_helper=new SQL_Helper(this);
        setupAdapter();
    }

    private void setupAdapter() {

    }

    @OnClick(R.id.fabAddNewAlarm)
    public void openGoogleMap(View view){
        Intent gotoSetAlarmActivity=new Intent(AlarmListActivity.this,MapsActivity.class);
        gotoSetAlarmActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        gotoSetAlarmActivity.putExtra("from","AlarmListActivity");
        startActivity(gotoSetAlarmActivity);
    }

}
