package se.tst.addressbook;

public class ContactDate {
  private final String name;
  private final String lastname;
  private final String address;
  private final String tlfn;
  private final String mail;

  public ContactDate(String name, String lastname, String address, String tlfn, String mail) {
    this.name = name;
    this.lastname = lastname;
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
}
