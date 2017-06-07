package com.apps.twelve.floor.salon.utils;

/**
 * Created by John on 26.01.2017.
 */

public final class RxBusHelper {

  public static class EventForNextStep {
    public String fragmentTag;

    public EventForNextStep(String fragmentTag) {
      this.fragmentTag = fragmentTag;
    }
  }

  public static class BackCategories {
  }

  public static class StateBackBookingMaster {
  }

  public static class StateBackBookingService {
  }

  public static class CloseBookingService {
  }

  public static class UpdateLastBookingListEvent {
  }

  public static class UpdateNews {
  }

  public static class UpdateBonusFromChildren {
  }

  public static class UpdateBonusFromParent {
  }

  public static class UpdateBonusSwipe {
  }

  public static class UpdateUserInfo {
  }

  public static class StopRefreshNewsMainFragment {
  }

  public static class HideFloatingButton {
  }

  public static class ShowFloatingButton {
  }

  public static class SetBookingItemInMenu {
  }

  public static class SetNewsItemInMenu {

  }

  public static class SetBonusItemInMenu {

  }

  public static class UpdateOurWorkList {

  }

  public static class UpdateGoodsList {

  }

  public static class UpdateFavoriteGoodsList {

  }

  public static class ShowAuthDialog {

  }

  public static class ShowAuthDialogBooking {

  }

  public static class MessageConnectException {
  }

  public static class ReloadCatalogByCategory {
    public Integer id;
    public String title;

    public ReloadCatalogByCategory(Integer id, String title) {
      this.id = id;
      this.title = title;
    }
  }
}
