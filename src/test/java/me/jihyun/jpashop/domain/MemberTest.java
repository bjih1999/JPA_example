package me.jihyun.jpashop.domain;

import me.jihyun.jpashop.domain.Item.Book;
import me.jihyun.jpashop.domain.Item.Item;
import me.jihyun.jpashop.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserGroupRepository groupRepository;

    @Autowired
    EntityManager em;

    @Test
    public void 로딩테스트() {
        Member member = memberRepository.save(Member.of("Jihyun", new Address("city", "street", "zipcode"), new UserGroup()));
        Book book = new Book();
        book.setName("test");
        book.setStockQuantity(30);

        itemRepository.save(book);

        Item orderBook = itemRepository.findAll().get(0);

        orderRepository.save(Order.of(member, Delivery.of(member.getAddress()), OrderItem.createOrderItem(orderBook, 1111, 1)));
        orderRepository.save(Order.of(member, Delivery.of(member.getAddress()), OrderItem.createOrderItem(orderBook, 1111, 1)));
        orderRepository.save(Order.of(member, Delivery.of(member.getAddress()), OrderItem.createOrderItem(orderBook, 1111, 1)));

        List<Member> temp = memberRepository.findAll();
        assertNotNull(member);
    }

    @Test
    public void 로딩테스트2() {
        Member member = memberRepository.save(Member.of("Jihyun", new Address("city", "street", "zipcode"), new UserGroup()));
        Member member1 = memberRepository.save(Member.of("Jihyun2", new Address("city", "street", "zipcode"), new UserGroup()));
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

    @Test
    @Transactional
    public void 로딩테스트3() {
        UserGroup group1 = UserGroup.of("testGroup");
        UserGroup group2 = UserGroup.of("testGroup2");

        Member member = memberRepository.save(Member.of("Jihyun", new Address("city", "street", "zipcode"), group1));
        Member member1 = memberRepository.save(Member.of("Jihyun2", new Address("city", "street", "zipcode"), group1));
        Member member2 = memberRepository.save(Member.of("Jihyun2", new Address("city", "street", "zipcode"), group2));

        group1.addMember(member);
        group1.addMember(member1);
        group2.addMember(member2);
        groupRepository.save(group1);
        groupRepository.save(group2);
//        em.flush();

        List<UserGroup> groupList = groupRepository.findAll();
        groupList.stream().mapToInt(tempGroup -> tempGroup.getMembers().size()).forEach(System.out::println);

        assertNotNull(groupList);
    }

}