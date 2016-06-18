package com.n3rditorium.pocketdoggie.buisness;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.n3rditorium.pocketdoggie.injection.Injector;
import com.n3rditorium.pocketdoggie.models.Deed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class DeedBO implements BuisnessObject<Deed> {

   private static final int INDEX_DOG_ID = 0;

   @Inject
   DatabaseReference database;

   public DeedBO() {
      Injector.getAppComponent()
            .inject(this);
   }

   @Override
   public void delete(Deed deed) {

   }

   @Override
   public DatabaseReference getMainNode(final String... params) {
      if (params == null || params.length != 1) {
         throw new IllegalArgumentException("passed params are not valid");
      }
      final String parentId = params[0];
      return database.child("dogs")
            .child(parentId)
            .child("deeds");
   }

   @Override
   public void loadAll(final OnDataCallback<Deed> callback, String... params) {
      try {
         validateParams(params);
      } catch (IllegalArgumentException e) {
         Log.e(getClass().getSimpleName(), e.getMessage(), e);
         return;
      }
      Query query = getMainNode(params).orderByChild(Deed.Fields.TIMESTAMP);
      query.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onCancelled(DatabaseError databaseError) {
            callback.noData(databaseError.toException());
         }

         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
            List<Deed> items = new ArrayList<>();
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
               items.add(snapshot.getValue(Deed.class));
            }
            callback.onList(items);
         }
      });
   }

   public void registerChildEventListener(final ChildEventListener listener,
         final String... params) {
      getMainNode(params).addChildEventListener(listener);
   }

   @Override
   public void save(final Deed deed, final String... params) {
      try {
         validateParams(params);
      } catch (IllegalArgumentException e) {
         Log.e(getClass().getSimpleName(), e.getMessage(), e);
         return;
      }
      String key = database.child("deeds")
            .push()
            .getKey();

      String dogId = params[INDEX_DOG_ID];
      deed.setKey(key);
      Map<String, Object> deedMap = deed.toMap();
      Map<String, Object> childUpdates = new HashMap<>();
      childUpdates.put("/dogs/" + dogId + "/deeds/" + key, deedMap);

      database.updateChildren(childUpdates);
      Log.i(getClass().getSimpleName(), "save()\n" + deed);
   }

   public void unregisterChildEventListener(final ChildEventListener listener,
         final String... params) {
      getMainNode(params).removeEventListener(listener);
   }

   @Override
   public void update(Deed deed) {

   }

   private void validateParams(final String... params) throws IllegalArgumentException {
      if (params == null) {
         throw new IllegalArgumentException("params must not be null");
      }
   }
}
