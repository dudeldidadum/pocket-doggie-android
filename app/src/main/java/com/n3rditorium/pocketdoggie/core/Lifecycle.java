package com.n3rditorium.pocketdoggie.core;

import android.os.Bundle;

public interface Lifecycle {

   void onCreate(Bundle savedInstanceState);

   void onDestory();

   void onPause();

   void onResume();

   void onStart();

   void onStop();
}
