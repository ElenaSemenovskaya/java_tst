package se.tst.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactDate> {

    private Set<ContactDate> delegate;

    public Contacts(Contacts contacts) {
        this.delegate = new HashSet<ContactDate>(contacts.delegate);
    }

    public Contacts() {
        this.delegate = new HashSet<ContactDate> ();
    }

    public Contacts(Collection<ContactDate> contacts) {
        this.delegate = new HashSet<ContactDate>(contacts);
    }

    @Override
    protected Set<ContactDate> delegate() {
        return delegate;
    }

    public Contacts withAdded (ContactDate contact) {
        Contacts contacts = new Contacts(this);
        contacts.add(contact);
        return contacts;
    }

    public Contacts without (ContactDate contact) {
        Contacts contacts = new Contacts(this);
        contacts.remove(contact);
        return contacts;
    }
}
