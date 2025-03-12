package com.driver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrderController {

//@Autowired
//OrderService orderService;
    private  OrderService orderService;
  //  @Autowired
    public OrderController() {}

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping("/add-order")
    public ResponseEntity<String> addOrder(@RequestBody Order order) {
        orderService.addOrder(order);
        return new ResponseEntity<>("New order added successfully", HttpStatus.CREATED);
    }

    @PostMapping("/add-partner/{partnerId}")
    public ResponseEntity<String> addPartner(@PathVariable String partnerId) {
        orderService.addPartner(partnerId);
        return new ResponseEntity<>("New delivery partner added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/add-order-partner-pair")
    public ResponseEntity<String> addOrderPartnerPair(@RequestParam String orderId, @RequestParam String partnerId) {
        orderService.assignOrderToPartner(orderId, partnerId);
        return new ResponseEntity<>("New order-partner pair added successfully", HttpStatus.OK);
    }

    @GetMapping("/get-order-by-id/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable String orderId) {
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/get-partner-by-id/{partnerId}")
    public ResponseEntity<DeliveryPartner> getPartnerById(@PathVariable String partnerId) {
        DeliveryPartner partner = orderService.getPartnerById(partnerId);
        if (partner == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(partner, HttpStatus.OK);
    }

    @GetMapping("/get-order-count-by-partner-id/{partnerId}")
    public ResponseEntity<Integer> getOrderCountByPartnerId(@PathVariable String partnerId) {
        Integer orderCount = orderService.getOrderCountByPartnerId(partnerId);
        return new ResponseEntity<>(orderCount, HttpStatus.OK);
    }

    @GetMapping("/get-orders-by-partner-id/{partnerId}")
    public ResponseEntity<List<String>> getOrdersByPartnerId(@PathVariable String partnerId) {
        List<String> orders = orderService.getOrdersByPartnerId(partnerId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/get-all-orders")
    public ResponseEntity<List<String>> getAllOrders() {
        List<String> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/get-count-of-unassigned-orders")
    public ResponseEntity<Integer> getCountOfUnassignedOrders() {
        Integer countOfOrders = orderService.getCountOfUnassignedOrders();
        return new ResponseEntity<>(countOfOrders, HttpStatus.OK);
    }

    @GetMapping("/get-count-of-orders-left-after-given-time/{partnerId}/{time}")
    public ResponseEntity<Integer> getOrdersLeftAfterGivenTimeByPartnerId(@PathVariable String partnerId, @PathVariable String time) {
        Integer countOfOrders = orderService.getOrdersLeftAfterGivenTimeByPartnerId(partnerId, time);
        return new ResponseEntity<>(countOfOrders, HttpStatus.OK);
    }

    @GetMapping("/get-last-delivery-time/{partnerId}")
    public ResponseEntity<String> getLastDeliveryTimeByPartnerId(@PathVariable String partnerId) {
        String time = orderService.getLastDeliveryTimeByPartnerId(partnerId);
        return new ResponseEntity<>(time, HttpStatus.OK);
    }

  @DeleteMapping("/delete-partner-by-id/{partnerId}")
   public ResponseEntity<String> deletePartnerById(@PathVariable String partnerId) {
     orderService.deletePartnerById(partnerId);
    return new ResponseEntity<>(partnerId + " removed successfully", HttpStatus.OK);
   }
  @DeleteMapping("/delete-order-by-id/{orderId}")
    public ResponseEntity<String> deleteOrderById(@PathVariable String orderId) {
      orderService.deleteOrderById(orderId);
       return new ResponseEntity<>(orderId + " removed successfully", HttpStatus.OK);
   }
}