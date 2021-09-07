package me.jihyun.jpashop.repository;

import me.jihyun.jpashop.domain.Order;

import java.util.List;

public interface OrderCustomRepository {

    public void save(Order order);

    public Order findOne(Long id);

    public List<Order> findAllByCriteria(OrderSearch orderSearch);

    public List<Order> findAll(OrderSearch orderSearch);

}
