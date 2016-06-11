package com.n3rditorium.pocketdoggie.buisness;

import com.n3rditorium.pocketdoggie.injection.Injector;
import com.n3rditorium.pocketdoggie.models.Dog;
import com.n3rditorium.pocketdoggie.persistence.DogDB;
import com.n3rditorium.pocketdoggie.persistence.DogDBDao;
import com.n3rditorium.pocketdoggie.persistence.MetricDB;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class DogBO implements BuisnessObject<Dog, DogDB> {

   @Inject
   DogDBDao dogDBDao;
   @Inject
   MetricBO metricBO;

   public DogBO() {
      Injector.getAppComponent()
            .inject(this);
   }

   @Override
   public DogDB buildForDatabase(Dog dog) {
      DogDB dbEntry = new DogDB();
      dbEntry.setName(dog.getName());
      dbEntry.setDescription(dog.getDescription());
      dbEntry.setIdentifier(dog.getIdentifier());
      dbEntry.setBirthday(dog.getBirthday());
      return dbEntry;
   }

   @Override
   public Dog buildFromDatabase(DogDB dogDB) {
      return new Dog().setBirthday(dogDB.getBirthday())
            .setDescription(dogDB.getDescription())
            .setHeight(metricBO.getLatestHeight(dogDB.getId()))
            .setWeight(metricBO.getLatestWeight(dogDB.getId()))
            .setId(dogDB.getId())
            .setIdentifier(dogDB.getIdentifier())
            .setName(dogDB.getName());
   }

   @Override
   public void delete(Dog dog) {
      dogDBDao.delete(buildForDatabase(dog));
   }

   @Override
   public List<Dog> loadAllFromDatabase() {
      List<DogDB> entries = dogDBDao.loadAll();
      List<Dog> items = new ArrayList<>();
      for (DogDB entry : entries) {
         items.add(buildFromDatabase(entry));
      }
      return items;
   }

   @Override
   public void persistEntry(DogDB entry) {
      dogDBDao.insert(entry);
   }

   @Override
   public void persistItem(Dog dog) {
      MetricDB dbHeight = metricBO.buildForDatabase(dog.getHeight());
      if (dbHeight.getId() == null) {
         metricBO.persistEntry(dbHeight);
      } else {
         metricBO.updateEntry(dbHeight);
      }
      MetricDB dbWeight = metricBO.buildForDatabase(dog.getWeight());
      if (dbWeight.getId() == null) {
         metricBO.persistEntry(dbWeight);
      } else {
         metricBO.updateEntry(dbWeight);
      }
      DogDB dbDog = buildForDatabase(dog);
      if (dbDog.getId() == null) {
         persistEntry(dbDog);
      } else {
         updateEntry(dbDog);
      }
   }

   @Override
   public void updateEntry(DogDB dog) {
      dogDBDao.update(dog);
   }
}
