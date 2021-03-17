package main.worker;

import main.barbeque.Barbeque;
import main.items.Items;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Worker extends Thread{
    protected Barbeque barbeque;
    protected Items item;

    public Worker(Barbeque barbeque){
        this.barbeque = barbeque;
    }

    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }
}
