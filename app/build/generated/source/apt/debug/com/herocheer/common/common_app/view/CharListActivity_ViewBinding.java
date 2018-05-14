// Generated code from Butter Knife. Do not modify!
package com.herocheer.common.common_app.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.aspsine.irecyclerview.IRecyclerView;
import com.herocheer.common.commonapp.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CharListActivity_ViewBinding implements Unbinder {
  private CharListActivity target;

  @UiThread
  public CharListActivity_ViewBinding(CharListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CharListActivity_ViewBinding(CharListActivity target, View source) {
    this.target = target;

    target.iRecyclerView = Utils.findRequiredViewAsType(source, R.id.iRecyclerView, "field 'iRecyclerView'", IRecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CharListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.iRecyclerView = null;
  }
}
