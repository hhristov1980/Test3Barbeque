package main.items;

public class Steak extends Meat{
    @Override
    protected String validateName() {
        return "Steak";
    }

    @Override
    protected Double validatePrice() {
        return 3.0;
    }

    @Override
    protected int validateTime() {
        return 4;
    }

    @Override
    protected int validateId() {
        return 3;
    }
}
