package main.client;

import java.lang.ref.Cleaner;
import java.util.Objects;

public class Order {
    private static int uniqueId = 1;
    private String meatChoice;
    private String breadChoice;
    private String garnituraChoice;
    private int orderId;
    private Client client;

    public Order (String meatChoice, String breadChoice, String garnituraChoice, Client client){
        if(meatChoice.length()>0){
            this.meatChoice = meatChoice;
        }
        if(breadChoice.length()>0){
            this.meatChoice = meatChoice;
        }
        if(garnituraChoice.length()>0){
            this.garnituraChoice = garnituraChoice;
        }
        orderId = uniqueId++;
        this.client = client;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId && Objects.equals(meatChoice, order.meatChoice) && Objects.equals(breadChoice, order.breadChoice) && Objects.equals(garnituraChoice, order.garnituraChoice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meatChoice, breadChoice, garnituraChoice, orderId);
    }

    public String getMeatChoice() {
        return meatChoice;
    }

    public String getBreadChoice() {
        return breadChoice;
    }

    public String getGarnituraChoice() {
        return garnituraChoice;
    }

    public int getOrderId() {
        return orderId;
    }

    public Client getClient() {
        return client;
    }
}
