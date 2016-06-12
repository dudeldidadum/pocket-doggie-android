package com.n3rditorium.pocketdoggie.injection;

import com.n3rditorium.persistence.injection.PersistenceModule;
import com.n3rditorium.pocketdoggie.buisness.DeedBO;
import com.n3rditorium.pocketdoggie.buisness.DogBO;
import com.n3rditorium.pocketdoggie.buisness.MetricBO;
import com.n3rditorium.pocketdoggie.signin.FirebaseController;
import com.n3rditorium.pocketdoggie.signin.SignInActivity;
import com.n3rditorium.pocketdoggie.signin.SignInModule;

import dagger.Component;


@Component (modules = { ApplicationModule.class, BusinessModule.class, PersistenceModule.class,
      SignInModule.class })
public interface AppComponent {

   void inject(MetricBO metricBO);

   void inject(DogBO dogBO);

   void inject(SignInActivity signInActivity);

   void inject(FirebaseController firebaseController);

   void inject(DeedBO deedBO);
}
