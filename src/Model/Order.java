package Model;

import Services.PetManagement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import utils.Util;

public class Order implements Serializable {

    private String orderId;
    private Date orderDate;
    private String customerName;
    private List<OrderDetail> orderDetailList;

    public Order() {
        this.orderDetailList = new ArrayList();
    }

    public Order(String orderId, Date orderDate, String customerName) {
        this.orderDetailList = new ArrayList();
        this.setOrderId(orderId);
        this.setCustomerName(customerName);
        this.setOrderDate(orderDate);
    }

    public String getOrderId() {
        return orderId;
    }

    public final void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public final void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public final void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        if (orderDetailList != null) {
            this.orderDetailList.addAll(orderDetailList);
        }
    }

    public static String inputId() {
        String id = null;
        do {
            id = Util.inputString("Input Order ID", false);
            if (/*Validation*/false) {
                System.out.println("Error");
            } else {
                break;
            }
        } while (true);
        return id;
    }

    public void input() {
        // orderDate
        do {
            Date orderDate = Util.inputDate("Input order date", false);
            if (/*Validation*/true) {
                setOrderDate(orderDate);
                break;
            } else {
                System.out.println("Error.");
            }
        } while (true);
        // customerName
        do {
            String name = Util.inputString("Input customer's name", false);
            if (/*Validation*/true) {
                setCustomerName(name);
                break;
            } else {
                System.out.println("Error.");
            }
        } while (true);
        // orderDetail
        String petId = null;
        Category cate = null;
        do {
            cate = new Pet().inputCategory(true);
            petId = Util.inputString("Input pet's id", true);
            if (petId.isBlank()) {
                break;
            }
            if (PetManagement.getInstance().getPetByID(petId, cate) != null) {
                if (true /*Validation*/) {
                    Pet pet = PetManagement.getInstance().getPetByID(petId, cate);
                    int quantity = Util.inputInteger("Input order quantity", 0, Integer.MAX_VALUE);
                    int cost = quantity * pet.getUnitPrice();
                    this.orderDetailList.add(new OrderDetail(pet, quantity, cost));
                } else {
                    System.out.println("Error");
                }
            } else {
                System.out.println("Pet not found.");
            }
        } while (!petId.isBlank() || !(cate == null) || this.orderDetailList.isEmpty());
    }

    public int getOrderTotal() {
        int total = 0;
        for (OrderDetail ordD : orderDetailList) {
            total += ordD.getCost();
        }
        return total;
    }

    public int getOrderCount() {
        int count = 0;
        for (OrderDetail ordD : orderDetailList) {
            count += ordD.getQuantity();
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (OrderDetail orderDetail : orderDetailList) {
            sb.append("\n");
            sb.append(orderId);
            sb.append(",").append(orderDate);
            sb.append(",").append(customerName);
            sb.append(",").append(orderDetail.toString());
        }
        return sb.toString().substring(1);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Order) {
            return this.orderId.equalsIgnoreCase(((Order) obj).orderId);
        }
        return super.equals(obj);
    }

}
