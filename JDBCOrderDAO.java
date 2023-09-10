package j2ee.lab04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCOrderDAO implements OrderDAO {
    private Connection connection;

    public JDBCOrderDAO() {
        connection = DBCon.getConnection();
    }

    @Override
    public void saveOrder(Order order) {
        try {
            connection.setAutoCommit(false);

            String insertCustomerSQL = "INSERT INTO Customer (id, name, email) VALUES (?, ?, ?) RETURNING id";
            PreparedStatement customerStmt = connection.prepareStatement(insertCustomerSQL);
            customerStmt.setInt(1, order.getCustomer().getId());
            customerStmt.setString(2, order.getCustomer().getName());
            customerStmt.setString(3, order.getCustomer().getEmail());

            ResultSet customerResult = customerStmt.executeQuery();
            int customerId = -1;
            if (customerResult.next()) {
                customerId = customerResult.getInt(1);
            }

            String insertAddressSQL = "INSERT INTO Address (id, address, city, pin) VALUES (?, ?, ?, ?) RETURNING id";
            PreparedStatement addressStmt = connection.prepareStatement(insertAddressSQL);
            addressStmt.setInt(1, order.getShippingAddress().getId());
            addressStmt.setString(2, order.getShippingAddress().getAddress());
            addressStmt.setString(3, order.getShippingAddress().getCity());
            addressStmt.setString(4, order.getShippingAddress().getPin());

            ResultSet addressResult = addressStmt.executeQuery();
            int addressId = -1;
            if (addressResult.next()) {
                addressId = addressResult.getInt(1);
            }

            String insertOrderSQL = "INSERT INTO \"order\" (orderno, customerid, shippingaddressid, payment_txnId) VALUES (?, ?, ?, ?) RETURNING orderno";
            PreparedStatement orderStmt = connection.prepareStatement(insertOrderSQL);
            orderStmt.setInt(1, order.getOrderNo());
            orderStmt.setInt(2, customerId);
            orderStmt.setInt(3, addressId);
            orderStmt.setString(4, order.getPaymentTxnId());

            ResultSet orderResult = orderStmt.executeQuery();
            int insertedOrderNo = -1;
            if (orderResult.next()) {
                insertedOrderNo = orderResult.getInt(1);
            }

            String insertOrderItemSQL = "INSERT INTO OrderItems (orderno, productid, price, qty) VALUES (?, ?, ?, ?)";
            PreparedStatement orderItemStmt = connection.prepareStatement(insertOrderItemSQL);
            for (OrderItem item : order.getOrderItems()) {
                orderItemStmt.setInt(1, insertedOrderNo);
                orderItemStmt.setInt(2, item.getProductId());
                orderItemStmt.setDouble(3, item.getPrice());
                orderItemStmt.setInt(4, item.getQty());
                orderItemStmt.addBatch();
            }
            orderItemStmt.executeBatch();

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order readOrder(int orderNo) {
        Order order = null;
        try {
            String selectOrderSQL = "SELECT * FROM \"order\" WHERE orderno = ?";
            PreparedStatement orderStmt = connection.prepareStatement(selectOrderSQL);
            orderStmt.setInt(1, orderNo);

            ResultSet rs = orderStmt.executeQuery();

            if (rs.next()) {
                int customerId = rs.getInt("customerid");
                int shippingAddressId = rs.getInt("shippingaddressid");
                String paymentTxnId = rs.getString("payment_txnId");

                Customer customer = fetchCustomerData(customerId);
                List<OrderItem> orderItems = fetchOrderItems(orderNo);
                Address shippingAddress = fetchAddressData(shippingAddressId);

                order = new Order(orderNo, customer, orderItems, shippingAddress, paymentTxnId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    private Customer fetchCustomerData(int customerId) {
        try {
            String selectCustomerSQL = "SELECT * FROM Customer WHERE id = ?";
            PreparedStatement customerStmt = connection.prepareStatement(selectCustomerSQL);
            customerStmt.setInt(1, customerId);

            ResultSet rs = customerStmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");

                return new Customer(customerId, name, email, null); // Assuming null for billingAddress
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<OrderItem> fetchOrderItems(int orderNo) {
        List<OrderItem> orderItems = new ArrayList<>();
        try {
            String selectOrderItemsSQL = "SELECT * FROM OrderItems WHERE orderno = ?";
            PreparedStatement orderItemStmt = connection.prepareStatement(selectOrderItemsSQL);
            orderItemStmt.setInt(1, orderNo);

            ResultSet rs = orderItemStmt.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("productid");
                double price = rs.getDouble("price");
                int qty = rs.getInt("qty");

                orderItems.add(new OrderItem(productId, price, qty));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItems;
    }

    private Address fetchAddressData(int addressId) {
        try {
            String selectAddressSQL = "SELECT * FROM Address WHERE id = ?";
            PreparedStatement addressStmt = connection.prepareStatement(selectAddressSQL);
            addressStmt.setInt(1, addressId);

            ResultSet rs = addressStmt.executeQuery();

            if (rs.next()) {
                String address = rs.getString("address");
                String city = rs.getString("city");
                String pin = rs.getString("pin");

                return new Address(address, city, pin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
