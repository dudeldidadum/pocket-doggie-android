package com.n3rditorium.persistence.databse;

import android.database.sqlite.SQLiteDatabase;

public abstract class AbstractMigrationHelper {

   protected static final String ALTER_TABLE = "ALTER TABLE ";
   protected static final String CREATE_TABLE = "CREATE TABLE ";

   public abstract void onUpgrade(SQLiteDatabase db);
}
