package com.n3rditorium.pocketdoggie.buisness;

import java.util.List;

public interface BuisnessObject<Item, DatabaseEntry> {

   DatabaseEntry buildForDatabase(Item item);

   Item buildFromDatabase(DatabaseEntry entry);

   void delete(Item item);

   List<Item> loadAllFromDatabase();

   void persistEntry(DatabaseEntry entry);

   void persistItem(Item item);

   void updateEntry(DatabaseEntry entry);
}
