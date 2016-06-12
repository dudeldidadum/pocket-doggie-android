package com.n3rditorium.pocketdoggie.buisness;

import com.google.firebase.database.DatabaseReference;
import com.n3rditorium.pocketdoggie.injection.Injector;
import com.n3rditorium.pocketdoggie.models.Dog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
   public List<Dog> loadAll(String userToken) {
      return null;
   }

   @Override
   public void save(String userToken, Dog dog) {
      String key = databaseReference.child("dogs")
            .push()
            .getKey();

      dog.setUid(key);
      Map<String, Object> dogValues = dog.toMap();

      Map<String, Object> childUpdates = new HashMap<>();
      childUpdates.put("/dogs/" + key, dogValues);
      childUpdates.put("/user-dogs/" + userToken + "/" + key, dogValues);

      databaseReference.updateChildren(childUpdates);
   }

   @Override
   public void update(Dog dog) {

   }
}
