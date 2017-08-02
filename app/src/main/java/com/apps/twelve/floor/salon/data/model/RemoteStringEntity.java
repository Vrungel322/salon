package com.apps.twelve.floor.salon.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Vrungel on 02.08.2017.
 */

public class RemoteStringEntity extends RealmObject {
  @PrimaryKey @SerializedName("id") @Expose private String id;
  @SerializedName("text") @Expose private String text;

  public RemoteStringEntity() {
  }

  public RemoteStringEntity(String id, String text) {
    this.id = id;
    this.text = text;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
