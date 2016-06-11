package com.n3rditorium.persistence.injection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.n3rditorium.persistence.databse.DatabaseHelper;
import com.n3rditorium.pocketdoggie.persistence.DaoMaster;
import com.n3rditorium.pocketdoggie.persistence.DaoSession;
import com.n3rditorium.pocketdoggie.persistence.DogDBDao;
import com.n3rditorium.pocketdoggie.persistence.MetricDBDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PersistenceModule {

   @Provides
   @Singleton
   DaoSession provideDaoSession(final Context context) {
      final DaoMaster.OpenHelper helper = new DatabaseHelper(context, null);
      final SQLiteDatabase db = helper.getWritableDatabase();
      final DaoMaster daoMaster = new DaoMaster(db);
      return daoMaster.newSession();
   }

   @Provides
   DogDBDao provideDogDBDao(final DaoSession session) {
      return session.getDogDBDao();
   }

   @Provides
   MetricDBDao provideMetricDBDao(final DaoSession session) {
      return session.getMetricDBDao();
   }
}
