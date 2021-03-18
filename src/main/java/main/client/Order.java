package main.client;

import java.lang.ref.Cleaner;
import java.util.Objects;

public class Order {
    private static int uniqueId = 1;
    private String meatChoice;
    private String breadChoice;
    private String garnishChoice;
    private int orderId;
    private Client client;

    public Order (String meatChoice, String breadChoice, String garnishChoice, Client client){
        if(meatChoice.length()>0){
            this.meatChoice = meatChoice;
        }
        if(breadChoice.length()>0){
            this.breadChoice = breadChoice;
        }
        if(garnishChoice.length()>0){
            this.garnishChoice = garnishChoice;
        }
        orderId = uniqueId++;
        this.client = client;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId && Objects.equals(meatChoice, order.meatChoice) && Objects.equals(breadChoice, order.breadChoice) && Objects.equals(garnishChoice, order.garnishChoice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meatChoice, breadChoice, garnishChoice, orderId);
    }

    public String getMeatChoice() {
        return meatChoice;
    }

    public String getBreadChoice() {
        return breadChoice;
    }

    public String getGarnishChoice() {
        return garnishChoice;
    }

    public int getOrderId() {
        return orderId;
    }

    public Client getClient() {
        return client;
    }
}
