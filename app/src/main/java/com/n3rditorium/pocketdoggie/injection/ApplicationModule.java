package com.n3rditorium.pocketdoggie.injection;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

   private Context context;

   public ApplicationModule(Context context) {
      this.context = context.getApplicationContext();
   }

   @Provides
   @Singleton
   Context provideContext() {
      return context;
   }

   @Provides
   DatabaseReference provideDatabaseReference(FirebaseDatabase firebaseDatabase) {
      return firebaseDatabase.getReference();
   }

   @Provides
   @Singleton
   FirebaseAnalytics provideFirebaseAnalytics(Context context) {
      FirebaseAnalytics analytics = FirebaseAnalytics.getInstance(context);
      return analytics;
   }

   @Provides
   @Singleton
   FirebaseDatabase provideFirebaseDatabase() {
      FirebaseDatabase database = FirebaseDatabase.getInstance();
      database.setPersistenceEnabled(true);
      return database;
   }

   @Provides
   @Singleton
   Picasso providePicasso(Context context) {
      return Picasso.with(context);
   }

   @Provides
   @Singleton
   SharedPreferences provideSharedPreferences(Context context) {
      return PreferenceManager.getDefaultSharedPreferences(context);
   }
}
