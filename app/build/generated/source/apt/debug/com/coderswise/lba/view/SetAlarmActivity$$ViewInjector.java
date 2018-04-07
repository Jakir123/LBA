// Generated code from Butter Knife. Do not modify!
package com.coderswise.lba.view;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class SetAlarmActivity$$ViewInjector {
  public static void inject(Finder finder, final com.coderswise.lba.view.SetAlarmActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689677, "field 'etAlarmDescription'");
    target.etAlarmDescription = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131689675, "field 'etAlarmTitle'");
    target.etAlarmTitle = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131689671, "field 'etLatitude'");
    target.etLatitude = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131689673, "field 'etLongitude'");
    target.etLongitude = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131689678, "field 'btnCancel' and method 'addNewAlarm'");
    target.btnCancel = (android.widget.Button) view;
    view.setOnTouchListener(
      new android.view.View.OnTouchListener() {
        @Override public boolean onTouch(
          android.view.View p0,
          android.view.MotionEvent p1
        ) {
          return target.addNewAlarm(p0, p1);
        }
      });
    view = finder.findRequiredView(source, 2131689679, "field 'btnSave' and method 'addNewAlarm'");
    target.btnSave = (android.widget.Button) view;
    view.setOnTouchListener(
      new android.view.View.OnTouchListener() {
        @Override public boolean onTouch(
          android.view.View p0,
          android.view.MotionEvent p1
        ) {
          return target.addNewAlarm(p0, p1);
        }
      });
  }

  public static void reset(com.coderswise.lba.view.SetAlarmActivity target) {
    target.etAlarmDescription = null;
    target.etAlarmTitle = null;
    target.etLatitude = null;
    target.etLongitude = null;
    target.btnCancel = null;
    target.btnSave = null;
  }
}
