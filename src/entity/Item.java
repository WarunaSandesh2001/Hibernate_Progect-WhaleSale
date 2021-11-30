package entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "item")
public class Item {
    @Id
    private String itemCode;
    private String name;
    private double qty;
    private double price;
    private double discount;

    public Item(String iCode, String name, double qty, double price, double discount) {
        this.itemCode = iCode;
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.discount = discount;
    }

    public Item() {
    }

    public String getiCode() {
        return itemCode;
    }

    public void setiCode(String iCode) {
        this.itemCode = iCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Item{" +
                "iCode='" + itemCode + '\'' +
                ", name='" + name + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }
}
