package main.barbeque;

import main.client.Client;
import main.items.Bread;
import main.items.Garnitura;
import main.items.Items;
import main.items.Meat;


public class Portion {
    private Items meats;
    private Items bread;
    private Items garnitura;
    private Client client;

    public Portion (Items meats, Items bread, Items garnitura, Client client){
        this.meats = meats;
        this.bread = bread;
        this.garnitura = garnitura;
        this.client = client;
    }

}
