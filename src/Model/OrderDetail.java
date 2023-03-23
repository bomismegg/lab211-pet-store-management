package Model;

import java.io.Serializable;

public class OrderDetail implements Serializable{

    private Pet pet;
    private int quantity;
    private double cost;

    public OrderDetail() {
    }

    public OrderDetail(Pet pet, int quantity) {
        this.pet = pet;
        this.quantity = quantity;
        this.cost = quantity*pet.getUnitPrice();
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

    public void setCost() {
        this.cost = this.getQuantity()*pet.getUnitPrice();
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "pet=" + pet + ", quantity=" + quantity + ", cost=" + cost + '}';
    }
}
