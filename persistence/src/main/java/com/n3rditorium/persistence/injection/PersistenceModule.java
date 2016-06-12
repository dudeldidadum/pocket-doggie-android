package com.n3rditorium.persistence.injection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.n3rditorium.persistence.databse.DatabaseHelper;
import com.n3rditorium.pocketdoggie.persistence.DaoMaster;
import com.n3rditorium.pocketdoggie.persistence.DaoSession;
import com.n3rditorium.pocketdoggie.persistence.DeedDBDao;
import com.n3rditorium.pocketdoggie.persistence.DogDBDao;
import com.n3rditorium.pocketdoggie.persistence.MetricDBDao;

import dagger.Module;
import dagger.Provides;

@Module
public class PersistenceModule {

   private final Context context;

   public PersistenceModule(Context context) {
      this.context = context;
   }

   @Provides
   DaoSession provideDaoSession() {
      final DaoMaster.OpenHelper helper = new DatabaseHelper(context, null);
      final SQLiteDatabase db = helper.getWritableDatabase();
      final DaoMaster daoMaster = new DaoMaster(db);
      return daoMaster.newSession();
   }

   @Provides
   DeedDBDao provideDeedDBDao(final DaoSession session) {
      return session.getDeedDBDao();
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
