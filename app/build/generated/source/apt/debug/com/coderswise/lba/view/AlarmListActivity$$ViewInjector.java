// Generated code from Butter Knife. Do not modify!
package com.coderswise.lba.view;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class AlarmListActivity$$ViewInjector {
  public static void inject(Finder finder, final com.coderswise.lba.view.AlarmListActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689663, "field 'fab' and method 'openGoogleMap'");
    target.fab = (android.support.design.widget.FloatingActionButton) view;
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.openGoogleMap(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689662, "field 'toolbar'");
    target.toolbar = (android.support.v7.widget.Toolbar) view;
    view = finder.findRequiredView(source, 2131689718, "field 'rvAlarmList'");
    target.rvAlarmList = (android.support.v7.widget.RecyclerView) view;
  }

  public static void reset(com.coderswise.lba.view.AlarmListActivity target) {
    target.fab = null;
    target.toolbar = null;
    target.rvAlarmList = null;
  }
}
