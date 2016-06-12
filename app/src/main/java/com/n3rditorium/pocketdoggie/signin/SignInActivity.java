package com.n3rditorium.pocketdoggie.signin;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.n3rditorium.pocketdoggie.R;
import com.n3rditorium.pocketdoggie.injection.Injector;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class SignInActivity extends AppCompatActivity {

   private static final int RC_SIGN_IN = 904;

   @Inject
   FirebaseController firebaseController;
   @Inject
   GoogleApiClient googleApiClient;
   @BindView (R.id.login_form)
   View loginFormView;
   @BindView (R.id.email)
   AutoCompleteTextView mEmailView;
   @BindView (R.id.password)
   EditText mPasswordView;
   @BindView (R.id.login_progress)
   View progressView;
   private View focusView;

   @Override
   public void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);

      // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
      if (requestCode == RC_SIGN_IN) {
         GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
         if (result.isSuccess()) {
            // Google Sign In was successful, authenticate with Firebase
            GoogleSignInAccount account = result.getSignInAccount();
            firebaseController.signInWithGoogle(account);
         } else {
            showProgress(false);
         }
      }
   }

   @OnClick (R.id.btn_email_register)
   public void onRegisterClick() {
      mEmailView.setError(null);
      mPasswordView.setError(null);
      // Store values at the time of the login attempt.
      String email = mEmailView.getText()
            .toString();
      String password = mPasswordView.getText()
            .toString();

      boolean cancel = validateInput(email, password);
      if (cancel) {
         // There was an error; don't attempt login and focus the first
         // form field with an error.
         focusView.requestFocus();
      } else {
         // Show a progress spinner, and kick off a background task to
         // perform the user login attempt.
         showProgress(true);
         firebaseController.registerWithEmail(email, password);
      }
   }

   @SuppressWarnings ("unused")
   @OnClick ( { R.id.btn_email_register, R.id.btn_email_sign_in })
   public void onSignOrRegisterClick(View clickedView) {
      mEmailView.setError(null);
      mPasswordView.setError(null);
      // Store values at the time of the login attempt.
      String email = mEmailView.getText()
            .toString();
      String password = mPasswordView.getText()
            .toString();

      boolean cancel = validateInput(email, password);
      if (cancel) {
         // There was an error; don't attempt login and focus the first
         // form field with an error.
         focusView.requestFocus();
      } else {
         // Show a progress spinner, and kick off a background task to
         // perform the user login attempt.
         showProgress(true);
         if (clickedView.getId() == R.id.btn_email_register) {
            firebaseController.registerWithEmail(email, password);
         } else {
            firebaseController.signInWithEmail(email, password);
         }
      }
   }

   @SuppressWarnings ("unused")
   @OnClick (R.id.btn_google_signin)
   public void startGoogleSignIn() {
      Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
      startActivityForResult(signInIntent, RC_SIGN_IN);
   }

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      Injector.getAppComponent()
            .inject(this);
      setContentView(R.layout.sign_in_activity);
      ButterKnife.bind(this);
      firebaseController.init(this);
      firebaseController.onCreate(savedInstanceState);
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

   private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
      //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
      ArrayAdapter<String> adapter =
            new ArrayAdapter<>(SignInActivity.this, android.R.layout.simple_dropdown_item_1line,
                  emailAddressCollection);

      mEmailView.setAdapter(adapter);
   }

   private int getVisibility(boolean show) {
      int visibility;
      if (show) {
         visibility = View.GONE;
      } else {
         visibility = View.VISIBLE;
      }
      return visibility;
   }

   private boolean isEmailValid(String email) {
      //TODO: Replace this with your own logic
      return email.contains("@");
   }

   private boolean isPasswordValid(String password) {
      //TODO: Replace this with your own logic
      return password.length() > 4;
   }

   /**
    * Shows the progress UI and hides the login form.
    */
   private void showProgress(final boolean show) {
      int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

      final int visibility = getVisibility(show);
      //noinspection WrongConstant
      loginFormView.setVisibility(visibility);
      loginFormView.animate()
            .setDuration(shortAnimTime)
            .alpha(show ? 0 : 1)
            .setListener(new AnimatorListenerAdapter() {
               @Override
               public void onAnimationEnd(Animator animation) {
                  //noinspection WrongConstant
                  loginFormView.setVisibility(visibility);
               }
            });

      progressView.setVisibility(show ? View.VISIBLE : View.GONE);
      progressView.animate()
            .setDuration(shortAnimTime)
            .alpha(show ? 1 : 0)
            .setListener(new AnimatorListenerAdapter() {
               @Override
               public void onAnimationEnd(Animator animation) {
                  progressView.setVisibility(show ? View.VISIBLE : View.GONE);
               }
            });
   }

   private boolean validateInput(String email, String password) {
      boolean cancel = false;
      focusView = null;

      // Check for a valid password, if the user entered one.
      if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
         mPasswordView.setError(getString(R.string.error_invalid_password));
         focusView = mPasswordView;
         cancel = true;
      }

      // Check for a valid email address.
      if (TextUtils.isEmpty(email)) {
         mEmailView.setError(getString(R.string.error_field_required));
         focusView = mEmailView;
         cancel = true;
      } else if (!isEmailValid(email)) {
         mEmailView.setError(getString(R.string.error_invalid_email));
         focusView = mEmailView;
         cancel = true;
      }
      return cancel;
   }
}

