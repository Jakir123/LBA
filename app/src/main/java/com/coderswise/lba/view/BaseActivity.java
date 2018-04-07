package com.coderswise.lba.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import com.coderswise.lba.R;
import com.coderswise.lba.model.SQL_Helper;

public class BaseActivity extends AppCompatActivity {

    protected SQL_Helper sql_helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void showToast(String msg){
        Toast toast = Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
