package main.worker;

import main.barbeque.Barbeque;
import main.items.MeatBall;
import main.items.LargeMeatBall;
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
                if(barbeque.getMeats().contains("LargeMeatBall")){
                    if(barbeque.getMeats().get("LargeMeatBall").size()<20){
                        item = new LargeMeatBall();
                    }
                    else {
                        System.out.println("No space");
                    }
                }
                else {
                    item = new LargeMeatBall();
                }
                break;
        }
    }

}
