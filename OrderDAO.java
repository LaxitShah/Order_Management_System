package j2ee.lab04;

public interface OrderDAO {
    void saveOrder(Order order);
    Order readOrder(int orderNo);
}
