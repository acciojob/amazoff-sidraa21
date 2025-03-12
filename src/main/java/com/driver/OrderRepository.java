//package com.driver;
//
//import java.util.*;
//
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class OrderRepository {
//
//    private HashMap<String, Order> orderMap;
//    private HashMap<String, DeliveryPartner> partnerMap;
//    private HashMap<String, HashSet<String>> partnerToOrderMap;
//    private HashMap<String, String> orderToPartnerMap;
//
//    public OrderRepository(){
//        this.orderMap = new HashMap<String, Order>();
//        this.partnerMap = new HashMap<String, DeliveryPartner>();
//        this.partnerToOrderMap = new HashMap<String, HashSet<String>>();
//        this.orderToPartnerMap = new HashMap<String, String>();
//    }
//
////    public void saveOrder(Order order){
////        // your code here
////
////
////    }
////
////    public void savePartner(String partnerId){
////        // your code here
////        // create a new partner with given partnerId and save it
////    }
////
////    public void saveOrderPartnerMap(String orderId, String partnerId){
////        if(orderMap.containsKey(orderId) && partnerMap.containsKey(partnerId)){
////            // your code here
////            //add order to given partner's order list
////            //increase order count of partner
////            //assign partner to this order
////        }
////    }
////
////    public Order findOrderById(String orderId){
////        // your code here
////    }
////
////    public DeliveryPartner findPartnerById(String partnerId){
////        // your code here
////    }
////
////    public Integer findOrderCountByPartnerId(String partnerId){
////        // your code here
////    }
////
////    public List<String> findOrdersByPartnerId(String partnerId){
////        // your code here
////    }
////
////    public List<String> findAllOrders(){
////        // your code here
////        // return list of all orders
////    }
////
////    public void deletePartner(String partnerId){
////        // your code here
////        // delete partner by ID
////    }
////
////    public void deleteOrder(String orderId){
////        // your code here
////        // delete order by ID
////    }
////
////    public Integer findCountOfUnassignedOrders(){
////        // your code here
////    }
////
////    public Integer findOrdersLeftAfterGivenTimeByPartnerId(String timeString, String partnerId){
////        // your code here
////    }
////
////    public String findLastDeliveryTimeByPartnerId(String partnerId){
////        // your code here
////        // code should return string in format HH:MM
////    }
////}
//
//public void saveOrder(Order order) {
//    orderMap.put(order.getId(), order);
//}
//
//public void savePartner(String partnerId) {
//    partnerMap.put(partnerId, new DeliveryPartner(partnerId));
//}
//
//public void saveOrderPartnerMap(String orderId, String partnerId) {
//    if (orderMap.containsKey(orderId) && partnerMap.containsKey(partnerId)) {
//        orderPartnerMap.put(orderId, partnerId);
//        partnerOrdersMap.computeIfAbsent(partnerId, k -> new ArrayList<>()).add(orderId);
//        DeliveryPartner partner = partnerMap.get(partnerId);
//        partner.setNumberOfOrders(partner.getNumberOfOrders() + 1);
//    }
//}
//
//public Order findOrderById(String orderId) {
//    return orderMap.getOrDefault(orderId, null);
//}
//
//public DeliveryPartner findPartnerById(String partnerId) {
//    return partnerMap.getOrDefault(partnerId, null);
//}
//
//public Integer findOrderCountByPartnerId(String partnerId) {
//    return partnerOrdersMap.getOrDefault(partnerId, new ArrayList<>()).size();
//}
//
//public List<String> findOrdersByPartnerId(String partnerId) {
//    return partnerOrdersMap.getOrDefault(partnerId, new ArrayList<>());
//}
//
//public List<String> findAllOrders() {
//    return new ArrayList<>(orderMap.keySet());
//}
//
//public void deletePartner(String partnerId) {
//    if (partnerMap.containsKey(partnerId)) {
//        List<String> assignedOrders = partnerOrdersMap.getOrDefault(partnerId, new ArrayList<>());
//        for (String orderId : assignedOrders) {
//            orderPartnerMap.remove(orderId);
//        }
//        partnerOrdersMap.remove(partnerId);
//        partnerMap.remove(partnerId);
//    }
//}
//
//public void deleteOrder(String orderId) {
//    if (orderMap.containsKey(orderId)) {
//        String partnerId = orderPartnerMap.get(orderId);
//        if (partnerId != null) {
//            partnerOrdersMap.get(partnerId).remove(orderId);
//            DeliveryPartner partner = partnerMap.get(partnerId);
//            if (partner != null) {
//                partner.setNumberOfOrders(partner.getNumberOfOrders() - 1);
//            }
//            orderPartnerMap.remove(orderId);
//        }
//        orderMap.remove(orderId);
//    }
//}
//
//public Integer findCountOfUnassignedOrders() {
//    return (int) orderMap.keySet().stream().filter(orderId -> !orderPartnerMap.containsKey(orderId)).count();
//}
//
//public Integer findOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
//    if (!partnerOrdersMap.containsKey(partnerId)) return 0;
//    int timeInMinutes = convertTimeToMinutes(time);
//    return (int) partnerOrdersMap.get(partnerId).stream()
//            .map(orderId -> orderMap.get(orderId))
//            .filter(order -> order.getDeliveryTime() > timeInMinutes)
//            .count();
//}
//
//public String findLastDeliveryTimeByPartnerId(String partnerId) {
//    if (!partnerOrdersMap.containsKey(partnerId)) return null;
//    return partnerOrdersMap.get(partnerId).stream()
//            .map(orderId -> orderMap.get(orderId))
//            .max(Comparator.comparingInt(Order::getDeliveryTime))
//            .map(order -> convertMinutesToTime(order.getDeliveryTime()))
//            .orElse(null);
//}
//
//private int convertTimeToMinutes(String time) {
//    String[] parts = time.split(":");
//    return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
//}
//
//private String convertMinutesToTime(int minutes) {
//    int hours = minutes / 60;
//    int min = minutes % 60;
//    return String.format("%02d:%02d", hours, min);
//}
//}
package com.driver;

import java.util.*;
import java.util.stream.Collectors;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

    private HashMap<String, Order> orderMap;
    private HashMap<String, DeliveryPartner> partnerMap;
    private HashMap<String, HashSet<String>> partnerToOrderMap;
    private HashMap<String, String> orderToPartnerMap;

    public OrderRepository(){
        this.orderMap = new HashMap<>();
        this.partnerMap = new HashMap<>();
        this.partnerToOrderMap = new HashMap<>();
        this.orderToPartnerMap = new HashMap<>();
    }

   public void saveOrder(@org.jetbrains.annotations.NotNull Order order) {
       System.out.println("✅ Saving Order: " + order.getId());
       orderMap.put(order.getId(), order);
       System.out.println("📌 Updated orderMap: " + orderMap);
    }
//    public void saveOrder(Order order) {
//        System.out.println("✅ Saving Order: " + order.getId());
//        orderMap.put(order.getId(), order);
//        System.out.println("📌 Updated orderMap: " + orderMap);
//    }

    public void savePartner(String partnerId) {
        partnerMap.put(partnerId, new DeliveryPartner(partnerId));
        System.out.println("📌 Updated partnerMap: " + partnerMap);

    }

//    public void saveOrderPartnerMap(String orderId, String partnerId) {
//        if (orderMap.containsKey(orderId) && partnerMap.containsKey(partnerId)) {
//            orderToPartnerMap.put(orderId, partnerId);
//            partnerToOrderMap.computeIfAbsent(partnerId, k -> new HashSet<>()).add(orderId);
//            DeliveryPartner partner = partnerMap.get(partnerId);
//            partner.setNumberOfOrders(partner.getNumberOfOrders() + 1);
//        }
//    }
    public void saveOrderPartnerMap(String orderId, String partnerId) {
        if (!orderMap.containsKey(orderId)) {
            System.out.println("❌ Order ID not found: " + orderId);
        }
        if (!partnerMap.containsKey(partnerId)) {
            System.out.println("❌ Partner ID not found: " + partnerId);
        }

        if (orderMap.containsKey(orderId) && partnerMap.containsKey(partnerId)) {
            System.out.println("Before adding order: " + partnerToOrderMap);

            orderToPartnerMap.put(orderId, partnerId);
            partnerToOrderMap.computeIfAbsent(partnerId, k -> new HashSet<>()).add(orderId);

            DeliveryPartner partner = partnerMap.get(partnerId);
            int newCount = partnerToOrderMap.get(partnerId).size(); // Ensure correct count
            partner.setNumberOfOrders(newCount);

            System.out.println("✅ Order " + orderId + " assigned to Partner " + partnerId);
            System.out.println("Updated partnerToOrderMap: " + partnerToOrderMap);
            System.out.println("Updated order count for partner: " + partner.getNumberOfOrders());
        }
    }

    public Order findOrderById(String orderId) {
        return orderMap.get(orderId);
    }

    public DeliveryPartner findPartnerById(String partnerId) {
        return partnerMap.get(partnerId);
    }

    public Integer findOrderCountByPartnerId(String partnerId) {
        return partnerToOrderMap.getOrDefault(partnerId, new HashSet<>()).size();
    }

    public List<String> findOrdersByPartnerId(String partnerId) {
        return new ArrayList<>(partnerToOrderMap.getOrDefault(partnerId, new HashSet<>()));
    }

//    public List<String> findAllOrders() {
//        return new ArrayList<>(orderMap.keySet());
//    }
    public List<String> findAllOrders() {
        return orderMap.values().stream()
                .map(order -> "Order ID: " + order.getId() + ", Delivery Time: " + order.getDeliveryTime())
                .collect(Collectors.toList());
    }


    public void deletePartner(String partnerId) {
        if (partnerMap.containsKey(partnerId)) {
            HashSet<String> assignedOrders = partnerToOrderMap.getOrDefault(partnerId, new HashSet<>());
            for (String orderId : assignedOrders) {
                orderToPartnerMap.remove(orderId);
            }
            partnerToOrderMap.remove(partnerId);
            partnerMap.remove(partnerId);
        }
    }

    public void deleteOrder(String orderId) {
        if (orderMap.containsKey(orderId)) {
            String partnerId = orderToPartnerMap.get(orderId);
            if (partnerId != null) {
                partnerToOrderMap.get(partnerId).remove(orderId);
                DeliveryPartner partner = partnerMap.get(partnerId);
                if (partner != null) {
                    partner.setNumberOfOrders(partner.getNumberOfOrders() - 1);
                }
                orderToPartnerMap.remove(orderId);
            }
            orderMap.remove(orderId);
        }
    }

//    public Integer findCountOfUnassignedOrders() {
//        return (int) orderMap.keySet().stream().filter(orderId -> !orderToPartnerMap.containsKey(orderId)).count();
//    }
    public Integer findCountOfUnassignedOrders() {
        System.out.println("📌 orderMap: " + orderMap);
        System.out.println("📌 orderToPartnerMap: " + orderToPartnerMap);

        long unassignedCount = orderMap.keySet().stream()
                .filter(orderId -> !orderToPartnerMap.containsKey(orderId))
                .count();

        System.out.println("📌 Unassigned Orders Count: " + unassignedCount);
        return (int) unassignedCount;
    }

//    public Integer findOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
//        if (!partnerToOrderMap.containsKey(partnerId)) return 0;
//        int timeInMinutes = convertTimeToMinutes(time);
//        return (int) partnerToOrderMap.get(partnerId).stream()
//                .map(orderId -> orderMap.get(orderId))
//                .filter(order -> order.getDeliveryTime() > timeInMinutes)
//                .count();
//    }
    public Integer findOrdersLeftAfterGivenTimeByPartnerId(String partnerId, String time) {
        System.out.println("📌 Partner Orders: " + partnerToOrderMap);
        System.out.println("📌 Checking if Partner ID exists: " + partnerId + " → " + partnerToOrderMap.containsKey(partnerId));

        if (!partnerToOrderMap.containsKey(partnerId)) return 0;

        int timeInMinutes = convertTimeToMinutes(time);
        System.out.println("🕒 Given Time: " + time + " → Minutes: " + timeInMinutes);

        return (int) partnerToOrderMap.get(partnerId).stream()
                .map(orderId -> {
                    Order order = orderMap.get(orderId);
                    if (order == null) {
                        System.out.println("❌ Order ID " + orderId + " not found in orderMap");
                        return null;
                    }
                    System.out.println("🔍 Checking Order ID: " + order.getId() + " → Delivery Time: " + order.getDeliveryTime());
                    return order;
                })
                .filter(order -> order != null && order.getDeliveryTime() > timeInMinutes)
                .count();
    }

    public String findLastDeliveryTimeByPartnerId(String partnerId) {
        if (!partnerToOrderMap.containsKey(partnerId)) return null;
        return partnerToOrderMap.get(partnerId).stream()
                .map(orderId -> orderMap.get(orderId))
                .max(Comparator.comparingInt(Order::getDeliveryTime))
                .map(order -> convertMinutesToTime(order.getDeliveryTime()))
                .orElse(null);
    }

    private int convertTimeToMinutes(@NotNull String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    @Contract(pure = true)
    private @NotNull String convertMinutesToTime(int minutes) {
        int hours = minutes / 60;
        int mins = minutes % 60;
        return String.format("%02d:%02d", hours, mins);
    }
}
