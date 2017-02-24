package com.apps.twelve.floor.salon.mvp.data;

import android.net.Uri;
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
          "COMMUNIQUE Прически и стрижки Stafford COMMUNIQUE Прически и стрижки Stafford COMMUNIQUE Прически и стрижки Stafford", i));
    }
    return Observable.just(owe);
  }
}
