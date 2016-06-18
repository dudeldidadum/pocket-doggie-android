package com.n3rditorium.pocketdoggie.buisness;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.n3rditorium.pocketdoggie.injection.Injector;
import com.n3rditorium.pocketdoggie.models.Dog;

import java.util.HashMap;
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
      // TODO implement
   }

   @Override
   public DatabaseReference getMainNode(final String... params) {
      if (params == null || params.length != 1) {
         throw new IllegalArgumentException("passed params are not valid");
      }
      String dogId = params[0];

      return databaseReference.child("dogs")
            .child(dogId);
   }

   @Override
   public void loadAll(final OnDataCallback<Dog> callback, final String... params) {
      if (params == null || params.length != 1) {
         throw new IllegalArgumentException("passed params are not valid");
      }
      // load all dogs for a specific user
      String userToken = params[0];
      DatabaseReference dogsForUsers = databaseReference.child("user-dogs")
            .child(userToken);
      dogsForUsers.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onCancelled(DatabaseError databaseError) {
            callback.noData(databaseError.toException());
         }

         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
            Dog result;
            try {
               result = (Dog) dataSnapshot.getValue();
            } catch (ClassCastException e) {
               callback.noData(e);
               return;
            }
            callback.onItem(result);
         }
      });
   }

   @Override
   public void registerChildEventListener(final ChildEventListener listener,
         final String... params) {
      // nothing to do here => events for Dog.class not necessary at the moment
   }

   @Override
   public void save(final Dog dog, final String... params) {
      String key = databaseReference.child("dogs")
            .push()
            .getKey();

      dog.setKey(key);
      Map<String, Object> dogValues = dog.toMap();

      Map<String, Object> childUpdates = new HashMap<>();
      childUpdates.put("/dogs/" + key, dogValues);
      childUpdates.put("/user-dogs/" + params[0] + "/" + key, dogValues);

      databaseReference.updateChildren(childUpdates);
   }

   @Override
   public void unregisterChildEventListener(ChildEventListener listener, String... params) {
      // nothing to do here => events for Dog.class not necessary at the moment
   }

   @Override
   public void update(Dog dog) {
      // TODO implement
   }
}
