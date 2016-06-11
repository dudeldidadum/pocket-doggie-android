package com.n3rditorium.pocketdoggie.injection;

import com.n3rditorium.pocketdoggie.buisness.DogBO;
import com.n3rditorium.pocketdoggie.buisness.MetricBO;

import dagger.Module;
import dagger.Provides;

@Module
public class BusinessModule {

   @Provides
   DogBO provideDogBO() {
      return new DogBO();
   }

   @Provides
   MetricBO provideMetricBo() {
      return new MetricBO();
   }
}
