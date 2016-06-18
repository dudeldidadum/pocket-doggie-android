package com.n3rditorium.pocketdoggie.injection;

import com.n3rditorium.pocketdoggie.buisness.DeedBO;
import com.n3rditorium.pocketdoggie.buisness.DogBO;

import dagger.Module;
import dagger.Provides;

@Module
public class BusinessModule {

   @Provides
   DeedBO provideDeedBo() {
      return new DeedBO();
   }

   @Provides
   DogBO provideDogBO() {
      return new DogBO();
   }
}
