package com.n3rditorium.pocketdoggie.core;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public abstract class AbstractController implements Lifecycle {

   protected AppCompatActivity activity;

   public Context getContext() {
      return activity.getApplicationContext();
   }

   public abstract void init(AppCompatActivity activity);

   @Override
   public void onCreate(Bundle savedInstanceState) {

   }

   @Override
   public void onDestory() {

   }

   @Override
   public void onPause() {

   }

   @Override
   public void onResume() {

   }

   @Override
   public void onStart() {

   }

   @Override
   public void onStop() {

   }


}
