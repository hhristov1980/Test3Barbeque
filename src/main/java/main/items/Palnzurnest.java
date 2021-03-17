package main.items;

public class Palnzurnest extends Bread{
    @Override
    protected String validateName() {
        return "Palnozurnest";
    }

    @Override
    protected Double validatePrice() {
        return 1.4;
    }

    @Override
    protected int validateTime() {
        return 5;
    }

    @Override
    protected int validateId() {
        return 2;
    }
}
