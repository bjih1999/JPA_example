package me.jihyun.jpashop.Controller;

import lombok.RequiredArgsConstructor;
import me.jihyun.jpashop.domain.Item.Item;
import me.jihyun.jpashop.domain.Member;
import me.jihyun.jpashop.domain.Order;
import me.jihyun.jpashop.repository.OrderSearch;
import me.jihyun.jpashop.service.ItemService;
import me.jihyun.jpashop.service.MemberService;
import me.jihyun.jpashop.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private final MemberService memberService;

    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model) {
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count) {
        orderService.order(memberId, itemId, count);

        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {

        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);

        return "order/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String orderCancel(@PathVariable("orderId") Long orderId) {
        orderService.cancleOrder(orderId);

        return "redirect:/orders";
    }

}
