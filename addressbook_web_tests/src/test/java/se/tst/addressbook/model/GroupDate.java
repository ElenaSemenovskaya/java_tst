package se.tst.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("group")
@Entity
@Table(name = "group_list")
public class GroupDate {
  @XStreamOmitField
  @Id
  @Column(name = "group_id")
  private int id = Integer.MAX_VALUE;
  @Column(name = "group_name")
  private String name;
  @Column(name = "group_header")
  @Type(type = "text")
  private String header;
  @Column(name = "group_footer")
  @Type(type = "text")
  private String footer;

  @ManyToMany(mappedBy = "groups")
  private Set<ContactDate> contacts = new HashSet<ContactDate>();

  public int getId() {
    return id;
  }

  public Contacts getContacts() {
    return new Contacts(contacts);
  }

  public GroupDate withId(int id) {
    this.id = id;
    return this;
  }

  public GroupDate withName(String name) {
    this.name = name;
    return this;
  }

  public GroupDate withHeader(String header) {
    this.header = header;
    return this;
  }

  public GroupDate withFooter(String footer) {
    this.footer = footer;
    return this;
  }

  public String getName() {
    return name;
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }




  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupDate groupDate = (GroupDate) o;
    return id == groupDate.id &&
            Objects.equals(name, groupDate.name) &&
            Objects.equals(header, groupDate.header) &&
            Objects.equals(footer, groupDate.footer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, header, footer);
  }



  @Override
  public String toString() {
    return "GroupDate{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            '}';
  }

}
