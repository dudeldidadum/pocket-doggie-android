package com.n3rditorium.persistence.generator;

import java.io.IOException;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class Generator {

   // TODO update the version after changes!
   private static final int VERSION = 1;

   public static void main(String[] args) {
      createEntities();
      System.exit(0);
   }

   private static void createDeedEntitiy(Schema schema) {
      final Entity deed = schema.addEntity("DeedDB");

      deed.addIdProperty()
            .autoincrement();
      deed.addLongProperty("dogId");
      deed.addIntProperty("type");
      deed.addStringProperty("description");
      deed.addBooleanProperty("inside");
      deed.addLongProperty("timestamp");
      deed.addLongProperty("latitude");
      deed.addLongProperty("longitude");
   }

   private static void createDogEntity(Schema schema) {
      final Entity dog = schema.addEntity("DogDB");

      dog.addIdProperty()
            .autoincrement();
      dog.addStringProperty("identifier");
      dog.addStringProperty("name");
      dog.addStringProperty("description");
      dog.addLongProperty("birthday");
      dog.addIntProperty("currentHeightId");
      dog.addIntProperty("weight");
   }

   private static void createEntities() {
      Schema schema = new Schema(VERSION, "com.n3rditorium.pocketdoggie.persistence");
      schema.enableKeepSectionsByDefault();

      // create entities:
      createDogEntity(schema);
      createMetricEntity(schema);
      createDeedEntitiy(schema);

      DaoGenerator daoGenerator;
      try {
         daoGenerator = new DaoGenerator();
         daoGenerator.generateAll(schema, "persistence/src/main/src-gen");
      } catch (IOException e) {
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   private static void createMetricEntity(Schema schema) {
      final Entity metric = schema.addEntity("MetricDB");

      metric.addIdProperty()
            .autoincrement();
      metric.addLongProperty("dogId");
      metric.addIntProperty("value");
      metric.addIntProperty("unitId");
      metric.addLongProperty("timestamp");
   }
}


