package com.n3rditorium.pocketdoggie.core;

import android.app.Application;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.n3rditorium.pocketdoggie.injection.Injector;

public class BaseApplication extends Application {

   @Override
   public void onCreate() {
      super.onCreate();
      FirebaseAnalytics.getInstance(this);
      Injector.init(this);
      Injector.getAppComponent()
            .inject(this);
   }
}
