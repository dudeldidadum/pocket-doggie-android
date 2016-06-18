package com.n3rditorium.pocketdoggie.buisness;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public interface BuisnessObject<Item> {

   interface OnDataCallback<Item> {

      void noData(Throwable throwable);

      void onItem(Item item);

      void onList(List<Item> items);
   }

   void delete(Item item);

   DatabaseReference getMainNode(String... params);

   void loadAll(OnDataCallback<Item> callback, String... params);

   void registerChildEventListener(final ChildEventListener listener, final String... params);

   void save(Item item, String... params);

   void unregisterChildEventListener(final ChildEventListener listener, final String... params);

   void update(Item entry);
}
