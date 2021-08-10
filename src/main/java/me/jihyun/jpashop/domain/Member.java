package me.jihyun.jpashop.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String username;

    /*
    Embeddable - Embedded
    - Entity에서 속성이 가질 타입을 정의하고 사용할 때 사용하는 애노테이션
    - 정의 하는 곳에 Embeddable, 사용하는 곳에 Embedded를 붙여줌
    - 둘 중 하나만 해주어도 되지만, 관용적으로 두 군데 다 붙여줌 -> 가독성이 좋아짐
     */
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}
