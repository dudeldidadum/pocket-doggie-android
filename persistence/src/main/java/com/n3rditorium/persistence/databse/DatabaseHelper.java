package com.n3rditorium.persistence.databse;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.n3rditorium.pocketdoggie.persistence.DaoMaster;

import java.util.HashMap;
import java.util.Map;

public class DatabaseHelper extends DaoMaster.OpenHelper {

   private static final String DATABASE_NAME = "com.aldi.app.persistence";
   private final Map<Integer, Class<? extends AbstractMigrationHelper>> availableMigrations;

   public DatabaseHelper(Context context, SQLiteDatabase.CursorFactory factory) {
      super(context, DATABASE_NAME, factory);
      availableMigrations = new HashMap<>();
      // add here potential migrations
      // availableMigrations.put(SCHEMA_V8, DBMigrationUpgradeToSchemeV2.class);
   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      executeUpgrade(db, oldVersion, newVersion);
   }

   private void executeUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      for (int i = oldVersion + 1; i <= newVersion; i++) {
         final Class<? extends AbstractMigrationHelper> migrationClass = getClassName(i);
         if (migrationClass == null) {
            continue;
         }
         try {
            final AbstractMigrationHelper migrationHelper = migrationClass.newInstance();
            if (migrationHelper != null) {
               migrationHelper.onUpgrade(db);
            }
         } catch (ClassCastException | IllegalAccessException |
               InstantiationException e) {
         }
      }
   }

   private Class<? extends AbstractMigrationHelper> getClassName(int i) {
      return availableMigrations.get(i);
   }
}
