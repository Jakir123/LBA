// Generated code from Butter Knife. Do not modify!
package com.coderswise.lba.Adapters;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class Adapter_AlarmList$ListViewHolder$$ViewInjector {
  public static void inject(Finder finder, final com.coderswise.lba.Adapters.Adapter_AlarmList.ListViewHolder target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689789, "field 'getImvAlarmLogo'");
    target.getImvAlarmLogo = (android.widget.ImageView) view;
    view = finder.findRequiredView(source, 2131689790, "field 'tvAlarmTitle'");
    target.tvAlarmTitle = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131689791, "field 'getTvAlarmDescription'");
    target.getTvAlarmDescription = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131689792, "field 'switchOnOff'");
    target.switchOnOff = (android.support.v7.widget.SwitchCompat) view;
  }

  public static void reset(com.coderswise.lba.Adapters.Adapter_AlarmList.ListViewHolder target) {
    target.getImvAlarmLogo = null;
    target.tvAlarmTitle = null;
    target.getTvAlarmDescription = null;
    target.switchOnOff = null;
  }
}
