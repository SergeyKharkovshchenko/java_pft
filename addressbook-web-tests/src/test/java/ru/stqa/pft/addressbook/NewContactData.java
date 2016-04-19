package ru.stqa.pft.addressbook;

public class NewContactData {
  private final String name;
  private final String lastname;
  private final String email;

  public NewContactData(String name, String lastname, String email) {
    this.name = name;
    this.lastname = lastname;
    this.email = email;
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
}
