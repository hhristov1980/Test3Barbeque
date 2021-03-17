package main.items;

public class SnowWhite extends Garnitura{
    @Override
    protected String validateName() {
        return "SnowWhite";
    }

    @Override
    protected Double validatePrice() {
        return 1.2;
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
