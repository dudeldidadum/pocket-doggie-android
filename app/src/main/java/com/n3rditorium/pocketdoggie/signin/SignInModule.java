package com.n3rditorium.pocketdoggie.signin;

import android.content.Context;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.n3rditorium.pocketdoggie.R;

import dagger.Module;
import dagger.Provides;

@Module
public class SignInModule {

   @Provides
   FirebaseAuth provideFirebaseAuth() {
      return FirebaseAuth.getInstance();
   }

   @Provides
   FirebaseController provideFirebaseController() {
      return new FirebaseController();
   }

   @Provides
   GoogleApiClient provideGoogleApiClient(Context context) {
      GoogleSignInOptions gso =
            new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail()
                  .requestIdToken(context.getString(R.string.google_web_client_id))
                  .build();
      // Build a GoogleApiClient with access to the Google Sign-In API and the
      // options specified by gso.
      return new GoogleApiClient.Builder(context).addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build();
   }
}
