package j2ee.lab04;

import java.util.List;

public class Order {
    private int orderNo;
    private Customer customer;
    private List<OrderItem> orderItems;
    private Address shippingAddress;
    private String paymentTxnId;

    public Order() {
    }

    public Order(int orderNo, Customer customer, List<OrderItem> orderItems, Address shippingAddress, String paymentTxnId) {
        this.orderNo = orderNo;
        this.customer = customer;
        this.orderItems = orderItems;
        this.shippingAddress = shippingAddress;
        this.paymentTxnId = paymentTxnId;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getPaymentTxnId() {
        return paymentTxnId;
    }

    public void setPaymentTxnId(String paymentTxnId) {
        this.paymentTxnId = paymentTxnId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNo=" + orderNo +
                ", customer=" + customer +
                ", orderItems=" + orderItems +
                ", shippingAddress=" + shippingAddress +
                ", paymentTxnId='" + paymentTxnId + '\'' +
                '}';
    }
}
