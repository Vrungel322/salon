package com.apps.twelve.floor.salon.feature.settings.views;

import com.apps.twelve.floor.salon.data.model.PartnerEntity;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import java.util.List;

/**
 * Created by alexandersvyatetsky on 16/08/17.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IPartnersFragment
    extends MvpView {
  void showPartners(List<PartnerEntity> partnerEntityList);
}
