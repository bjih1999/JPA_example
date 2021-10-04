package me.jihyun.jpashop.domain;

import me.jihyun.jpashop.domain.Item.Book;
import me.jihyun.jpashop.domain.Item.Item;
import me.jihyun.jpashop.repository.ItemRepository;
import me.jihyun.jpashop.repository.MemberRepositoryCustomImpl;
import me.jihyun.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberTest {

    @Autowired
    MemberRepositoryCustomImpl memberRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 로딩테스트() {
        Member member = memberRepository.save(Member.of("Jihyun", new Address("city", "street", "zipcode")));
        Book book = new Book();
        book.setName("test");
        book.setStockQuantity(30);

        itemRepository.save(book);

        Item orderBook = itemRepository.findAll().get(0);

        orderRepository.save(Order.of(member, Delivery.of(member.getAddress()), OrderItem.createOrderItem(orderBook, 1111, 1)));
        orderRepository.save(Order.of(member, Delivery.of(member.getAddress()), OrderItem.createOrderItem(orderBook, 1111, 1)));
        orderRepository.save(Order.of(member, Delivery.of(member.getAddress()), OrderItem.createOrderItem(orderBook, 1111, 1)));

        memberRepository.findAll();
        assertNotNull(member);
    }

    @Test
    public void 로딩테스트2() {
        Member member = memberRepository.save(Member.of("Jihyun", new Address("city", "street", "zipcode")));
        Member member1 = memberRepository.save(Member.of("Jihyun2", new Address("city", "street", "zipcode")));
        Book book = new Book();
        book.setName("test");
        book.setStockQuantity(30);

        itemRepository.save(book);

        Item orderBook = itemRepository.findAll().get(0);

        orderRepository.save(Order.of(member, Delivery.of(member.getAddress()), OrderItem.createOrderItem(orderBook, 1111, 1)));
        orderRepository.save(Order.of(member, Delivery.of(member.getAddress()), OrderItem.createOrderItem(orderBook, 1111, 1)));
        orderRepository.save(Order.of(member1, Delivery.of(member.getAddress()), OrderItem.createOrderItem(orderBook, 1111, 1)));

        List<Member> members = memberRepository.findAll();
        for (Member mem : members) {
            System.out.println(mem.getOrders().size());
        }
        assertNotNull(member);
    }

}