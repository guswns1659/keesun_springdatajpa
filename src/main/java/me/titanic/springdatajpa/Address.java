package me.titanic.springdatajpa;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {

    @Column
    private String street;

    private String city;

    private String state;

    private String zipCode;
}
