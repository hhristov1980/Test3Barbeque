package main;

import main.barbeque.Barbeque;
import main.barbeque.Owner;
import main.client.Client;
import main.worker.BarbequeMaker;
import main.worker.BreadMaker;
import main.worker.GarnituraMaker;
import main.worker.Seller;

public class Demo {
    public static void main(String[] args) {
        Barbeque barbeque = new Barbeque("Hristo shop",26);
        Owner owner = new Owner(barbeque);
        BarbequeMaker barbequeMaker = new BarbequeMaker(barbeque);
        barbequeMaker.start();
        BreadMaker breadMaker = new BreadMaker(barbeque);
        breadMaker.start();
        GarnituraMaker garnituraMaker = new GarnituraMaker(barbeque);
        garnituraMaker.start();
        Seller seller = new Seller(barbeque);
        seller.start();
        for (int i = 0; i<20; i++){
            Client client = new Client(barbeque);
            client.start();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
