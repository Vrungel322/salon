package com.apps.twelve.floor.salon.data.local;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import java.util.List;
import rx.Observable;
import timber.log.Timber;

/**
 * Created by Vrungel on 27.07.2017.
 */

public class DbHelper {

  private Realm mRealm;

  public DbHelper(Realm realm) {
    this.mRealm = realm;
  }

  public <T extends RealmObject> Observable<T> save(T object) {
    Realm realm = mRealm;

    return Observable.just(object)
        .flatMap(t -> Observable.just(t)
            .doOnSubscribe(realm::beginTransaction)
            .doOnUnsubscribe(realm::commitTransaction)
            .doOnNext(realm::copyToRealmOrUpdate));
  }

  public <T extends RealmObject> Observable<List<T>> getAll(Class<T> clazz) {
    Realm realm = mRealm;

    return Observable.just(clazz)
        .flatMap(t -> Observable.just(t)
            .doOnSubscribe(realm::beginTransaction)
            .doOnUnsubscribe(realm::commitTransaction)
            .map(type -> realm.where(type).findAll()));
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
