package main.worker;

import main.barbeque.Barbeque;
import main.items.*;
import main.util.Randomizer;

public class GarnishMaker extends Worker {
    public GarnishMaker(Barbeque barbeque) {
        super(barbeque);
    }


    @Override
    public void run() {
        while (true) {
            while (item == null) {
                make();
            }
            barbeque.putGarnishIn(this);
            System.out.println("Prepared "+item.getName());
            try {
                Thread.sleep(item.getTimeToPrepare() * 1000);
                item = null;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    private void make() {
        int option = Randomizer.getRandomInt(1, 5);
        switch (option) {
            case 1:
                if (barbeque.getGarnishes().contains("Lyutenitsa")) {
                    if (barbeque.getGarnishes().get("Lyutenitsa").size() < 10) {
                        item = new Lyutenitsa();
                    } else {
                        System.out.println("No space");
                    }
                } else {
                    item = new Lyutenitsa();
                }
                break;
            case 2:
                if (barbeque.getGarnishes().contains("Russian")) {
                    if (barbeque.getGarnishes().get("Russian").size() < 10) {
                        item = new Russian();
                    } else {
                        System.out.println("No space");
                    }
                } else {
                    item = new Russian();
                }
                break;
            case 3:
                if (barbeque.getGarnishes().contains("SnowWhite")) {
                    if (barbeque.getGarnishes().get("SnowWhite").size() < 10) {
                        item = new SnowWhite();
                    } else {
                        System.out.println("No space");
                    }
                } else {
                    item = new SnowWhite();
                }
                break;
            case 4:
                if (barbeque.getGarnishes().contains("TomatoesCucumbers")) {
                    if (barbeque.getGarnishes().get("TomatoesCucumbers").size() < 10) {
                        item = new TomatosCucumbers();
                    } else {
                        System.out.println("No space");
                    }
                } else {
                    item = new TomatosCucumbers();
                }
                break;
            case 5:
                if (barbeque.getGarnishes().contains("CabbageCarrots")) {
                    if (barbeque.getGarnishes().get("CabbageCarrots").size() < 10) {
                        item = new CabbageCarrots();
                    } else {
                        System.out.println("No space");
                    }
                } else {
                    item = new CabbageCarrots();
                }
                break;
        }
    }
}
