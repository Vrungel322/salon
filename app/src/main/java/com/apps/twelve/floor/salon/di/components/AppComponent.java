package com.apps.twelve.floor.salon.di.components;

import com.apps.twelve.floor.salon.base.BaseActivity;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.di.modules.AppModule;
import com.apps.twelve.floor.salon.di.modules.BookingModule;
import com.apps.twelve.floor.salon.di.scopes.AppScope;
import com.apps.twelve.floor.salon.feature.booking.presenters.BookingActivityPresenter;
import com.apps.twelve.floor.salon.feature.booking.presenters.BookingFragmentPresenter;
import com.apps.twelve.floor.salon.feature.catalog.presenters.CatalogFavoriteFragmentPresenter;
import com.apps.twelve.floor.salon.feature.catalog.presenters.CatalogFragmentPresenter;
import com.apps.twelve.floor.salon.feature.catalog.presenters.CategoryDialogFragmentPresenter;
import com.apps.twelve.floor.salon.feature.catalog.presenters.GoodsDetailsFragmentPresenter;
import com.apps.twelve.floor.salon.feature.contacts.presenters.ContactsAboutFragmentPresenter;
import com.apps.twelve.floor.salon.feature.contacts.presenters.ContactsFragmentPresenter;
import com.apps.twelve.floor.salon.feature.main_screen.presenters.MainFragmentPresenter;
import com.apps.twelve.floor.salon.feature.main_screen.presenters.MyLastBookingAdapterPresenter;
import com.apps.twelve.floor.salon.feature.main_screen.presenters.SubBonusRegistrationFragmentPresenter;
import com.apps.twelve.floor.salon.feature.main_screen.presenters.SubBookingFragmentPresenter;
import com.apps.twelve.floor.salon.feature.main_screen.presenters.SubNewsFragmentPresenter;
import com.apps.twelve.floor.salon.feature.my_bonus.presenters.BonusHistoryFragmentPresenter;
import com.apps.twelve.floor.salon.feature.my_bonus.presenters.BonusHowFragmentPresenter;
import com.apps.twelve.floor.salon.feature.my_bonus.presenters.MyBonusFragmentPresenter;
import com.apps.twelve.floor.salon.feature.my_booking.presenters.ActiveMyBookFragmentPresenter;
import com.apps.twelve.floor.salon.feature.my_booking.presenters.BookDetailsFragmentPresenter;
import com.apps.twelve.floor.salon.feature.my_booking.presenters.BookingDetailActivityPresenter;
import com.apps.twelve.floor.salon.feature.my_booking.presenters.BookingListActivityPresenter;
import com.apps.twelve.floor.salon.feature.my_booking.presenters.HistoryBookingFragmentPresenter;
import com.apps.twelve.floor.salon.feature.my_booking.presenters.MyBookingAdapterPresenter;
import com.apps.twelve.floor.salon.feature.my_booking.presenters.PostponeFragmentPresenter;
import com.apps.twelve.floor.salon.feature.news.presenters.AllNewsFragmentPresenter;
import com.apps.twelve.floor.salon.feature.news.presenters.DetailNewsFragmentPresenter;
import com.apps.twelve.floor.salon.feature.news.presenters.ListNewsDetailPresenter;
import com.apps.twelve.floor.salon.feature.our_works.presenters.OurWorkFragmentPresenter;
import com.apps.twelve.floor.salon.feature.our_works.presenters.WorkDetailsFragmentPresenter;
import com.apps.twelve.floor.salon.feature.settings.fragments.AboutApplicationDialog;
import com.apps.twelve.floor.salon.feature.settings.fragments.ThemeDialogFragment;
import com.apps.twelve.floor.salon.feature.settings.presenters.AboutApplicationDialogPresenter;
import com.apps.twelve.floor.salon.feature.settings.presenters.NotificationSettingsFragmentPresenter;
import com.apps.twelve.floor.salon.feature.settings.presenters.ReportProblemFragmentPresenter;
import com.apps.twelve.floor.salon.feature.settings.presenters.SettingsActivityPresenter;
import com.apps.twelve.floor.salon.feature.settings.presenters.SettingsFragmentPresenter;
import com.apps.twelve.floor.salon.feature.settings.presenters.ThemeDialogFragmentPresenter;
import com.apps.twelve.floor.salon.feature.start_point.presenters.MainActivityPresenter;
import com.apps.twelve.floor.salon.feature.start_point.presenters.StartActivityPresenter;
import com.apps.twelve.floor.salon.utils.NetworkBroadcastReceiver;
import com.apps.twelve.floor.salon.utils.custom_views.FabNestedScroll;
import com.apps.twelve.floor.salon.utils.custom_views.FabRecyclerView;
import com.apps.twelve.floor.salon.utils.jobs.JobsCreator;
import com.apps.twelve.floor.salon.utils.jobs.NotificationJob;
import dagger.Component;

/**
 * Created by Vrungel on 25.01.2017.
 */
@AppScope @Component(modules = AppModule.class) public interface AppComponent {

  BookingComponent plusBookingComponent(BookingModule bookingModule);

  //presenters
  void inject(MainActivityPresenter presenter);

  void inject(StartActivityPresenter presenter);

  void inject(BookingActivityPresenter presenter);

  void inject(MainFragmentPresenter presenter);

  void inject(BookingFragmentPresenter presenter);

  void inject(ActiveMyBookFragmentPresenter presenter);

  void inject(BookDetailsFragmentPresenter presenter);

  void inject(MyBonusFragmentPresenter presenter);

  void inject(OurWorkFragmentPresenter presenter);

  void inject(WorkDetailsFragmentPresenter presenter);

  void inject(ContactsFragmentPresenter presenter);

  void inject(ContactsAboutFragmentPresenter presenter);

  void inject(SubNewsFragmentPresenter presenter);

  void inject(DetailNewsFragmentPresenter presenter);

  void inject(AllNewsFragmentPresenter presenter);

  void inject(SubBonusRegistrationFragmentPresenter presenter);

  void inject(SubBookingFragmentPresenter presenter);

  void inject(SettingsActivityPresenter presenter);

  void inject(SettingsFragmentPresenter presenter);

  void inject(PostponeFragmentPresenter presenter);

  void inject(NotificationSettingsFragmentPresenter presenter);

  void inject(ReportProblemFragmentPresenter presenter);

  void inject(CatalogFragmentPresenter presenter);

  void inject(GoodsDetailsFragmentPresenter presenter);

  void inject(CategoryDialogFragmentPresenter presenter);

  void inject(BonusHowFragmentPresenter presenter);

  void inject(BonusHistoryFragmentPresenter presenter);

  void inject(CatalogFavoriteFragmentPresenter presenter);

  void inject(ThemeDialogFragmentPresenter presenter);

  void inject(ListNewsDetailPresenter presenter);

  void inject(BookingListActivityPresenter presenter);

  void inject(BookingDetailActivityPresenter presenter);

  void inject(AboutApplicationDialogPresenter aboutApplicationDialogPresenter);

  void inject(HistoryBookingFragmentPresenter aboutApplicationDialogPresenter);

  //activities
  void inject(BaseActivity activity);

  //fragments
  void inject(BaseFragment fragment);

  //dialog fragment
  void inject(ThemeDialogFragment fragment);

  void inject(AboutApplicationDialog aboutApplicationDialog);

  //adapters
  void inject(MyLastBookingAdapterPresenter presenter);

  void inject(MyBookingAdapterPresenter presenter);

  //job
  void inject(NotificationJob notificationJob);

  void inject(JobsCreator jobsCreator);

  //custom views
  void inject(FabNestedScroll scroll);

  void inject(FabRecyclerView view);

  //BroadcastReceivers
  void inject(NetworkBroadcastReceiver networkBroadcastReceiver);
}
