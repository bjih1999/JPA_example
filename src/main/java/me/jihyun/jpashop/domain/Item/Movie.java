package me.jihyun.jpashop.domain.Item;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Movie")
@Data
public class Movie extends Item{

    private String director;

    private String actor;
}
