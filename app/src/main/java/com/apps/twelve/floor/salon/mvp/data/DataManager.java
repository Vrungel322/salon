package com.apps.twelve.floor.salon.mvp.data;

import android.net.Uri;
import com.apps.twelve.floor.salon.mvp.data.model.LastBookingEntity;
import com.apps.twelve.floor.salon.mvp.data.model.NewsEntity;
import com.apps.twelve.floor.salon.mvp.data.model.OurWorkEntity;
import com.apps.twelve.floor.salon.mvp.data.model.PhotoWorksEntity;
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
    ArrayList<PhotoWorksEntity> listImageUrl = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      listImageUrl.add(new PhotoWorksEntity(1,
          "Каждая свадебная прическа это своего рода шедевр. Если платья и аксессуары могут повторяться, то прическа всегда индивидуальна. "
              + "Даже если она создается по какому-то образцу, все равно у каждой невесты она будет выглядеть по-своему неповторимо.",
          true,
          "http://liza.ua/wp-content/uploads/2016/12/tild3332-6430-4264-b238-343934343733__79615508f7484025a538db52b07e5b04.jpg"));
      listImageUrl.add(new PhotoWorksEntity(2,
          "Свой свадебный образ каждая девушка продумывает заблаговременно, ведь все должно быть гармоничным и сочетаемым: платье, аксессуары",
          false,
          "http://stilnyiy-mir.ru/wp-content/uploads/2016/11/Modnye-pricheski-s-kudrjami-2017-zhenskie-na-srednie-volosy-foto-24-e1478638531162.jpg"));
      listImageUrl.add(new PhotoWorksEntity(3,
          "Эти составляющие образа подбирают в одном стиле, наилучший вариант – соответствие постоянному стилю невесты.",
          true, "http://v-2017.com/wp-content/uploads/2016/07/Hair-fashion-2017-12.jpg"));
    }
    for (int i = 0; i < 5; i++) {
      owe.add(new OurWorkEntity(Uri.parse(
          "http://beauty-proceduri.ru/assets/images/gallery/srednie-svadebnie-pricheski/svadebnaya-na-srednie-volosi-53.jpg"),
          "Каталог причесок, новинки 2017", i, listImageUrl));
      owe.add(new OurWorkEntity(Uri.parse(
          "http://beauty-proceduri.ru/assets/images/gallery/srednie-svadebnie-pricheski/svadebnaya-na-srednie-volosi-53.jpg"),
          "Прически и стрижки", i, listImageUrl));
      owe.add(new OurWorkEntity(Uri.parse(
          "http://beauty-proceduri.ru/assets/images/gallery/srednie-svadebnie-pricheski/svadebnaya-na-srednie-volosi-53.jpg"),
          "COMMUNIQUE Прически и стрижки Stafford COMMUNIQUE Прически и стрижки Stafford COMMUNIQUE Прически и стрижки Stafford",
          i, listImageUrl));
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
    }
    return Observable.just(lbe);
  }
}
