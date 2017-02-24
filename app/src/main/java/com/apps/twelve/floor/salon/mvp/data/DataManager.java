package com.apps.twelve.floor.salon.mvp.data;

import android.net.Uri;
import com.apps.twelve.floor.salon.mvp.data.model.NewsEntity;
import com.apps.twelve.floor.salon.mvp.data.model.OurWorkEntity;
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
    ArrayList<OurWorkEntity> owe = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      owe.add(new OurWorkEntity(Uri.parse(
          "http://beauty-proceduri.ru/assets/images/gallery/srednie-svadebnie-pricheski/svadebnaya-na-srednie-volosi-53.jpg"),
          "http://www.drodd.com/images15/hair16.jpg " + i, i));
    }
    return Observable.just(owe);
  }

  public Observable<NewsEntity> fetchNewsPreview() {
    return Observable.just(new NewsEntity(Uri.parse("http://mac.h-cdn.co/assets/16/16/640x320/landscape-1461093915-ciara-zoom.jpg"),
        "Short description Short description Short description Short description", "23.02.2017", NewsEntity.LAST_NEWS));
  }

  public Observable<List<NewsEntity>> fetchAllNews() {
    ArrayList<NewsEntity> ne = new ArrayList<>();
    for (int i = 0; i < 9; i++) {
      ne.add(new NewsEntity(Uri.parse("http://mac.h-cdn.co/assets/16/16/640x320/landscape-1461093915-ciara-zoom.jpg"),
          "Short description Short description Short description Short description", "23.02.2017", NewsEntity.DEFAULT_NEWS));
    }
    ne.get(0).setIsLastNews(NewsEntity.LAST_NEWS);
    return Observable.just(ne);
  }
}
