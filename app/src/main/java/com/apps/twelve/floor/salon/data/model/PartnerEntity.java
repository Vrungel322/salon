package com.apps.twelve.floor.salon.data.model;

import java.util.List;

/**
 * Created by alexandersvyatetsky on 16/08/17.
 */

public class PartnerEntity {
  private String id;
  private String name;
  private List<String> addresses;
  private List<String> phones;
  private List<String> emails;
  private List<String> webSites;

  public PartnerEntity(String id, String name, List<String> addresses, List<String> phones,
      List<String> emails, List<String> webSites) {
    this.id = id;
    this.name = name;
    this.addresses = addresses;
    this.phones = phones;
    this.emails = emails;
    this.webSites = webSites;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<String> addresses) {
    this.addresses = addresses;
  }

  public List<String> getPhones() {
    return phones;
  }

  public void setPhones(List<String> phones) {
    this.phones = phones;
  }

  public List<String> getEmails() {
    return emails;
  }

  public void setEmails(List<String> emails) {
    this.emails = emails;
  }

  public List<String> getWebSites() {
    return webSites;
  }

  public void setWebSites(List<String> webSites) {
    this.webSites = webSites;
  }
}
