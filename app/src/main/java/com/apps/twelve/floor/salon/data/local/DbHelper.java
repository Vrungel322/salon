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
    Realm realm = mRealm;
    realm.beginTransaction();
    realm.copyToRealmOrUpdate(object);
    realm.commitTransaction();
  }

  public <T extends RealmObject> List<T> getAll(Class<T> clazz) {
    List<T> list = new ArrayList<T>();
    Realm realm = mRealm;
    realm.beginTransaction();
    list = realm.where(clazz).findAllAsync();
    realm.commitTransaction();
    return list;
  }

  public <T extends RealmObject> List<T> getElementsFromDBByQuery(Class<T> clazz, String field,
      String value) {

    Realm realm = mRealm;
    RealmQuery<T> query = realm.where(clazz).equalTo(field, value);

    List<T> list = new ArrayList<T>();
    realm.beginTransaction();
    list = query.findAllAsync();
    realm.commitTransaction();
    return list;
  }

  public <T extends RealmObject> void clearRealmTable(Class<T> clazz) {
    RealmResults<T> results = mRealm.where(clazz).findAll();

    // All changes to data must happen in a transaction
    mRealm.beginTransaction();

    // Delete all matches
    results.deleteAllFromRealm();

    mRealm.commitTransaction();
    mRealm.close();
    Timber.e("clearRealm " + clazz.toString());
  }
}
