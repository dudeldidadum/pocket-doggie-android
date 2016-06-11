package com.n3rditorium.pocketdoggie.injection;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

   private Context context;

   public ApplicationModule(Context context) {
      this.context = context.getApplicationContext();
   }

   @Provides
   Context provideContext() {
      return context;
   }

   @Provides
   SharedPreferences provideSharedPreferences(Context context) {
      return PreferenceManager.getDefaultSharedPreferences(context);
   }
}
