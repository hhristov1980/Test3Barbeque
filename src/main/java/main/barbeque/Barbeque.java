package main.barbeque;

import main.client.Client;
import main.client.Order;
import main.items.*;
import main.util.Constants;
import main.util.Randomizer;
import main.worker.BarbequeMaker;
import main.worker.BreadMaker;
import main.worker.GarnituraMaker;
import main.worker.Seller;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.concurrent.*;

public class Barbeque {
    private String name;
    private ConcurrentHashMap<String, BlockingQueue<Items>> meats;
    private ConcurrentHashMap<String, BlockingQueue<Items>> breads;
    private ConcurrentHashMap<String, BlockingQueue<Items>> garnituras;
    private CopyOnWriteArraySet<Order> orders;
    private double money;
    private int shopId;

    public Barbeque (String name, int shopId){
        if(name.length()>0){
            this.name = name;
        }
        this.shopId = shopId;

        meats = new ConcurrentHashMap<>();
        breads = new ConcurrentHashMap<>();
        garnituras = new ConcurrentHashMap<>();
        orders = new CopyOnWriteArraySet<>();
    }

    public synchronized void putMeatIn(BarbequeMaker barbequeMaker){
        Items meat = barbequeMaker.getItem();
        if(!meats.containsKey(meat.getName())){
            meats.put(meat.getName(),new LinkedBlockingQueue<>(20));
        }
        try {
            meats.get(meat.getName()).put(meat);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void putBreadIn(BreadMaker breadMaker){
        Items bread = breadMaker.getItem();
        if(!breads.containsKey(bread.getName())){
            breads.put(bread.getName(),new LinkedBlockingQueue<>(30));
        }
        try {
            breads.get(bread.getName()).put(bread);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void putGarnituraIn(GarnituraMaker garnituraMaker){
        Items garnitura = garnituraMaker.getItem();
        if(!garnituras.containsKey(garnitura.getName())){
            garnituras.put(garnitura.getName(),new LinkedBlockingQueue<>(10));
        }
        try {
            garnituras.get(garnitura.getName()).put(garnitura);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void makeOrder(Client client){
        String meatType = Constants.MEATS[Randomizer.getRandomInt(0,Constants.MEATS.length-1)];
        String breadType = Constants.BREADS[Randomizer.getRandomInt(0,Constants.BREADS.length-1)];
        String garnituraType = Constants.GARNITURAS[Randomizer.getRandomInt(0,Constants.GARNITURAS.length-1)];
        orders.add(new Order(meatType,breadType,garnituraType,client));
        notifyAll();
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void makePortionsAndFillArchive(Seller seller, PrintStream ps){
        for(Order order: orders) {
            String meatType = order.getMeatChoice();
            String breadType = order.getBreadChoice();
            String garnituraType = order.getGarnituraChoice();
            Client client = order.getClient();
            if (meats.containsKey(meatType) && breads.contains(breadType) && garnituras.contains(garnituraType)) {
                if (meats.get(meatType).size() > 0 && breads.get(breadType).size() > 0 && garnituras.get(garnituraType).size() > 0) {
                    Items meat = meats.get(meatType).peek();
                    Items bread = breads.get(breadType).peek();
                    Items garnitura = garnituras.get(garnituraType).peek();
                    try {
                        meat = meats.get(meatType).take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        bread = breads.get(breadType).take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        garnitura = garnituras.get(garnituraType).take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Portion portion = new Portion(meat, bread, garnitura, client);
                    double amountOfOrder = meat.getPrice() + bread.getPrice() + garnitura.getPrice();
                    money += amountOfOrder;
                    orders.remove(order);
                    client.setPortion(portion);
                    ps.println(breadType + ", " + meatType + ", " + garnituraType + ", " + amountOfOrder + ", " + LocalDateTime.now());
                    notifyAll();
                }
            }
        }
        }


    public String getName() {
        return name;
    }

    public ConcurrentHashMap<String, BlockingQueue<Items>> getMeats() {
        return meats;
    }

    public ConcurrentHashMap<String, BlockingQueue<Items>> getBreads() {
        return breads;
    }

    public ConcurrentHashMap<String, BlockingQueue<Items>> getGarnitura() {
        return garnituras;
    }
}
