package main.items;

public class Lyutenitsa extends Garnish {
    @Override
    protected String validateName() {
        return "Lyutenitsa";
    }

    @Override
    protected Double validatePrice() {
        return 1.1;
    }

    @Override
    protected int validateTime() {
        return 8;
    }

    @Override
    protected int validateId() {
        return 2;
    }
}
