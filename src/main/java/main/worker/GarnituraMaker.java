package main.worker;

import main.barbeque.Barbeque;
import main.items.*;
import main.util.Randomizer;

public class GarnituraMaker extends Worker {
    public GarnituraMaker(Barbeque barbeque) {
        super(barbeque);
    }


    @Override
    public void run() {
        while (true) {
            while (item == null) {
                make();
            }
            barbeque.putGarnituraIn(this);
            try {
                Thread.sleep(item.getTimeToPrepare() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    private void make() {
        int option = Randomizer.getRandomInt(1, 5);
        switch (option) {
            case 1:
                if (barbeque.getGarnitura().contains("Lyutenitsa")) {
                    if (barbeque.getGarnitura().get("Lyutenitsa").size() < 10) {
                        item = new Lyutenitsa();
                    } else {
                        System.out.println("No space");
                    }
                } else {
                    item = new Lyutenitsa();
                }
                break;
            case 2:
                if (barbeque.getGarnitura().contains("Russian")) {
                    if (barbeque.getGarnitura().get("Russian").size() < 10) {
                        item = new Russian();
                    } else {
                        System.out.println("No space");
                    }
                } else {
                    item = new Russian();
                }
                break;
            case 3:
                if (barbeque.getGarnitura().contains("SnowWhite")) {
                    if (barbeque.getGarnitura().get("SnowWhite").size() < 10) {
                        item = new SnowWhite();
                    } else {
                        System.out.println("No space");
                    }
                } else {
                    item = new SnowWhite();
                }
                break;
            case 4:
                if (barbeque.getGarnitura().contains("TomatoesCucumbers")) {
                    if (barbeque.getGarnitura().get("TomatoesCucumbers").size() < 10) {
                        item = new TomatosCucumbers();
                    } else {
                        System.out.println("No space");
                    }
                } else {
                    item = new TomatosCucumbers();
                }
                break;
            case 5:
                if (barbeque.getGarnitura().contains("CabbageCarrots")) {
                    if (barbeque.getGarnitura().get("CabbageCarrots").size() < 10) {
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
