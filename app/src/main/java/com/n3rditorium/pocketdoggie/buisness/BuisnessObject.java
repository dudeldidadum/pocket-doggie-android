package com.n3rditorium.pocketdoggie.buisness;

import java.util.List;

public interface BuisnessObject<Item> {

   void delete(Item item);

   List<Item> loadAll();

   void save(Item item);

   void update(Item entry);
}
