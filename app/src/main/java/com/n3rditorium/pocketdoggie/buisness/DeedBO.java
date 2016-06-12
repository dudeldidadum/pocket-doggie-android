package com.n3rditorium.pocketdoggie.buisness;

import com.google.firebase.database.DatabaseReference;
import com.n3rditorium.pocketdoggie.injection.Injector;
import com.n3rditorium.pocketdoggie.models.Deed;

import java.util.List;

import javax.inject.Inject;

public class DeedBO implements BuisnessObject<Deed> {

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
   public List<Deed> loadAll() {
      return null;
   }

   @Override
   public void save(Deed deed) {

   }

   @Override
   public void update(Deed deed) {

   }
}
