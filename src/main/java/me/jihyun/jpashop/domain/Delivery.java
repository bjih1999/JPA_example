package me.jihyun.jpashop.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Delivery {

    @Id @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    /*
    @Enumerated(EnumType.STRING)
    - 기본 값은 EnumType.ORDINAL
    - ORDINAL은 enum값이 1, 2, 3... 과 같은 방식으로 저장됨
        -> 중간에 다른 상태 값이 추가되면 망함 ** ORDINAL은 절대 사용하지 말것! **
     */
    private DeliveryStatus status;
}
