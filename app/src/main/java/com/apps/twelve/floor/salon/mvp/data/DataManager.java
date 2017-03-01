package com.apps.twelve.floor.salon.mvp.data;

import android.net.Uri;
import com.apps.twelve.floor.salon.mvp.data.model.LastBookingEntity;
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
    for (int i = 0; i < 5; i++) {
      owe.add(new OurWorkEntity(Uri.parse(
          "http://beauty-proceduri.ru/assets/images/gallery/srednie-svadebnie-pricheski/svadebnaya-na-srednie-volosi-53.jpg"),
          "Каталог причесок, новинки 2017", i));
      owe.add(new OurWorkEntity(Uri.parse(
          "http://beauty-proceduri.ru/assets/images/gallery/srednie-svadebnie-pricheski/svadebnaya-na-srednie-volosi-53.jpg"),
          "Прически и стрижки", i));
      owe.add(new OurWorkEntity(Uri.parse(
          "http://beauty-proceduri.ru/assets/images/gallery/srednie-svadebnie-pricheski/svadebnaya-na-srednie-volosi-53.jpg"),
          "COMMUNIQUE Прически и стрижки Stafford COMMUNIQUE Прически и стрижки Stafford COMMUNIQUE Прически и стрижки Stafford",
          i));
    }
    return Observable.just(owe);
  }

  public Observable<NewsEntity> fetchNewsPreview() {
    return Observable.just(new NewsEntity(
        Uri.parse("http://mac.h-cdn.co/assets/16/16/640x320/landscape-1461093915-ciara-zoom.jpg"),
        "Short description Short description Short description Short description", "23.02.2017"));
  }

  public Observable<List<NewsEntity>> fetchAllNews() {
    ArrayList<NewsEntity> ne = new ArrayList<>();
    for (int i = 0; i < 9; i++) {
      ne.add(new NewsEntity(
          Uri.parse("http://mac.h-cdn.co/assets/16/16/640x320/landscape-1461093915-ciara-zoom.jpg"),
          "Short description ShortShort description ShortShort description ShortShort description ShortShort description ShortShort description ShortShort description Short Short description Short description",
          "23.02.2017"));
      ne.add(new NewsEntity(
          Uri.parse("http://mac.h-cdn.co/assets/16/16/640x320/landscape-1461093915-ciara-zoom.jpg"),
          "Short description", "23.02.2017"));
    }
    return Observable.just(ne);
  }

  public Observable<List<LastBookingEntity>> fetchLastBooking() {
    ArrayList<LastBookingEntity> lbe = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      lbe.add(new LastBookingEntity(
          "https://s-media-cache-ak0.pinimg.com/736x/9a/34/cb/9a34cb759887396a7e46b62e39dfc60d.jpg",
          "Прически и стрижки " + i, "28.02.2017"));
    } return Observable.just(lbe);
  }
}
