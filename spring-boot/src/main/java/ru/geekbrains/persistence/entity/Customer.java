package ru.geekbrains.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "CUSTOMER")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column
    private String contacts;

    public Customer() {
    }

    public Customer(Long id, String name, String contacts) {
        this.id = id;
        this.name = name;
        this.contacts = contacts;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContacts() {
        return contacts;
    }
}
