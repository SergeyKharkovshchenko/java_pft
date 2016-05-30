package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;

/**
 * Created by Sergey on 21.04.2016.
 */

@XStreamAlias("contacts")

@Entity
@Table(name = "addressbook")
public class ContactData {
  @Id
  @Column(name ="Id")
  @XStreamOmitField
  private int id = Integer.MAX_VALUE;
  @Column(name ="firstname")
  private String name;
  @Column(name ="lastname")
  private String lastname;

  @Transient //поле не будет извлекаться из бд
  @Column(name ="address")
  @Type(type = "text")
  private String address;

  @Transient //поле не будет извлекаться из бд
  @XStreamOmitField
  private String email;

  @Transient //поле не будет извлекаться из бд
  private String group;


  @Column(name ="home")
  @Type(type = "text")
  private String homePhone;

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", lastname='" + lastname + '\'' +
            ", group='" + group + '\'' +
            ", homePhone='" + homePhone + '\'' +
            ", mobilePhone='" + mobilePhone + '\'' +
            ", workPhone='" + workPhone + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
    if (homePhone != null ? !homePhone.equals(that.homePhone) : that.homePhone != null) return false;
    if (mobilePhone != null ? !mobilePhone.equals(that.mobilePhone) : that.mobilePhone != null) return false;
    return workPhone != null ? workPhone.equals(that.workPhone) : that.workPhone == null;

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
    result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
    result = 31 * result + (workPhone != null ? workPhone.hashCode() : 0);
    return result;
  }

  @Column(name ="mobile")
  @Type(type = "text")
  private String mobilePhone;

  @Column(name ="work")
  @Type(type = "text")
  private String workPhone;

  @Transient //поле не будет извлекаться из бд
  @XStreamOmitField
  private String allPhones;
  @Transient //поле не будет извлекаться из бд
  private String email1;
  @Transient //поле не будет извлекаться из бд
  private String email2;
  @Transient //поле не будет извлекаться из бд
  private String email3;
  @Transient //поле не будет извлекаться из бд
  @XStreamOmitField
  private String allEmails;
  @Transient //поле не будет извлекаться из бд
  @Column(name ="photo")
  @Type(type = "text")
  private String photo;

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public File getPhoto() {
    return new File (photo);
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withEmail1(String email1) {
    this.email1 = email1;
    return  this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return  this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return  this;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return  this;
  }

  public String getAddress() {
    return address;
  }

  public String getEmail1() {
    return email1;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public int getId() {
    return id;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withName(String name) {
    this.name = name;
    return this;
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
