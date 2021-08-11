package me.jihyun.jpashop.domain;

import lombok.Data;
import me.jihyun.jpashop.domain.Item.Item;

import javax.persistence.*;

@Entity
@Data
public class OrderItem {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; // 주문 가격

    private int count;  // 주문 수량
}
