package main;

import main.barbeque.Barbeque;
import main.barbeque.Owner;
import main.client.Client;
import main.worker.BarbequeMaker;
import main.worker.BreadMaker;
import main.worker.GarnishMaker;
import main.worker.Seller;

public class Demo {
    public static void main(String[] args) {
        Barbeque barbeque = new Barbeque("Hristo shop",26);
        BarbequeMaker barbequeMaker = new BarbequeMaker(barbeque);
        barbequeMaker.start();
        BreadMaker breadMaker = new BreadMaker(barbeque);
        breadMaker.start();
        GarnishMaker garnishMaker = new GarnishMaker(barbeque);
        garnishMaker.start();
        Seller seller = new Seller(barbeque);
        seller.start();
        Owner owner = new Owner(barbeque);
        owner.setDaemon(true);
        owner.start();
        for (int i = 0; i<50; i++){
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
