package se.tst.addressbook.model;

import java.io.File;
import java.util.Objects;

public class ContactDate {

  private int id;
  private String name;
  private String lastname;
  private String group;
  private String address;
  private String tlfnhome;
  private String tlfnmobile;
  private String tlfnwork;
  private String allPhones;
  private String mail;
  private String mail2;
  private String mail3;
  private String allMail;
  private File photo;


  public File getPhoto() {
    return photo;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getHomePhone() {
    return tlfnhome;
  }

  public String getMobilePhone() {
    return tlfnmobile;
  }

  public String getWorkPhone() {
    return tlfnwork;
  }

  public String getMail() {
    return mail;
  }

  public String getMail2() {
    return mail2;
  }

  public String getMail3() {
    return mail3;
  }

  public String getAllMail() {
    return allMail;
  }

  public String getGroup() {
    return group;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public ContactDate withId(int id) {
    this.id = id;
    return this;
  }

  public ContactDate withName(String name) {
    this.name = name;
    return this;
  }

  public ContactDate withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactDate withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactDate withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactDate withHomePhone(String tlfnhome) {
    this.tlfnhome = tlfnhome;
    return this;
  }

  public ContactDate withMobilePhone(String tlfnmobile) {
    this.tlfnmobile = tlfnmobile;
    return this;
  }

  public ContactDate withWorkPhone(String tlfnwork) {
    this.tlfnwork = tlfnwork;
    return this;
  }

  public ContactDate withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactDate withMail(String mail) {
    this.mail = mail;
    return this;
  }

  public ContactDate withMail2(String mail2) {
    this.mail2 = mail2;
    return this;
  }

  public ContactDate withMail3(String mail3) {
    this.mail3 = mail3;
    return this;
  }

  public ContactDate withAllMail(String allMail) {
    this.allMail = allMail;
    return this;
  }

  public ContactDate withPhoto(File photo) {
    this.photo = photo;
    return this;
  }




  @Override
  public String toString() {
    return "ContactDate{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", lastname='" + lastname + '\'' +
            ", group='" + group + '\'' +
            '}';
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactDate that = (ContactDate) o;
    return id == that.id &&
            Objects.equals(name, that.name) &&
            Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, lastname);
  }

}
