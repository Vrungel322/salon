package com.apps.twelve.floor.salon.data.local;

import io.realm.Realm;
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

  private Realm mRealm;

  public DbHelper(Realm realm) {
    this.mRealm = realm;
  }

  public <T extends RealmObject> void save(T object) {

    Realm realm = Realm.getDefaultInstance();
    realm.beginTransaction();
    realm.copyToRealmOrUpdate(object);
    realm.commitTransaction();
  }

  public <T extends RealmObject> List<T> getAll(Class<T> clazz) {
    List<T> list = new ArrayList<T>();
    Realm realm = Realm.getDefaultInstance();
    realm.beginTransaction();
    list = realm.where(clazz).findAll();
    realm.commitTransaction();
    return list;
  }

  public <T extends RealmObject> T getElementById(Class<T> clazz, int id) {

    Realm realm = mRealm;
    RealmQuery<T> query = realm.where(clazz).equalTo("id", id);

    T t = null;
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

    Realm realm = Realm.getDefaultInstance();
    RealmQuery<T> query = realm.where(clazz).equalTo(field, value);

    List<T> list = new ArrayList<T>();
    realm.beginTransaction();
    list = query.findAll();
    realm.commitTransaction();
    return list;
  }

  public <T extends RealmObject> void clearRealmTable(Class<T> clazz) {
    RealmResults<T> results = Realm.getDefaultInstance().where(clazz).findAll();

    // All changes to data must happen in a transaction
    mRealm.beginTransaction();

    // Delete all matches
    results.deleteAllFromRealm();

    mRealm.commitTransaction();
    mRealm.close();
    Timber.e("clearRealm " + clazz.toString());
  }
}
