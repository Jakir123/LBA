// Generated code from Butter Knife. Do not modify!
package com.coderswise.lba.view;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class MainActivity$$ViewInjector {
  public static void inject(Finder finder, final com.coderswise.lba.view.MainActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689667, "field 'navigationView'");
    target.navigationView = (android.support.design.widget.BottomNavigationView) view;
  }

  public static void reset(com.coderswise.lba.view.MainActivity target) {
    target.navigationView = null;
  }
}
