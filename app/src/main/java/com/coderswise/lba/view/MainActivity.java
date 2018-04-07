package com.coderswise.lba.view;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.coderswise.lba.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity {
    private static final java.lang.String SELECTED_ITEM = "arg_selected_item";
    @InjectView(R.id.navigation)
    BottomNavigationView navigationView;
    private int mSelectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item);
                return true;
            }
        });
        MenuItem selectedItem;
        if(savedInstanceState !=null){
            mSelectedItem=savedInstanceState.getInt(SELECTED_ITEM,0);
            selectedItem=navigationView.getMenu().findItem(mSelectedItem);
        }else {
            selectedItem=navigationView.getMenu().getItem(0);
        }
        selectFragment(selectedItem);
    }

    private void selectFragment(MenuItem item) {
        Fragment fragment=null;
        switch (item.getItemId()){
            case R.id.menu_home:
                fragment=new AlarmListFragment();
                break;
            case R.id.menu_notifications:
                fragment=new SettingFragment();
                break;
            case R.id.menu_search:
                fragment=new SettingFragment();
                break;
        }
        mSelectedItem=item.getItemId();
        for (int i=0;i<navigationView.getMenu().size();i++){
            MenuItem menuItem=navigationView.getMenu().getItem(i);
            menuItem.setChecked(menuItem.getItemId()==item.getItemId());
        }
        updateToolbarText(item.getTitle());
        if(fragment!=null){
            FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container,fragment,fragment.getTag());
            transaction.commit();
        }
    }

    private void updateToolbarText(CharSequence title) {
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setTitle(title);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(SELECTED_ITEM,mSelectedItem);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        MenuItem homeItem= navigationView.getMenu().getItem(0);
        if(mSelectedItem!=homeItem.getItemId()){
            selectFragment(homeItem);
        }else {
            super.onBackPressed();
        }
    }

}
