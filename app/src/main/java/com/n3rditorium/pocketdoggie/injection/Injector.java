package com.n3rditorium.pocketdoggie.injection;

import android.content.Context;

import com.n3rditorium.persistence.injection.PersistenceModule;
import com.n3rditorium.pocketdoggie.signin.SignInModule;

public class Injector {

   private static AppComponent appComponent;

   public static AppComponent getAppComponent() {
      return appComponent;
   }

   public static void init(Context context) {
      appComponent = DaggerAppComponent.builder()
            .applicationModule(new ApplicationModule(context))
            .persistenceModule(new PersistenceModule(context))
            .businessModule(new BusinessModule())
            .signInModule(new SignInModule())
            .build();
   }
}
