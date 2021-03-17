package main.client;

import main.barbeque.Barbeque;
import main.barbeque.Portion;

public class Client extends Thread{
    private Barbeque barbeque;
    private Portion portion;

    public Client(Barbeque barbeque){
        this.barbeque = barbeque;
    }


    @Override
    public void run() {
        barbeque.makeOrder(this);
        while (portion==null){

        }
        System.out.println("I'm ready");
    }

    public void setPortion(Portion portion) {
        this.portion = portion;
    }
}
