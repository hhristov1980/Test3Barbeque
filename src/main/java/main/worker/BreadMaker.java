package main.worker;

import main.barbeque.Barbeque;
import main.items.*;
import main.util.Randomizer;

public class BreadMaker extends Worker{
    public BreadMaker(Barbeque barbeque) {
        super(barbeque);
    }

    @Override
    public void run() {
        while (true){
            while (item==null){
                make();
            }
            barbeque.putBreadIn(this);
            try {
                Thread.sleep(item.getTimeToPrepare()*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
    private void make(){
        int option = Randomizer.getRandomInt(1,2);
        switch (option){
            case 1:
                if(barbeque.getBreads().contains("White")){
                    if(barbeque.getBreads().get("White").size()<30){
                        item = new White();
                    }
                    else {
                        System.out.println("No space");
                    }
                }
                else {
                    item = new White();
                }
                break;
            case 2:
                if(barbeque.getBreads().contains("Palnozarnest")){
                    if(barbeque.getBreads().get("Palnozarnest").size()<30){
                        item = new Palnzurnest();
                    }
                    else {
                        System.out.println("No space");
                    }
                }
                else {
                    item = new Palnzurnest();
                }
                break;

        }
    }
}
