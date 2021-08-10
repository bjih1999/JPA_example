package me.jihyun.jpashop.domain;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Embeddable
@Data
public class Address {

    private String city;

    private String street;

    private String zipcode;

}
