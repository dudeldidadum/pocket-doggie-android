package com.n3rditorium.pocketdoggie.signin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.n3rditorium.pocketdoggie.core.AbstractController;
import com.n3rditorium.pocketdoggie.injection.Injector;

import javax.inject.Inject;

public class FirebaseController extends AbstractController {

   private static final String TAG = FirebaseController.class.getSimpleName();
   @Inject
   FirebaseAuth firebaseAuth;
   private FirebaseAuth.AuthStateListener authListener;

   @Override
   public void init(AppCompatActivity activity) {
      this.activity = activity;
      Injector.getAppComponent()
            .inject(this);
   }

   @Override
   public void onCreate(Bundle savedInstanceState) {
      authListener = new FirebaseAuth.AuthStateListener() {
         @Override
         public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
               // User is signed in
               Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
            } else {
               // User is signed out
               Log.d(TAG, "onAuthStateChanged:signed_out");
            }
         }
      };
   }

   @Override
   public void onStart() {
      firebaseAuth.addAuthStateListener(authListener);
   }

   @Override
   public void onStop() {
      if (authListener != null) {
         firebaseAuth.removeAuthStateListener(authListener);
      }
   }

   public void registerWithEmail(final String email, final String password) {
      firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                  Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                  // If sign in fails, display a message to the user. If sign in succeeds
                  // the auth state listener will be notified and logic to handle the
                  // signed in user can be handled in the listener.
                  if (!task.isSuccessful()) {
                     Toast.makeText(getContext(), "Authentication failed.", Toast.LENGTH_SHORT)
                           .show();
                  }
                  signInWithEmail(email, password);
               }
            });
   }

   public void signInWithEmail(String email, String password) {
      firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                  Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                  // If sign in fails, display a message to the user. If sign in succeeds
                  // the auth state listener will be notified and logic to handle the
                  // signed in user can be handled in the listener.
                  if (!task.isSuccessful()) {
                     Log.w(TAG, "signInWithEmail", task.getException());
                     Toast.makeText(getContext(), "Authentication failed.", Toast.LENGTH_SHORT)
                           .show();
                     // TODO handle failure
                  }
                  // TODO handle success
               }
            });
   }

   public void signInWithGoogle(GoogleSignInAccount account) {
      AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
      firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                  Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                  // If sign in fails, display a message to the user. If sign in succeeds
                  // the auth state listener will be notified and logic to handle the
                  // signed in user can be handled in the listener.
                  if (!task.isSuccessful()) {
                     Log.w(TAG, "signInWithCredential", task.getException());
                     Toast.makeText(getContext(), "Authentication failed.", Toast.LENGTH_SHORT)
                           .show();
                     // TODO handle failure
                  }
                  // TODO handle success
               }
            });
   }
}
