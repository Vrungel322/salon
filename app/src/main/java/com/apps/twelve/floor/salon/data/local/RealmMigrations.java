package com.apps.twelve.floor.salon.data.local;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

/**
 * Created by Vrungel on 03.08.2017.
 */

public class RealmMigrations implements RealmMigration {

  @Override public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
    final RealmSchema schema = realm.getSchema();

    //migration mechanism
    //LastBookingEntity extends RealmObject
    //oldVersion -> look in DbHelper constructor and newVersion-1
    //if (oldVersion == 1) {
    //  final RealmObjectSchema userSchema = schema.get("LastBookingEntity");
    //  userSchema.addField("age", int.class);
    //}
  }
}