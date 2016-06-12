package com.n3rditorium.pocketdoggie.buisness;

import com.n3rditorium.pocketdoggie.injection.Injector;
import com.n3rditorium.pocketdoggie.models.Metric;

import java.util.List;

public class MetricBO implements BuisnessObject<Metric> {

   public MetricBO() {
      Injector.getAppComponent()
            .inject(this);
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
   public List<Metric> loadAll() {
      return null;
   }

   @Override
   public void save(Metric metric) {

   }

   @Override
   public void update(Metric metric) {

   }
}
