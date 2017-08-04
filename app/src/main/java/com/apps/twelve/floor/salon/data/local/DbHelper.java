package com.apps.twelve.floor.salon.data.local;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import java.util.ArrayList;
import java.util.List;
import timber.log.Timber;

/**
 * Created by Vrungel on 27.07.2017.
 */

public class DbHelper {

  private final RealmConfiguration mConfiguration;

  public DbHelper() {
    mConfiguration = new RealmConfiguration.Builder().name("sample.realm")
        //new version
        .schemaVersion(2)
        .migration(new RealmMigrations())
        .build();
    Realm.setDefaultConfiguration(mConfiguration);
  }

  public <T extends RealmObject> void save(T object) {

    Realm realm = Realm.getInstance(mConfiguration);
    realm.beginTransaction();
    realm.copyToRealmOrUpdate(object);
    realm.commitTransaction();
  }

  public <T extends RealmObject> List<T> getAll(Class<T> clazz) {
    List<T> list = new ArrayList<T>();
    Realm realm = Realm.getInstance(mConfiguration);
    realm.beginTransaction();
    list = realm.where(clazz).findAllAsync();
    realm.commitTransaction();
    return list;
  }

  public <T extends RealmObject> T getElementById(Class<T> clazz, int id) {

    Realm realm = Realm.getInstance(mConfiguration);
    RealmQuery<T> query = realm.where(clazz).equalTo("id", id);

    T t;
    try {
      t = clazz.newInstance();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }

    realm.beginTransaction();
    t = query.findFirst();
    realm.commitTransaction();
    return t;
  }

  public <T extends RealmObject> List<T> getElementsFromDBByQuery(Class<T> clazz, String field,
      String value) {

    Realm realm = Realm.getInstance(mConfiguration);
    RealmQuery<T> query = realm.where(clazz).equalTo(field, value);

    List<T> list = new ArrayList<T>();
    realm.beginTransaction();
    list = query.findAllAsync();
    realm.commitTransaction();
    return list;
  }

  public <T extends RealmObject> void clearRealmTable(Class<T> clazz) {
    RealmResults<T> results = Realm.getInstance(mConfiguration).where(clazz).findAllAsync();

    // All changes to data must happen in a transaction
    Realm.getInstance(mConfiguration).beginTransaction();

    // Delete all matches
    results.deleteAllFromRealm();

    Realm.getInstance(mConfiguration).commitTransaction();
    Realm.getInstance(mConfiguration).close();
    Timber.e("clearRealm " + clazz.toString());
  }
}
