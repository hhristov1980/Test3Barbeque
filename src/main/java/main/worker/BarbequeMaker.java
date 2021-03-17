package main.worker;

import main.barbeque.Barbeque;
import main.items.Meat;
import main.items.MeatBall;
import main.items.Pleskavitsa;
import main.items.Steak;
import main.util.Randomizer;

public class BarbequeMaker extends Worker{
    public BarbequeMaker(Barbeque barbeque) {
        super(barbeque);
    }

    @Override
    public void run() {
        while (true){
            while (item==null){
                make();
            }
            barbeque.putMeatIn(this);
            try {
                Thread.sleep(item.getTimeToPrepare()*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
    private void make(){
        int option = Randomizer.getRandomInt(1,3);
        switch (option){
            case 1:
                if(barbeque.getMeats().contains("MeatBall")){
                    if(barbeque.getMeats().get("MeatBall").size()<20){
                        item = new MeatBall();
                    }
                    else {
                        System.out.println("No space");
                    }
                }
                else {
                    item = new MeatBall();
                }
                break;
            case 2:
                if(barbeque.getMeats().contains("Steak")){
                    if(barbeque.getMeats().get("Steak").size()<20){
                        item = new Steak();
                    }
                    else {
                        System.out.println("No space");
                    }
                }
                else {
                    item = new Steak();
                }
                break;
            case 3:
                if(barbeque.getMeats().contains("Pleskavitsa")){
                    if(barbeque.getMeats().get("Pleskavitsa").size()<20){
                        item = new Pleskavitsa();
                    }
                    else {
                        System.out.println("No space");
                    }
                }
                else {
                    item = new Pleskavitsa();
                }
                break;
        }
    }

}
