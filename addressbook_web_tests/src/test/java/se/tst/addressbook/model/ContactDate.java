package se.tst.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")

@XStreamAlias("contact")

public class ContactDate {
  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "firstname")
  private String name;

  @Column(name = "lastname")
  private String lastname;

  @Column(name = "address")
  @Type(type = "text")
  private String address;

  @Column(name = "home")
  @Type(type = "text")
  private String tlfnhome;

  @Column(name = "mobile")
  @Type(type = "text")
  private String tlfnmobile;

  @Column(name = "work")
  @Type(type = "text")
  private String tlfnwork;

  @Transient
  private String allPhones;

  @Column(name = "email")
  @Type(type = "text")
  private String mail;

  @Column(name = "email2")
  @Type(type = "text")
  private String mail2;

  @Column(name = "email3")
  @Type(type = "text")
  private String mail3;

  @Transient
  private String allMail;

  @Column(name = "photo")
  @Type(type = "text")
  private String photo;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups"
          , joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupDate> groups = new HashSet<GroupDate>();


  public File getPhoto() {
    return new File (photo); //преобразование возращаемого значения из String в File(photo)
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

  public String getAllPhones() {
    return allPhones;
  }

  public Groups getGroups() {
    return new Groups(groups);
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
    this.photo = photo.getPath();  //извлекаем путь к файлу
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactDate that = (ContactDate) o;
    return id == that.id &&
            Objects.equals(name, that.name) &&
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(address, that.address) &&
            Objects.equals(tlfnhome, that.tlfnhome) &&
            Objects.equals(tlfnmobile, that.tlfnmobile) &&
            Objects.equals(tlfnwork, that.tlfnwork) &&
            Objects.equals(mail, that.mail) &&
            Objects.equals(mail2, that.mail2) &&
            Objects.equals(mail3, that.mail3);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, lastname, address, tlfnhome, tlfnmobile, tlfnwork, mail, mail2, mail3);
  }

  @Override
  public String toString() {
    return "ContactDate{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", lastname='" + lastname + '\'' +
            ", address='" + address + '\'' +
            ", tlfnhome='" + tlfnhome + '\'' +
            ", tlfnmobile='" + tlfnmobile + '\'' +
            ", tlfnwork='" + tlfnwork + '\'' +
            ", mail='" + mail + '\'' +
            ", mail2='" + mail2 + '\'' +
            ", mail3='" + mail3 + '\'' +
            ", groups=" + groups +
            '}';
  }

  public ContactDate inGroup(GroupDate group) {
    groups.add(group);
    return this;
  }
}
