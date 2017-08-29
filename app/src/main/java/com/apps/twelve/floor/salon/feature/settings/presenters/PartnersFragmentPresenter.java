package com.apps.twelve.floor.salon.feature.settings.presenters;

import com.apps.twelve.floor.authorization.utils.ThreadSchedulers;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.model.PartnerEntity;
import com.apps.twelve.floor.salon.feature.settings.views.IPartnersFragment;
import com.arellomobile.mvp.InjectViewState;
import rx.Subscription;

/**
 * Created by alexandersvyatetsky on 16/08/17.
 */

@InjectViewState public class PartnersFragmentPresenter extends BasePresenter<IPartnersFragment> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().showPartners(mDataManager.getAllElementsFromDB(PartnerEntity.class));

    Subscription subscription = mDataManager.fetchListOfPartners()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(partnerEntities -> {
          cacheEntities(partnerEntities, PartnerEntity.class);
          getViewState().showPartners(partnerEntities);
        });
    addToUnsubscription(subscription);
  }
}