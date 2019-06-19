package se.tst.addressbook.model;

import java.util.Objects;

public class ContactDate {
  private final String name;
  private final String lastname;
  private String group;
  private final String address;
  private final String tlfn;
  private final String mail;

  public ContactDate(String name, String lastname, String group, String address, String tlfn, String mail) {
    this.name = name;
    this.lastname = lastname;
    this.group = group;
    this.address = address;
    this.tlfn = tlfn;
    this.mail = mail;
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

  public String getTlfn() {
    return tlfn;
  }

  public String getMail() {
    return mail;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public String toString() {
    return "ContactDate{" +
            "name='" + name + '\'' +
            ", lastname='" + lastname + '\'' +
            ", address='" + address + '\'' +
            '}';
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactDate that = (ContactDate) o;
    return Objects.equals(name, that.name) &&
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(address, that.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, lastname, address);
  }


}
