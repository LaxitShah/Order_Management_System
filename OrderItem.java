package j2ee.lab04;

public class OrderItem {
    private int productId;
    private double price;
    private int qty;

    public OrderItem() {
    }

    public OrderItem(int productId, double price, int qty) {
        this.productId = productId;
        this.price = price;
        this.qty = qty;
    }

   
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "productId=" + productId +
                ", price=" + price +
                ", qty=" + qty +
                '}';
    }
}
