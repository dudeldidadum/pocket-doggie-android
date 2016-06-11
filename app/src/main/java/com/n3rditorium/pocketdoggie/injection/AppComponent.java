package com.n3rditorium.pocketdoggie.injection;

import com.n3rditorium.persistence.injection.PersistenceModule;
import com.n3rditorium.pocketdoggie.buisness.DogBO;
import com.n3rditorium.pocketdoggie.buisness.MetricBO;

import dagger.Component;

@Component (modules = { ApplicationModule.class, BusinessModule.class, PersistenceModule.class })
public interface AppComponent {

   void inject(MetricBO metricBO);

   void inject(DogBO dogBO);
}
