//package com.driver;
//
//public class Order {
//
//    private String id;
//    private int deliveryTime;
//
//    public Order(String id, String deliveryTime) {
//
//        // The deliveryTime has to converted from string to int and then stored in the attribute
//        //deliveryTime  = HH*60 + MM
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public int getDeliveryTime() {return deliveryTime;}
//}
package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {
        this.id = id;
        this.deliveryTime = convertTimeToMinutes(deliveryTime);
    }

    private int convertTimeToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return (hours * 60) + minutes;
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }
}
