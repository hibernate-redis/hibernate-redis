package org.hibernate.test.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * org.hibernate.test.domain.Person
 *
 * @author sunghyouk.bae@gmail.com
 * @since 13. 4. 6. 오전 12:54
 */
@Entity
@org.hibernate.annotations.Cache(region = "common", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Person implements Serializable {

    private static final long serialVersionUID = -8245742950718661800L;

    @Id
    @GeneratedValue
    private Long id;

    private int age;
    private String firstname;
    private String lastname;

    private Float weight = 77.7f;
    private Double height = 188.8d;

    @ManyToMany(mappedBy = "participants")
    private List<Event> events = new ArrayList<Event>();

    @CollectionTable(name = "EmailAddressSet", joinColumns = @JoinColumn(name = "PersonId"))
    @ElementCollection(targetClass = String.class)
    @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<String> emailAddresses = new HashSet<String>();

    @CollectionTable(name = "PhoneNumberSet", joinColumns = @JoinColumn(name = "ProductItemId"))
    @ElementCollection(targetClass = PhoneNumber.class)
    @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<PhoneNumber> phoneNumbers = new HashSet<PhoneNumber>();

    @CollectionTable(name = "TailsManList", joinColumns = @JoinColumn(name = "PersonId"))
    @ElementCollection(targetClass = String.class)
    @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<String> tailsmans = new ArrayList<String>();

    @Override
    public boolean equals(Object obj) {
        return (obj != null) && (obj instanceof Person) && hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        return (id != null)
                ? id.hashCode()
                : Objects.hash(firstname, lastname, weight, height, age);
    }

    public Long getId() {
        return this.id;
    }

    public int getAge() {
        return this.age;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public Float getWeight() {
        return this.weight;
    }

    public Double getHeight() {
        return this.height;
    }

    public List<Event> getEvents() {
        return this.events;
    }

    public Set<String> getEmailAddresses() {
        return this.emailAddresses;
    }

    public Set<PhoneNumber> getPhoneNumbers() {
        return this.phoneNumbers;
    }

    public List<String> getTailsmans() {
        return this.tailsmans;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void setEmailAddresses(Set<String> emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    public void setPhoneNumbers(Set<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public void setTailsmans(List<String> tailsmans) {
        this.tailsmans = tailsmans;
    }
}
