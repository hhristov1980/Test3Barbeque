package main.items;

public class Pleskavitsa extends Meat{
    @Override
    protected String validateName() {
        return "Pleskavitsa";
    }

    @Override
    protected Double validatePrice() {
        return 2.0;
    }

    @Override
    protected int validateTime() {
        return 3;
    }

    @Override
    protected int validateId() {
        return 2;
    }
}
