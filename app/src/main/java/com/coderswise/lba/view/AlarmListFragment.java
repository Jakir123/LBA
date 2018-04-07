package com.coderswise.lba.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coderswise.lba.Adapters.Adapter_AlarmList;
import com.coderswise.lba.R;
import com.coderswise.lba.model.ModelAlarmList;
import com.coderswise.lba.model.SQL_Helper;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlarmListFragment extends Fragment {
    @InjectView(R.id.fabAddNewAlarm)
    FloatingActionButton fab;
    @InjectView(R.id.rvAlarmList)
    RecyclerView rvAlarmList;
    private ArrayList<ModelAlarmList> alarmLists;
    private SQL_Helper sql_helper;
    private Adapter_AlarmList adapterAlarmList;

    public AlarmListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm_list, container, false);
        ButterKnife.inject(this, view);
        initializations();
        return view;
    }

    private void initializations() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rvAlarmList.setLayoutManager(llm);
        alarmLists = new ArrayList<>();
        sql_helper = new SQL_Helper(getActivity());
        setupAdapter();
    }

    private void setupAdapter() {
        alarmLists = sql_helper.getAlarms();
        adapterAlarmList=new Adapter_AlarmList(alarmLists,getActivity());
        rvAlarmList.setAdapter(adapterAlarmList);
    }

    @OnClick(R.id.fabAddNewAlarm)
    public void openGoogleMap(View view) {
        Intent gotoSetAlarmActivity = new Intent(getActivity(), MapsActivity.class);
        gotoSetAlarmActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        gotoSetAlarmActivity.putExtra("from", "AlarmListActivity");
        startActivity(gotoSetAlarmActivity);
    }
}
