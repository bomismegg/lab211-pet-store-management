package Services;

import Model.Order;
import Model.Pet;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import utils.Util;

public class OrderManagement implements Serializable {

    private static final OrderManagement instance = new OrderManagement();

    public static OrderManagement getInstance() {
        return instance;
    }

    private Map<YearMonth, List<Order>> orderMap;
    private final String YEAR_MONTH_PATTERN = "MM-yyyy";

    public OrderManagement() {
        this.orderMap = new HashMap();
    }

    public List<Order> filterOrderByPet(Pet pet) {
        return pet == null ? null
                : this.orderMap.values().stream().flatMap(e -> e.stream()).filter(p -> pet.equals(p)).toList();
    }

    public List<Order> getOrderById(String id) {
        return this.orderMap.values().stream().flatMap(e -> e.stream()).filter(p -> id.equals(p.getOrderId())).toList();
    }

    public void addOrder() {
        String orderID = Order.inputId();
        Order order = new Order();
        order.input();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(YEAR_MONTH_PATTERN);
        YearMonth yearMonth = YearMonth.parse(Util.toString(order.getOrderDate(), YEAR_MONTH_PATTERN), dateTimeFormatter);

        List<Order> orderList = orderMap.get(yearMonth);

        if (orderList == null) {
            orderList = new ArrayList();
            orderMap.put(yearMonth, orderList);
        }

        if (getOrderById(orderID).isEmpty()) {
            if (orderList.add(order)) {
                order.setOrderId(orderID);
                OrderManagement.getInstance().saveToFile();
            }
        } else {
            System.out.println("This order [" + orderID + "] already exists.");
        }
    }

    public List<Order> getAllOrderFromDate() {
        YearMonth startYm = Util.inputYearMonth("Input start date", YEAR_MONTH_PATTERN, false);
        YearMonth endYm = Util.inputYearMonth("Input end date", YEAR_MONTH_PATTERN, false);

        List<Order> resList = new ArrayList();
        Set<YearMonth> set = orderMap.keySet();
        for (YearMonth ym : set) {
            if (ym.compareTo(startYm) >= 0 && ym.compareTo(endYm) <= 0) {
                List<Order> ordList = orderMap.get(ym);
                for (Order ord : ordList) {
                    resList.add(ord);
                }
            }
        }
        System.out.println("\nList order from " + startYm + " to " + endYm + "\n");
        return resList;
    }

    public void printOutTable(List<Order> list) {
        if (list.isEmpty()) {
            System.out.println("Empty List");
            return;
        }
        int index = 1;
        int total = 0, count = 0;
        Formatter fmt = new Formatter();
        fmt.format("%4s %10s %15s %15s %15s %15s\n",
                "No.",
                "OrderID",
                "OrderDate",
                "Customer",
                "PetCount",
                "OrderTotal");
        for (Order ord : list) {
            fmt.format("%4s %10s %15s %15s %15s %15s\n",
                    index++,
                    ord.getOrderId(),
                    Util.toString(ord.getOrderDate(), Util.DATE_FORMAT),
                    ord.getCustomerName(),
                    ord.getOrderCount(),
                    "$ " + ord.getOrderTotal());
            total += ord.getOrderTotal();
            count += ord.getOrderCount();
        }
        fmt.format("%4s %10s %15s %15s %15s %15s\n",
                "",
                "Total:",
                "",
                "",
                count,
                "$ " + total);
        System.out.println(fmt);
    }

    public void listOrder() {
        printOutTable(getAllOrderFromDate());
    }

    public void sortOrder() {
        String field = Util.inputString("Enter field to sort[ID, Date, Name, Total]", false);
        String sortOrder = Util.inputString("Enter sorting order[ASC or DESC]", false);

        List<Order> orderList = getAllOrderFromDate();
        Collections.sort(orderList, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                switch (field.trim().toUpperCase().substring(0, 1)) {
                    case "I":
                        // ID
                        String ID1 = o1.getOrderId();
                        String ID2 = o2.getOrderId();
                        if (sortOrder.trim().toUpperCase().startsWith("A")) {
                            return ID1.compareTo(ID2);
                        }
                        return -ID1.compareTo(ID2);
                    case "D":
                        // OrderDate
                        Date d1 = o1.getOrderDate();
                        Date d2 = o2.getOrderDate();
                        if (sortOrder.trim().toUpperCase().startsWith("A")) {
                            return d1.compareTo(d2);
                        }
                        return -d1.compareTo(d2);
                    case "N":
                        // Name
                        String name1 = o1.getCustomerName();
                        String name2 = o2.getCustomerName();
                        if (sortOrder.trim().toUpperCase().startsWith("A")) {
                            return name1.compareTo(name2);
                        }
                        return -name1.compareTo(name2);
                    case "T":
                        // Total
                        int total1 = o1.getOrderTotal();
                        int total2 = o2.getOrderTotal();
                        if (sortOrder.trim().toUpperCase().startsWith("A")) {
                            return total1 - total2;
                        }
                        return -(total1 - total2);
                }
                return 0;
            }
        });
        System.out.println("Sorted by: " + field.toUpperCase());
        System.out.println("Sort order: " + sortOrder.toUpperCase());
        printOutTable(orderList);
    }

    public void saveToFile() {
        if (orderMap.isEmpty()) {
            System.out.println("Nothing to write");
            return;
        }

        try {
            File f = new File("order.dat");
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(orderMap);
            oos.close();
            fos.close();
        } catch (Exception e) {
//            System.out.println("Failed to save.");
            e.printStackTrace();
            return;
        }
        System.out.println("Saved data to order.dat");
    }

    public void loadFromFile() {
        try {
            File f = new File("order.dat");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            orderMap = (Map<YearMonth, List<Order>>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            System.out.println("Failed to load");
            return;
        }
        System.out.println("Loaded data from order.dat");
    }
}
