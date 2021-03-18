package main.barbeque;

import main.client.Client;
import main.items.Items;


public class Portion {
    private Items meats;
    private Items bread;
    private Items garnish;
    private Client client;

    public Portion (Items meats, Items bread, Items garnish, Client client){
        this.meats = meats;
        this.bread = bread;
        this.garnish = garnish;
        this.client = client;
    }

}
