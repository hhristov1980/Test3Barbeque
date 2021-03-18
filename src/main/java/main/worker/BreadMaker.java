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
            System.out.println("Prepared "+item.getName());
            try {
                Thread.sleep(item.getTimeToPrepare()*1000);
                item = null;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
    private void make(){
        int option = Randomizer.getRandomInt(1,2);
        switch (option){
            case 1:
                if(barbeque.getBreads().contains("WhiteBread")){
                    if(barbeque.getBreads().get("WhiteBread").size()<30){
                        item = new WhiteBread();
                    }
                    else {
                        System.out.println("No space");
                    }
                }
                else {
                    item = new WhiteBread();
                }
                break;
            case 2:
                if(barbeque.getBreads().contains("WholeGrain")){
                    if(barbeque.getBreads().get("WholeGrain").size()<30){
                        item = new WholeGrain();
                    }
                    else {
                        System.out.println("No space");
                    }
                }
                else {
                    item = new WholeGrain();
                }
                break;

        }
    }
}
