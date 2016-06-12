package com.n3rditorium.pocketdoggie.buisness;

import com.google.firebase.database.DatabaseReference;
import com.n3rditorium.pocketdoggie.injection.Injector;
import com.n3rditorium.pocketdoggie.models.Dog;

import java.util.List;

import javax.inject.Inject;

public class DogBO implements BuisnessObject<Dog> {

   @Inject
   DatabaseReference databaseReference;

   public DogBO() {
      Injector.getAppComponent()
            .inject(this);
   }

   @Override
   public void delete(Dog dog) {

   }

   @Override
   public List<Dog> loadAll() {
      return null;
   }

   @Override
   public void save(Dog dog) {
      databaseReference.child("dogs")
            .setValue(dog);
   }

   @Override
   public void update(Dog dog) {

   }
}
