package com.driver;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

//    @Autowired
//    OrderRepository orderRepository = new OrderRepository();

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public void addOrder(Order order){
        orderRepository.saveOrder(order);
    }

    public void addPartner(String partnerId){
        orderRepository.savePartner(partnerId);
    }

    public void assignOrderToPartner(String orderId, String partnerId){
        orderRepository.saveOrderPartnerMap(orderId, partnerId);
    }

    public Order getOrderById(String orderId){
        return orderRepository.findOrderById(orderId);
    }

    public DeliveryPartner getPartnerById(String partnerId){
        return orderRepository.findPartnerById(partnerId);
    }

    public Integer getOrderCountByPartnerId(String partnerId){
        return orderRepository.findOrderCountByPartnerId(partnerId);
    }

    public List<String> getOrdersByPartnerId(String partnerId){
        return orderRepository.findOrdersByPartnerId(partnerId);
    }

    public List<String> getAllOrders(){
        return orderRepository.findAllOrders();
    }

    public void deletePartnerById(String partnerId){
        orderRepository.deletePartner(partnerId);
    }

    public void deleteOrderById(String orderId){
        orderRepository.deleteOrder(orderId);
    }

    public Integer getCountOfUnassignedOrders(){
        return orderRepository.findCountOfUnassignedOrders();
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String partnerId, String time){
        return orderRepository.findOrdersLeftAfterGivenTimeByPartnerId(partnerId, time);
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId){
        return orderRepository.findLastDeliveryTimeByPartnerId(partnerId);
    }
}