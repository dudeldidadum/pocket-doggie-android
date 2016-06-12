package com.n3rditorium.pocketdoggie.injection;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

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
   DatabaseReference provideDatabaseReference() {
      return FirebaseDatabase.getInstance()
            .getReference();
   }

   @Provides
   FirebaseDatabase provideFirebaseDatabase() {
      return FirebaseDatabase.getInstance();
   }

   @Provides
   Picasso providePicasso(Context context) {
      return Picasso.with(context);
   }

   @Provides
   SharedPreferences provideSharedPreferences(Context context) {
      return PreferenceManager.getDefaultSharedPreferences(context);
   }
}
