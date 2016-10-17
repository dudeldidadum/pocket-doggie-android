package com.n3rditorium.pocketdoggie;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.n3rditorium.pocketdoggie.dog.DogActivity;
import com.n3rditorium.pocketdoggie.images.CircleTransformation;
import com.n3rditorium.pocketdoggie.injection.Injector;
import com.n3rditorium.pocketdoggie.models.ADogDeedsActivity;
import com.n3rditorium.pocketdoggie.signin.FirebaseController;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity
      implements NavigationView.OnNavigationItemSelectedListener {

   private static final int RC_SIGN_IN = 904;
   @Inject
   FirebaseAnalytics firebaseAnalytics;
   @Inject
   FirebaseController firebaseController;
   @Inject
   Picasso picasso;

   private FirebaseAnalytics mFirebaseAnalytics;

   @Override
   public void onBackPressed() {
      DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
      if (drawer.isDrawerOpen(GravityCompat.START)) {
         drawer.closeDrawer(GravityCompat.START);
      } else {
         super.onBackPressed();
      }
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
   }

   @SuppressWarnings ("StatementWithEmptyBody")
   @Override
   public boolean onNavigationItemSelected(MenuItem item) {
      // Handle navigation view item clicks here.
      int id = item.getItemId();

      Bundle params = new Bundle();
      params.putString(FirebaseAnalytics.Param.ITEM_ID, "viewId: " + id);

      String itemName = "";
      if (id == R.id.nav_profile) {
         itemName = "";
         // Handle the camera action
         if (FirebaseAuth.getInstance()
               .getCurrentUser() == null) {
            startSignIn();
         } else {
            // TODO show profile
         }
      } else if (id == R.id.nav_dog_profile) {
         itemName = "profile";
         startActivity(new Intent(this, DogActivity.class));
      } else if (id == R.id.nav_deeds) {
         itemName = "a dog deeds";
         startActivity(new Intent(this, ADogDeedsActivity.class));
      } else if (id == R.id.nav_share) {
         itemName = "share";
      } else if (id == R.id.nav_logout) {
         itemName = "logout";
         firebaseController.logout();
      }
      params.putString(FirebaseAnalytics.Param.ITEM_NAME, itemName);
      firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, params);

      DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
      drawer.closeDrawer(GravityCompat.START);
      return true;
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
      // Handle action bar item clicks here. The action bar will
      // automatically handle clicks on the Home/Up button, so long
      // as you specify a parent activity in AndroidManifest.xml.
      int id = item.getItemId();

      //noinspection SimplifiableIfStatement
      if (id == R.id.action_settings) {
         return true;
      }

      return super.onOptionsItemSelected(item);
   }

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      Injector.getAppComponent()
            .inject(this);
      setContentView(R.layout.main_activity);
      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);

      if (getIntent() != null && getIntent().getExtras() != null) {
         Log.d("PUSH", "contains key1 " + getIntent().getExtras()
               .containsKey("key1"));
      }
      firebaseController.init(this);
      firebaseController.onCreate(savedInstanceState);
      firebaseController.getCurrentUser(new FirebaseController.OnUserListener() {
         @Override
         public void notSignedIn() {
            //            startActivity(new Intent(MainActivity.this, SignInActivity.class));
            startSignIn();
         }

         @Override
         public void onSignedIn(FirebaseUser user) {
            Log.i("User", "name:  " + user.getDisplayName());
            Log.i("User", "email: " + user.getEmail());
            Log.i("User", "photo: " + user.getPhotoUrl());
            Log.i("User", "uid:   " + user.getUid());
            updateNavigationView(user);
         }
      });

      FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
      fab.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                  .setAction("Action", null)
                  .show();
         }
      });

      DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
      ActionBarDrawerToggle toggle =
            new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
                  R.string.navigation_drawer_close);
      drawer.setDrawerListener(toggle);
      toggle.syncState();

      NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
      navigationView.setNavigationItemSelectedListener(this);
   }

   @Override
   protected void onStart() {
      super.onStart();
      firebaseController.onStart();
   }

   @Override
   protected void onStop() {
      super.onStop();
      firebaseController.onStop();
   }

   private void startSignIn() {
      startActivityForResult(AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setTheme(R.style.Doggie_SignIn)
            .setProviders(AuthUI.EMAIL_PROVIDER, AuthUI.GOOGLE_PROVIDER, AuthUI.FACEBOOK_PROVIDER)
            .build(), RC_SIGN_IN);
   }

   private void updateNavigationView(FirebaseUser user) {
      NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
      View headerView = navigationView.getHeaderView(0);
      ImageView avatar = (ImageView) headerView.findViewById(R.id.img_avatar);
      if (avatar != null) {
         picasso.load(user.getPhotoUrl())
               .transform(new CircleTransformation())
               .into(avatar);
      }
      TextView name = (TextView) headerView.findViewById(R.id.txt_name);
      if (name != null) {
         name.setText(user.getDisplayName());
      }
      TextView mail = (TextView) headerView.findViewById(R.id.txt_mail);
      if (mail != null) {
         mail.setText(user.getEmail());
      }
   }
}
