package com.coderswise.lba.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.coderswise.lba.R;
import com.coderswise.lba.model.ModelAlarmList;
import com.coderswise.lba.model.SQL_Helper;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnTouch;

public class SetAlarmActivity extends BaseActivity {
    @InjectView(R.id.etAlarmDescription)
    EditText etAlarmDescription;
    @InjectView(R.id.etAlarmTitle)
    EditText etAlarmTitle;
    @InjectView(R.id.etLatitude)
    EditText etLatitude;
    @InjectView(R.id.etLongitude)
    EditText etLongitude;
    @InjectView(R.id.btnCancel)
    Button btnCancel;
    @InjectView(R.id.btnSave)
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);
        ButterKnife.inject(this);
        sql_helper=new SQL_Helper(this);
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            etLatitude.setText(extras.getString("Lat"));
            etLatitude.setFocusable(false);
            etLongitude.setText(extras.getString("Long"));
            etLongitude.setFocusable(false);
        }
    }

    @OnTouch({R.id.btnSave,R.id.btnCancel})
    public boolean addNewAlarm(View view, MotionEvent event){
        switch (view.getId()){
            case R.id.btnCancel:
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    btnCancel.getBackground().setAlpha(100);
                }
                else if(event.getAction()==MotionEvent.ACTION_UP){
                    btnCancel.getBackground().setAlpha(250);
                    finish();
                }
                break;
            case R.id.btnSave:
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    btnSave.getBackground().setAlpha(100);
                }
                else if (event.getAction()==MotionEvent.ACTION_UP){
                    btnSave.getBackground().setAlpha(250);
                    String lat=etLatitude.getText().toString().trim();
                    String lon=etLongitude.getText().toString().trim();
                    String title=etAlarmTitle.getText().toString().trim();
                    String description=etAlarmDescription.getText().toString().trim();

                    if(title.equalsIgnoreCase("")){
                        etAlarmTitle.setError("Enter Alarm Title Here.");
                        return false;
                    }
                    if (description.equalsIgnoreCase("")){
                        etAlarmDescription.setError("Enter Alarm Description Here.");
                        return false;
                    }
                    ModelAlarmList alarm=new ModelAlarmList(title,description,lat,lon,1);
                    long insert=sql_helper.addNewAlarm(alarm);
                    if(insert>=0){
                        showToast("New Alarm Created!");
                        Intent gotoAlarmList=new Intent(this,MainActivity.class);
                        gotoAlarmList.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(gotoAlarmList);
                        finish();
                    }else {
                        showToast("Insertion Failed");
                    }
                }
                break;
        }
        return true;
    }

}
