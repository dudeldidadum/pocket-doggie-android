package com.n3rditorium.pocketdoggie.core;

import android.app.Application;

import com.n3rditorium.pocketdoggie.injection.Injector;

public class BaseApplication extends Application {

   @Override
   public void onCreate() {
      super.onCreate();
      Injector.init(this);
   }
}
