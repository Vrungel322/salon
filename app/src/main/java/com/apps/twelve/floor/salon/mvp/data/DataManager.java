package com.apps.twelve.floor.salon.mvp.data;

import android.net.Uri;
import com.apps.twelve.floor.salon.mvp.data.model.OurWorkEntity;
import com.apps.twelve.floor.salon.mvp.data.model.PreviewNewsEntity;
import com.apps.twelve.floor.salon.mvp.data.remote.RestApi;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;

/**
 * Created by Vrungel on 26.01.2017.
 */

public class DataManager {

  private RestApi mRestApi;

  public DataManager(RestApi restApi) {
    this.mRestApi = restApi;
  }

  public Observable<List<OurWorkEntity>> fetchListOfWorks() {
    return Observable.create(subscriber -> {
      ArrayList<OurWorkEntity> owe = new ArrayList<>();
      for (int i = 0; i < 10; i++) {
        owe.add(new OurWorkEntity(Uri.parse("http://www.drodd.com/images15/hair16.jpg"),
            "http://www.drodd.com/images15/hair16.jpg " + i, i));
      }
      subscriber.onNext(owe);
      subscriber.onCompleted();
    });
  }

  public Observable<PreviewNewsEntity> fetchNewsPreview() {
    return Observable.just(new PreviewNewsEntity(Uri.parse("http://mac.h-cdn.co/assets/16/16/640x320/landscape-1461093915-ciara-zoom.jpg"),
        "Short description Short description Short description Short description", "23.02.2017"));
  }
}
