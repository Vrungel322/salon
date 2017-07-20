package com.apps.twelve.floor.authorization.utils;

/**
 * Created by John on 26.01.2017.
 */

public final class AuthRxBusHelper {

  public static class EventForNextStep {
    public String fragmentTag;

    public EventForNextStep(String fragmentTag) {
      this.fragmentTag = fragmentTag;
    }
  }

  public static class UpdateUserInfo {
  }

  public static class SuccessResponse {
  }

  public static class MessageContentError {

    private String type;
    private String message;

    public MessageContentError(String message) {
      this.message = message;
    }

    public String getMessage() {
      return message;
    }
  }

  public static class MessageConnectException {
  }

  public static class UnauthorizedEvent {
  }

  public static class CloseActivityEvent {
  }

  public static class LogoutEvent {
  }

  public static class ChangePasswordSuccess {
  }
}
