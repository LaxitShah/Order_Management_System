package j2ee.lab04;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class OrderTester {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            OrderWrapper orderWrapper = objectMapper.readValue(new File("order.json"), OrderWrapper.class);

            Order order = orderWrapper.getOrder();

            OrderDAO orderDAO = new JDBCOrderDAO();

            orderDAO.saveOrder(order);
            System.out.println("Order saved to the database.");

            int orderNo = order.getOrderNo();
            Order retrievedOrder = orderDAO.readOrder(orderNo);

            if (retrievedOrder != null) {
                System.out.println("Retrieved Order:");
                System.out.println(retrievedOrder);
            } else {
                System.out.println("Order not found in the database.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
