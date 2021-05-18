public class Product {
    boolean isImported;
    ProductType type;
    double price;
    int count;
    String info;
    double priceAfterTax;

    @Override
    public String toString() {
        return "Product{" +
                "isImported=" + isImported +
                ", type=" + type +
                ", price=" + price +
                ", count=" + count +
                ", info='" + info + '\'' +
                '}';
    }

    Product(boolean isImported, ProductType type, double price, int count, String info) {
        this.isImported = isImported;
        this.type = type;
        this.price = price;
        this.count = count;
        this.info = info;
        this.priceAfterTax = price;
    }

    public void setPrice(double newPrice) {
        this.priceAfterTax = newPrice;
    }

}
