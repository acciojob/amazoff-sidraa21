//package com.driver.test;
//
//import com.driver.Application;
//import com.driver.DeliveryPartner;
//import com.driver.Order;
//import com.driver.OrderController;
//import org.junit.Before;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.MethodOrderer;
//
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.HashSet;
//import java.util.List;
//
//@SpringBootTest(classes = Application.class)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class TestCases {
//
//}
package com.driver.test;

import com.driver.Application;
import com.driver.OrderController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCases {

    @Autowired
    private OrderController orderController;

    @BeforeEach
    void setup() {
        // You can initialize any required data here before each test
    }
}
