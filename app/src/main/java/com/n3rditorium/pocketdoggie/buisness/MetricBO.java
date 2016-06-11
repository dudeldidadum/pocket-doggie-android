package com.n3rditorium.pocketdoggie.buisness;

import com.n3rditorium.pocketdoggie.injection.Injector;
import com.n3rditorium.pocketdoggie.models.Metric;
import com.n3rditorium.pocketdoggie.persistence.MetricDB;
import com.n3rditorium.pocketdoggie.persistence.MetricDBDao;

import java.util.List;

import javax.inject.Inject;

public class MetricBO implements BuisnessObject<Metric, MetricDB> {

   @Inject
   MetricDBDao metricDBDao;

   public MetricBO() {
      Injector.getAppComponent()
            .inject(this);
   }

   @Override
   public MetricDB buildForDatabase(Metric metric) {
      MetricDB dbEntry = new MetricDB();
      if (metric.getId() != null) {
         dbEntry.setId(metric.getId());
      }
      if (metric.getDogId() != null) {
         dbEntry.setDogId(metric.getDogId());
      }
      if (metric.getTimestamp() != null) {
         dbEntry.setTimestamp(metric.getTimestamp());
      }
      if (metric.getValue() != null) {
         dbEntry.setValue(metric.getValue());
      }
      if (metric.getUnit() != null) {
         dbEntry.setUnitId(metric.getUnit());
      }
      return dbEntry;
   }

   @Override
   public Metric buildFromDatabase(MetricDB metricDB) {
      return new Metric().setId(metricDB.getId())
            .setDogId(metricDB.getDogId())
            .setValue(metricDB.getValue())
            .setUnit(metricDB.getUnitId())
            .setTimestamp(metricDB.getTimestamp());
   }

   @Override
   public void delete(Metric metric) {

   }

   public Metric getLatestHeight(Long dogId) {
      // TODO load latest height using a custom query
      return null;
   }

   public Metric getLatestWeight(Long dogId) {
      // TODO load latest weight using a custom query
      return null;
   }

   @Override
   public List<Metric> loadAllFromDatabase() {
      return null;
   }

   @Override
   public void persistEntry(MetricDB metric) {
      metricDBDao.insert(metric);
   }

   @Override
   public void persistItem(Metric metric) {

   }

   @Override
   public void updateEntry(MetricDB metric) {
      metricDBDao.update(metric);
   }
}
