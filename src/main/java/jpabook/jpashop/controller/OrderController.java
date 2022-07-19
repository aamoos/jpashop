package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderSearch;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.MemberService;
import jpabook.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
* @package : jpabook.jpashop.controller
* @name : OrderController.java
* @date : 2022-07-19 오전 11:23
* @author : 김재성
* @Description: 상품 주문 controller
**/
@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    /**
    * @methodName : createForm
    * @date : 2022-07-19 오전 11:23
    * @author : 김재성
    * @Description: 상품 주문 목록화면
    **/
    @GetMapping(value = "/order")
    public String createForm(Model model){

        //주문회원 리스트
        List<Member> members = memberService.findMembers();

        //상품명 리스트
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    /**
    * @methodName : order
    * @date : 2022-07-19 오전 11:23
    * @author : 김재성
    * @Description: 주문
    **/
    @PostMapping(value = "/order")
    public String order(@RequestParam("memberId") Long memberId
            , @RequestParam("itemId") Long itemId
            , @RequestParam("count") int count){
        //주문
        orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }

    /**
    * @methodName : orderList
    * @date : 2022-07-19 오후 12:59
    * @author : 김재성
    * @Description: 주문내역 목록
    **/
    @GetMapping(value = "/orders")
    public String orderList(@ModelAttribute("orderSearch")OrderSearch orderSearch, Model model){
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);
        return "order/orderList";
    }

    /**
    * @methodName : cancelOrder
    * @date : 2022-07-19 오후 1:02
    * @author : 김재성
    * @Description: 주문취소
    **/
    @PostMapping(value = "/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId){
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }

}
