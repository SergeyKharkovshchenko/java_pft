package ru.stqa.pft.addressbook.model;

/**
 * Created by Sergey on 21.04.2016.
 */
public class ContactData {

  private final String name;
  private final String lastname;
  private final String email;
  private String group;

  public ContactData(String name, String lastname, String email, String group) {
    this.name = name;
    this.lastname = lastname;
    this.email = email;
    this.group = group;
  }

  public String getName() {
  return name;
  }

  public String getLastname() {
    return lastname;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }
}
