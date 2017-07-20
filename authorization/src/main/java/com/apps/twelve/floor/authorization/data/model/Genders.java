package com.apps.twelve.floor.authorization.data.model;
/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public enum Genders {

  MALE("Мужской"), FEMALE("Женский"), UNKNOWN("Не указан");

  private String gender;

  Genders(String gender) {
    this.gender = gender;
  }

  public static String findGenderByKey(String value) {
    if (value == null) {
      return null;
    }
    Genders[] saEnums = Genders.values();
    for (Genders saEnum : saEnums) {
      if (saEnum.getName().equals(value)) {
        return saEnum.getGender();
      }
    }
    return Genders.UNKNOWN.getGender();
  }

  public static int getGenderPosition(String gender) {
    int selected;
    switch (gender) {
      case "Не указан":
        selected = 0;
        break;
      case "Мужской":
        selected = 1;
        break;
      case "Женский":
        selected = 2;
        break;
      default:
        selected = 0;
        break;
    }
    return selected;
  }

  public String getGender() {
    return gender;
  }

  public String getName() {
    return this.toString().toLowerCase();
  }
}
