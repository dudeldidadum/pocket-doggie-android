package com.n3rditorium.pocketdoggie.persistence;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.n3rditorium.pocketdoggie.persistence.DogDB;
import com.n3rditorium.pocketdoggie.persistence.MetricDB;
import com.n3rditorium.pocketdoggie.persistence.DeedDB;

import com.n3rditorium.pocketdoggie.persistence.DogDBDao;
import com.n3rditorium.pocketdoggie.persistence.MetricDBDao;
import com.n3rditorium.pocketdoggie.persistence.DeedDBDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig dogDBDaoConfig;
    private final DaoConfig metricDBDaoConfig;
    private final DaoConfig deedDBDaoConfig;

    private final DogDBDao dogDBDao;
    private final MetricDBDao metricDBDao;
    private final DeedDBDao deedDBDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        dogDBDaoConfig = daoConfigMap.get(DogDBDao.class).clone();
        dogDBDaoConfig.initIdentityScope(type);

        metricDBDaoConfig = daoConfigMap.get(MetricDBDao.class).clone();
        metricDBDaoConfig.initIdentityScope(type);

        deedDBDaoConfig = daoConfigMap.get(DeedDBDao.class).clone();
        deedDBDaoConfig.initIdentityScope(type);

        dogDBDao = new DogDBDao(dogDBDaoConfig, this);
        metricDBDao = new MetricDBDao(metricDBDaoConfig, this);
        deedDBDao = new DeedDBDao(deedDBDaoConfig, this);

        registerDao(DogDB.class, dogDBDao);
        registerDao(MetricDB.class, metricDBDao);
        registerDao(DeedDB.class, deedDBDao);
    }
    
    public void clear() {
        dogDBDaoConfig.getIdentityScope().clear();
        metricDBDaoConfig.getIdentityScope().clear();
        deedDBDaoConfig.getIdentityScope().clear();
    }

    public DogDBDao getDogDBDao() {
        return dogDBDao;
    }

    public MetricDBDao getMetricDBDao() {
        return metricDBDao;
    }

    public DeedDBDao getDeedDBDao() {
        return deedDBDao;
    }

}
