package Model;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class OrderDetail implements Serializable{

    private Pet pet;
    private int quantity;
    private double cost;

    public OrderDetail() {
    }

    public OrderDetail(Pet pet, int quantity, double cost) {
        this.pet = pet;
        this.quantity = quantity;
        this.cost = cost;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "pet=" + pet + ", quantity=" + quantity + ", cost=" + cost + '}';
    }
}
